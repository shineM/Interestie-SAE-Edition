package com.zxy.interestie.rule;

/**
 * 规则类
 * 
 * @author zhy
 * 
 */
public class Rule
{
	/**
	 * 链接
	 */
	private String url;
    private String rootUrl; 	
	/**
	 * 过滤条件
	 */
	private String elementFilter ;
	/**
	 * 条目数量
	 */
	private int amount ;

	/**
	 * 设置elementFilter的类型，默认为class 
	 */
	private String type = "class" ;
	
	/**
	 *GET / POST
	 * 请求的类型，默认GET
	 */
	private boolean preHref ;
	
	private String tag ;
	
	private String from ;
	
	private int requestMoethod = GET ; 
	
	public final static int GET = 0 ;
	public final static int POST = 1 ;
	

	

	public Rule()
	{
	}

	
public Rule(String url,String rootUrl, 
			String elementFilter, String type, int requestMoethod, boolean preHref,String tag,String from)
	{
		super();
		this.url = url;
		this.rootUrl = rootUrl;
		this.elementFilter = elementFilter;
		this.type = type;
		this.requestMoethod = requestMoethod;
		this.preHref = preHref ;
		this.tag = tag ;
		this.from = from ;
	}

	
	



	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}


	public String getElementFilter()
	{
		return elementFilter;
	}

	public void setElementFilter(String elementFilter)
	{
		this.elementFilter = elementFilter;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getRequestMoethod()
	{
		return requestMoethod;
	}

	public void setRequestMoethod(int requestMoethod)
	{
		this.requestMoethod = requestMoethod;
	}


	public boolean isPreHref() {
		return preHref;
	}


	public void setPreHref(boolean preHref) {
		this.preHref = preHref;
	}


	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}


	public String getRootUrl() {
		return rootUrl;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	
	

}
