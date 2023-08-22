package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		MyDao dao = new MyDao();
		Customer customer = dao.fetchByEmail(email);
		if (customer == null) {
			resp.getWriter().print("<h1 style='color:green'>Invalid Email</h1>");
			req.getRequestDispatcher("Login.html").include(req,resp);
		} else {
			if (password.equals(customer.getPassword())) {
				resp.getWriter().print("<h1 style='color:green'>Login success</h1>");
				req.getRequestDispatcher("CustomerHome.html").include(req,resp);
			} else {
             //	if response should be both text and html
				resp.getWriter().print("<h1 style='color:green'>Invalid password</h1>");
				req.getRequestDispatcher("Signup.html").include(req,resp);
			}
		}
	}
}
