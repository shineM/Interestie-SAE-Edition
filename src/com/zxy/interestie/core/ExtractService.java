package com.zxy.interestie.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.TextUI;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zxy.interestie.bean.LinkTypeData;
import com.zxy.interestie.rule.Rule;
import com.zxy.interestie.rule.RuleException;
import com.zxy.interestie.util.TextUtil;

/**
 * 
 * @author zxy
 * 
 */
public class ExtractService
{
	/**
	 * @param rule
	 * @param size 
	 * @return
	 */
	public static List<LinkTypeData> extract(Rule rule, int size)
	{

		// 进行对rule的必要校验
		validateRule(rule);

		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		LinkTypeData data = null;
		try
		{
			/**
			 * 解析rule
			 */
			String url = rule.getUrl();
			String elementFilter = rule.getElementFilter();
			String type = rule.getType();
			
			int requestType = rule.getRequestMoethod();

			Connection conn = Jsoup.connect(url);
            // 设置请求类型
			Document doc = null;
			switch (requestType)
			{
			case Rule.GET:
				doc = conn.timeout(3000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(3000).post();
				break;
			}

			//处理返回数据
			Elements results = new Elements();
			
			if(type == "class"){
				results = doc.getElementsByClass(elementFilter);
			}
			else if(type == "id"){
				Element result = doc.getElementById(elementFilter);
				results.add(result);
			}
			else if(type == "tag"){
				results = doc.getElementsByTag(elementFilter) ;
			}
			else{
				//当elementFilter为空时默认去body标签
				if (TextUtil.isEmpty(elementFilter))
				{
					results = doc.getElementsByTag("body");
				}
			}
            if(size>7){
            	size=results.size();
            }
          
			for (int i=0;i<size;i++)
			{   
			
				Elements links = results.get(i).select("a[href]");
           
				for (Element link : links)
				{
					
					
					String linkText = link.text();
					String linkHref;
					if (rule.isPreHref()) {
						linkHref = rule.getRootUrl()+link.attr("href");
					}else {
						linkHref = link.attr("href");
					}
//					System.out.println(linkHref);
					data = new LinkTypeData();
					data.setLinkHref(linkHref);
					data.setLinkText(linkText);
					data.setSummary(rule.getTag());
                    data.setContent(rule.getFrom());
					datas.add(data);
				}
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return datas;
	}

	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule)
	{
		String url = rule.getUrl();
		if (TextUtil.isEmpty(url))
		{
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://"))
		{
			throw new RuleException("url的格式不正确！");
		}


	}
//我很烦  数字尾巴的程序员你出来  我打不死你
	public static List<LinkTypeData> extractSpecial(Rule rule, int tagsize) {
		// TODO Auto-generated method stub
		return null;
	}


}
