package login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.service.Service;

public class EnrollController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("uid");

		HttpSession session = request.getSession();
		String stu = (String)session.getAttribute("id");
		
		Service service = Service.getInstance();
		boolean check = service.checkEnroll(id,stu);
		
		if(!check) {
			request.setAttribute("error", id+"는 이미 신청된 과목입니다.");
			HttpUtil.forward(request, response, "/result/enrollOutput.jsp");
			return;
		}
		else {			
			boolean result = service.enroll(id,stu);
			
			request.setAttribute("id", id);
			if(!result)
				request.setAttribute("error", id+" 신청 실패 | 인원 초과 입니다.");
			else 
				request.setAttribute("error", id+" 신청 완료");

			HttpUtil.forward(request, response, "/result/enrollOutput.jsp");
		}
	}
}
