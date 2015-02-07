package net.shenru;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.shenru.database.DataManage;

/**
 * 初始化
 * 
 * @author along
 */
public class ShenruApplicationContent extends GenericServlet {

	@Override
	public void init() throws ServletException {
		DataManage.getInstace();
	}

	@Override
	public void destroy() {
		DataManage.getInstace().destroy();
	}

	@Override
	public String getInitParameter(String name) {
		return super.getInitParameter(name);
	}

	@Override
	public Enumeration getInitParameterNames() {
		return super.getInitParameterNames();
	}

	@Override
	public ServletConfig getServletConfig() {
		return super.getServletConfig();
	}

	@Override
	public ServletContext getServletContext() {
		return super.getServletContext();
	}

	@Override
	public String getServletInfo() {
		return super.getServletInfo();
	}

	@Override
	public void log(String msg) {
	}

	@Override
	public void log(String message, Throwable t) {
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getServletName() {
		return super.getServletName();
	}

}
