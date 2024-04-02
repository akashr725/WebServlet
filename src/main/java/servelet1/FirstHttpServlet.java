package servelet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=null;
		PrintWriter pw=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userlogin","root","Akash@1997");
			
			String name=request.getParameter("name");
			String uname=request.getParameter("username");
			String email=request.getParameter("email");
			String number=request.getParameter("number");
			String pass=request.getParameter("pass");
			String cpass=request.getParameter("cpass");
			String gender=request.getParameter("gender");
			PreparedStatement ps=con.prepareStatement("insert into servletdata values(?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, uname);
			ps.setString(3, email);
			ps.setString(4, number);
			ps.setString(5, pass);
			ps.setString(6, cpass);
			ps.setString(7, gender);
			int i=ps.executeUpdate();
			if(i<0) {
				pw.println("Registration Unsuccessfull");
			}
			else {
				//pw.println("Registration Successfull");
				//response.sendRedirect("home.html");
				RequestDispatcher rd=request.getRequestDispatcher("home.html");
				rd.forward(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

}
