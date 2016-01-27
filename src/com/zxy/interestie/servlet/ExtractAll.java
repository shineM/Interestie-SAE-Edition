package com.zxy.interestie.servlet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.zxy.interestie.bean.LinkTypeData;
import com.zxy.interestie.core.ExtractService;
import com.zxy.interestie.rule.Rule;

public class ExtractAll {

	public String getListByWords(int page, List<String> tags) {
		List<LinkTypeData> extracts = new ArrayList<LinkTypeData>();
		Rule rule1 = new Rule(page == 1 ? "http://www.sspai.com" : "http://www.sspai.com" + "/?page=" + page,
				"http://www.sspai.com", "title", "class", Rule.GET, false, "App", "少数派");
		Rule rule2 = new Rule(page == 1 ? "http://www.v2ex.com" : "http://www.v2ex.com" + "/recent?p=" + page,
				"http://www.v2ex.com", "item_title", "class", Rule.GET, true, "开发者社区", "V2EX");
		Rule rule3 = new Rule(
				page == 1 ? "http://zuimeia.com/apps/?page=1&platform=1"
						: "http://zuimeia.com/apps/?page=" + page + "&platform=1",
				"http://zuimeia.com", "app-title", "class", Rule.GET, true, "App", "最美应用");
		Rule rule4 = new Rule(
				page == 1 ? "http://www.zhibo8.cc/nba/" + getDateByPage(1) + "-top10.htm"
						: "http://www.zhibo8.cc/nba/" + getDateByPage(page) + "-top10.htm",
				"http://www.zhibo8.cc/nba/2015/1112-top10.htm", "video_full", "class", Rule.GET, false, "篮球",
				"Zhibo8");
		Rule rule5 = new Rule(
				page == 1 ? "http://stackexchange.com/questions?page=1"
						: "http://stackexchange.com/questions?page=" + page,
				"http://stackexchange.com/", "question-link", "class", Rule.GET, false, "开发者社区", "StackOverflow");
//		Rule rule6 = new Rule(
//				page == 1 ? "http://www.acfun.tv/v/list91/index_1.htm"
//						: "http://www.acfun.tv/v/list91/index_" + page + ".htm",
//				"http://www.acfun.tv", "title", "class", Rule.GET, true, "news", "Ac文章区");
		Rule rule7 = new Rule(
				page == 1
						? "http://www.acfun.tv/dynamic/channel/1.aspx?channelId=86&orderBy=9&startDate="
								+ getLastDayMillis() + "&endDate=" + System.currentTimeMillis() + "&pageSize=16"
						: "http://www.acfun.tv/dynamic/channel/" + page + ".aspx?channelId=86&orderBy=9&startDate="
								+ getLastDayMillis() + "&endDate=" + System.currentTimeMillis() + "&pageSize=16",
				"http://www.acfun.tv", "title", "class", Rule.GET, true, "生活娱乐", "Acfun");
		Rule rule8 = new Rule(
				page == 1 ? "http://www.acfun.tv/dynamic/channel/1.aspx?channelId=95&orderBy=0&pageSize=16"
						: "http://www.acfun.tv/dynamic/channel/" + page + ".aspx?channelId=95&orderBy=0&pageSize=16",
				"http://www.acfun.tv", "title", "class", Rule.GET, true, "篮球", "Acfun");
		Rule rule9 = new Rule(
				page == 1 ? "http://segmentfault.com/questions/hottest?page=1"
						: "http://segmentfault.com/questions/hottest?page=" + page,
				"http://segmentfault.com", "title", "class", Rule.GET, true, "开发者社区", "Segmentfault");
		Rule rule10 = new Rule(page == 1 ? "http://www.smzdm.com/p1" : "http://www.smzdm.com/p" + page,
				"http://www.smzdm.com/p1", "itemName", "class", Rule.GET, false, "什么值得买", "SMZDM");
		Rule[] rules1 = new Rule[] { rule1, rule2, rule3, rule4, rule5,  rule7, rule8, rule9 };

		int tagsize = 5;
		if (tags.size() < 4) {
			tagsize = 12 - tags.size() * 2;
		}
		// 特殊处理规则
		// for (Rule rule : rules2) {
		// if (tags.contains(rule.getTag())) {
		// extracts.addAll(ExtractService.extract(rule, tagsize));
		// }
		//
		// }
		for (Rule rule : rules1) {
			if (tags.contains(rule.getTag())) {
				extracts.addAll(ExtractService.extract(rule, tagsize));
			}

		}
		Collections.shuffle(extracts);
		return addAll(extracts);
	}

	private long getLastDayMillis() {

		// TODO Auto-generated method stub
		return System.currentTimeMillis() - (long) 86400000;
	}

	public static String getDateByPage(int page) {

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		String result = null;
		int days = getMonthDays(year, month);
		if (page == 1) {
			if (hour < 16) {
				return getDateByPage(2);
			}
		}
		if (page > 30) {
			result = year + "/" + (month < 10 ? "0" + (month) : month) + (day < 10 ? "0" + day : day);
		} else if ((day - page + 1) > 0) {
			result = year + "/" + (month < 10 ? "0" + (month) : month)
					+ ((day - page + 1) < 10 ? "0" + (day - page + 1) : (day - page + 1));
		} else if (month - 1 > 0) {
			result = year + "/" + (month - 1 < 10 ? "0" + (month - 1) : month - 1)
					+ ((day + days - page + 2) < 10 ? "0" + (day + days - page + 2) : (day + days - page + 2));
		} else {
			result = (year - 1) + "/" + "12" + ((days - page + 2) < 10 ? "0" + (days - page + 2) : (days - page + 2));
		}
		return result;
	}

	private static int getMonthDays(int year, int month) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);

		return cal.getActualMaximum(Calendar.DATE);
	}

	public void printf(List<LinkTypeData> datas) {
		for (LinkTypeData data : datas) {
			System.out.println(data.getLinkText());
			System.out.println(data.getLinkHref());
			System.out.println("***********************************");
		}

	}

	public String addAll(List<LinkTypeData> datas) {
		StringBuffer sb = new StringBuffer();
		for (LinkTypeData data : datas) {
			sb.append(data.getLinkText());
			sb.append("***");
			sb.append(data.getLinkHref());
			sb.append("***");
			sb.append(data.getSummary());
			sb.append("***");
			sb.append(data.getContent());
			sb.append("***");
		}
		return sb.toString();

	}

}
