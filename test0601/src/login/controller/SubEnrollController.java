package login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.service.Service;
import login.vo.SubjectVO;

public class SubEnrollController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String c = request.getParameter("cnt");
	
		if(id.isEmpty() || title.isEmpty() || c.isEmpty()) {
			request.setAttribute("error", "Enter All");
			HttpUtil.forward(request, response, "/subEnroll.jsp");
			return;
		}	
		Service service = Service.getInstance();
		SubjectVO member2 = service.search(id);
		if(member2!=null) {
			request.setAttribute("error", "subject is alreay exist.");
			HttpUtil.forward(request, response, "/subEnroll.jsp");
			return;
		}
		
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		HttpSession session = request.getSession();
		String pro = (String)session.getAttribute("id");
				
		SubjectVO member = new SubjectVO();
		member.setId(id);
		member.setName(title);
		member.setMax(cnt);
		
		service.subEroll(member,pro);		
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/result/subEnrolltOutput.jsp");
	}
}
