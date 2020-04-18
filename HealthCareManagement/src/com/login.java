package com;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		doGet(request, response);
		
		String name = request.getParameter("name");
		String pass= request.getParameter("pass");
		   try {
			      Class.forName("com.mysql.jdbc.Driver");
			      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
			      Statement stmt = con.createStatement();
			      ResultSet rs = stmt.executeQuery("select uname,upass from user_reg where uname='"+name+"' and upass='"+pass+"'");

	
	if(rs.next()){
		
		HttpSession session =request.getSession();
		session.setAttribute("name", name);
		response.sendRedirect("welcome.jsp");
	}
	else {
		out.println(" :Wrong username or password");
	}
	
	
	} catch(ClassNotFoundException e) {
		e.printStackTrace();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
	}

}
