package com.zxy.interestie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxy.interestie.bean.User;
import com.zxy.interestie.dao.DAO;

public class Signup extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String username =  req.getParameter("username");
		username = URLDecoder.decode(username,"utf-8");
		String password = req.getParameter("password");
		password = URLDecoder.decode(password,"utf-8");
		String email =req.getParameter("email");
		email = URLDecoder.decode(email,"utf-8");
		String tags =  req.getParameter("tags");
		tags = URLDecoder.decode(tags,"utf-8");
		System.out.println(tags);
		
		PrintWriter writer = resp.getWriter();
		String out;
	
		
			User user = new User(username,password,email,tags);
			DAO dao = new DAO();
			if(dao.insert(user)){
				out = "2";
			}else{
			out = "1";}
		
		writer.print(out);
		writer.flush();
		writer.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
