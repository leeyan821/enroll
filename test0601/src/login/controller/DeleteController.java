package login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.service.Service;

public class DeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("uid");
		int count = Integer.parseInt(request.getParameter("cnt"));
		
		HttpSession session = request.getSession();
		String stu = (String)session.getAttribute("id");
		
		Service service = Service.getInstance();
		service.delete(id, stu, count);
			
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/result/deleteOutput.jsp");
	}

}
