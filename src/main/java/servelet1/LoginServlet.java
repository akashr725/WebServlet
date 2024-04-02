package servelet1;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=null;
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userlogin","root","Akash@1997");
			
			
			String uname=request.getParameter("username");
			String pass=request.getParameter("pass");
			//System.out.println(uname+" "+pass);
			
			PreparedStatement ps=con.prepareStatement("select * from servletdata where uname=? and pasword=?");
			ps.setString(1, uname);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				
				RequestDispatcher rd=request.getRequestDispatcher("WelcomeServlet");
				rd.forward(request, response);
				
			}
			else {
				
				pw.println("Invalid username or Password");
				RequestDispatcher rd=request.getRequestDispatcher("home.html");
				rd.include(request, response);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

