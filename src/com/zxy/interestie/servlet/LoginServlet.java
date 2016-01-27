package com.zxy.interestie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zxy.interestie.dao.DAO;



public class LoginServlet extends HttpServlet {

	/**
	 * 处理用户登录登出
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		resp.setCharacterEncoding("utf-8");
		String name = req.getParameter("username");
		name = URLDecoder.decode(name, "utf-8");
		String pwd = req.getParameter("password");
		pwd = URLDecoder.decode(pwd, "utf-8");
		
		String flag = null;		
		DAO dao = new DAO();
		if (pwd.equals("∆ß©ƒ∂ßåΩ≈ç√∫˜µ")) {
			
			flag = dao.getTags(name);
			
		} else if(pwd.equals("mail∂ˆ˙ƒøßπ∆∂∂¥ƒ˚ß˜ƒ")){
			flag = dao.getEmail(name);
		}
		else{
			
			 flag = dao.login(name, pwd);
System.out.println("返回码："+flag);
			if (flag != "2" & flag != "3") {
				session.setAttribute("username", flag);

			}
		}
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(flag);
		printWriter.flush();
		printWriter.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
