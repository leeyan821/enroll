package login.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.service.Service;
import login.vo.SubjectVO;

public class MemberCheckController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		
		Service service = Service.getInstance();
		ArrayList<SubjectVO> list = service.chShow(id);
		ArrayList<SubjectVO> list2 = service.memberShow(id);

		request.setAttribute("list2", list2);
		request.setAttribute("list", list);
		HttpUtil.forward(request, response, "/result/chShowOutput.jsp");
	}

}
