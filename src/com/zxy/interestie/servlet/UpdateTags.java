package com.zxy.interestie.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxy.interestie.dao.DAO;

public class UpdateTags extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3711594970491178859L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = req.getParameter("user");
		user = URLDecoder.decode(user, "UTF-8");
		String tags = req.getParameter("tags");
		tags = URLDecoder.decode(tags, "UTF-8");
		DAO dao = new DAO();
		if(dao.update(user,tags)){
			System.out.println("插入成功");
		}else{
			System.out.println("upadte error");
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
