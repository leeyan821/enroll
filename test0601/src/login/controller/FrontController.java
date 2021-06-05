package login.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	HashMap<String,Controller> list = null;
	String charset = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		charset = config.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		list.put("/login.do", new LoginController());
		list.put("/search.do", new SearchController());
		list.put("/enroll.do", new EnrollController());
		list.put("/logout.do", new LogoutController());
		list.put("/subEnroll.do", new SubEnrollController());
		list.put("/show.do", new ShowController());
		list.put("/stuShow.do", new StushowContoller());
		list.put("/memberCheck.do", new MemberCheckController());
		list.put("/delete.do", new DeleteController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding(charset);
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		Controller subcontroller = list.get(path);
		subcontroller.execute(req, resp);
	}
}
