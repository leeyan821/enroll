package login.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.service.Service;
import login.vo.SubjectVO;

public class StushowContoller implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		Service service = Service.getInstance();
		ArrayList<SubjectVO> list = service.stuShow(id);
		
		request.setAttribute("list", list);
		HttpUtil.forward(request, response, "/result/stuShowOutput.jsp");
	}

}
