package login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.service.Service;

public class LoginController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String job = request.getParameter("job");

		if(id.isEmpty() || pwd.isEmpty()) {
			request.setAttribute("error", "enter all");
			HttpUtil.forward(request,response,"/index.jsp");
			return;
		}else if(job==null) {
			request.setAttribute("error", "check one");
			HttpUtil.forward(request,response,"/index.jsp");
			return;
		}
		
		Service service = Service.getInstance();
		String path=null;
		
		if(job.equals("Professor")) {
			boolean result = service.proLogin(id,pwd);
			if(result) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				path="/proMenu.jsp";
			}else {
				request.setAttribute("error", "NO ID");
				path="/index.jsp";
			}
			HttpUtil.forward(request, response, path);
			
		} else if(job.equals("Student")) {
			boolean result = service.stuLogin(id,pwd);
			if(result) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				path="/stuMenu.jsp";
			}else {
				request.setAttribute("error", "NO ID");
				path="/index.jsp";
			}
			HttpUtil.forward(request, response, path);
		}
	}
}

