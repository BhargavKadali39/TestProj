package org.team4.spring.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.team4.spring.essentials.Login;
import org.team4.spring.essentials.Mail;
import org.team4.spring.essentials.User;

@Controller
public class FrontController {

	// EmpService eserv;
	// ProService pserv;
	// T4_ProjectModuleService promodserv;
	User user = new User(1, "John Doe", "password123", "2023-06-08", "EMP001", "Active", (short) 1,
			"2023-06-08 10:30:00");

	@Autowired
	public FrontController() {
		System.out.println("Here");
	}

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// public String getLoginPage(Model model) {
	// System.out.println("Profile JSP Requested");
	// model.addAttribute("user", this.user);
	// return "profile";
	// }

	@RequestMapping(value = "wedit", method = RequestMethod.GET)
	public String getNewedit(Model model) {
		System.out.println("Newedit JSP Requested");
		model.addAttribute("user", this.user);
		return "Newedit";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		System.out.println("Login JSP Requested");
		return "login";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getLoginPage1(Model model) {
		System.out.println("Test JSP Requested");
		return "test";
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String getVerified(@Validated Login log, Model model) {
		System.out.println("managerAnalytics JSP Requested");

		try {
			Class s = Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Connection conn = DriverManager.getConnection(url, username, password);

			// Execute SQL query
			String sql = "select * from T4_Users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// Retrieve and display data
			while (rs.next()) {
				String name = rs.getString("user_displayname");
				String pwd = rs.getString("user_password");
				if (log.getUname().equalsIgnoreCase(name) && log.getPassword().equalsIgnoreCase(pwd)) {
					System.out.println("logged in");
					String role = Integer.toString(rs.getInt("user_role"));
					if (role.equalsIgnoreCase("1")) {
						// return "managerAnalytics";
						return "analytics";
						// return "prod";
					} else {
						// return "analytics";
						return "prod";
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "404error";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String getAllEmployees(@Validated Login log, Model model) {
		System.out.println("Login List JSP Requested");

		// Get the email parameter from the request
		String emailid = log.getEmail();
		System.out.println(emailid);
		Mail mail = new Mail();
		mail.email(emailid);
		// response.sendRedirect("otp.jsp");

		return "otp";
	}

	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public String getMail(Model model) {
		System.out.println("Mail JSP Requested");
		return "mail";
	}

	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String getErrorPage(Model model) {
		System.out.println("Error Page JSP Requested");
		return "404error";
	}

	@RequestMapping(value = "/an", method = RequestMethod.GET)
	public String getAnalytics(Model model) {
		System.out.println("Analytics JSP Requested");
		return "analytics";
	}

	@RequestMapping(value = "/man", method = RequestMethod.GET)
	public String getManagerAnalytics(Model model) {
		System.out.println("Manager Analytics JSP Requested");
		return "managerAnalytics";
	}

	@RequestMapping(value = "/getEmail", method = RequestMethod.GET)
	public String getForgotPage(Model model) {
		System.out.println("getforgotpassword JSP Requested");
		return "mail";
	}

}