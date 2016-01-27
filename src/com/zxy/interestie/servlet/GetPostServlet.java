package com.zxy.interestie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxy.interestie.bean.LinkTypeData;
import com.zxy.interestie.core.ExtractService;
import com.zxy.interestie.rule.Rule;

@SuppressWarnings("serial")
public class GetPostServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(req, resp);
    }
  
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
		int page = Integer.parseInt(req.getParameter("page"));
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/xml;charset=utf-8"); 
		resp.setHeader("Cache-Control", "no-cache");
		String tagString = req.getParameter("tags");
		tagString = URLDecoder.decode(tagString, "UTF-8");


		List<String> tags = Arrays.asList(tagString.split("~"));
    	PrintWriter out =resp.getWriter();
    	ExtractAll test=new ExtractAll();
    	

		String result = null;
		try {
			result = test.getListByWords(page, tags);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		out.println(result);

        out.close();

    }
}
