package net.shenru;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shenru.database.LoginData;
import net.shenru.mould.User;

/**
 * 登录
 * 
 * @author along
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "text/html;charset=UTF-8");

		String userName = req.getParameter("username");
		String passWord = req.getParameter("password");

		Cookie[] cookies = req.getCookies();
		if (userName == null && passWord == null && cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if ("username".equals(name)) {
					userName = cookie.getValue();
				} else if ("password".equals(name)) {
					passWord = cookie.getValue();
				}
			}
		}

		LoginData loginData = new LoginData();
		User user = loginData.getUser(userName, passWord);
		if (user != null) {
			Cookie nameCookie = new Cookie("username", userName);
			nameCookie.setMaxAge(60 * 60 * 24 * 7);
			Cookie passwordCookie = new Cookie("password", passWord);
			nameCookie.setMaxAge(60 * 60 * 24 * 7);

			resp.addCookie(nameCookie);
			resp.addCookie(passwordCookie);
			resp.addHeader("Refresh", "3;url=\"http://localhost:8080:/ShenruWeb/index.html");
			resp.getWriter()
					.print("登录成功！3秒后自动跳转到<a href=\"http://localhost:8080/ShenruWeb/index.html\">首页</a>");
		} else {
			resp.getWriter().print("用户名或密码错误");
		}

	}
}
