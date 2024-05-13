package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Registration extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name = req.getParameter("user_name");
	String email = req.getParameter("email");
	String password = req.getParameter("password");
	String course = req.getParameter("course");
	
	resp.setContentType("text/html");
	PrintWriter out= resp.getWriter();
	out.println("<h1>Name :" +name+"</h1>");
	out.println("<h1>Email :" +email+"</h1>");
	out.println("<h1>Password :" +password+"</h1>");
	out.println("<h1>course:" +course+"</h1>");
	
	
	}
	
	
	

	

}
