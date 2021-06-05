package login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.service.Service;
import login.vo.SubjectVO;


public class SearchController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String tp = request.getParameter("tp");
		String path = null;
		Service s = Service.getInstance();
		
		if(tp.equals("search")){
			path="/search.jsp";
			
			if(id.isEmpty()) {
				request.setAttribute("error", "Enter ID");
				HttpUtil.forward(request, response, path);
				return;
			}
			/*SubjectVO member = s.search(id);
			
			if(member == null) request.setAttribute("result", "No ID");		
			request.setAttribute("member", member);*/
			
		}else if(tp.equals("delete")) {
			path="/delete.jsp";
			
			if(id.isEmpty()) {
				request.setAttribute("error", "Enter ID");
				HttpUtil.forward(request, response, path);
				return;
			}
			HttpSession session = request.getSession();
			String stu = (String)session.getAttribute("id");
			boolean result = s.checkEnroll(id, stu);
			if(result) {
				request.setAttribute("error", "신청된 과목이 아닙니다.");
				HttpUtil.forward(request, response, path);
				return;
			}
		}		
		SubjectVO member = s.search(id);
		
		if(member == null) request.setAttribute("result", "No ID");
		request.setAttribute("member", member);
		HttpUtil.forward(request, response, path);
	}

}
