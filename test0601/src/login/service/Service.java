package login.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import login.dao.MemberDAO;
import login.vo.SubjectVO;

public class Service {
	private static Service service = new Service();
	private Service() {}
	public MemberDAO dao = MemberDAO.getInstance();
	public static Service getInstance() {
		return service;
	}
	public boolean proLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		return dao.proLogin(id,pwd);
	}
	public boolean stuLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		return dao.stuLogin(id,pwd);
	}
	public SubjectVO search(String id) {
		// TODO Auto-generated method stub
		return dao.search(id);
	}
	public boolean enroll(String id, String stu) {
		// TODO Auto-generated method stub
		return dao.enroll(id, stu);
	}
	public void subEroll(SubjectVO member,String pro) {
		// TODO Auto-generated method stub
		dao.subEnroll(member,pro);
	}
	public ArrayList<SubjectVO> show(String id) {
		// TODO Auto-generated method stub
		return dao.show(id);
	}
	public ArrayList<SubjectVO> stuShow(String id) {
		// TODO Auto-generated method stub
		return dao.stuShow(id);
	}
	public boolean checkEnroll(String id, String stu) {
		// TODO Auto-generated method stub
		return dao.checkEnroll(id, stu);
	}
	public ArrayList<SubjectVO> chShow(String id) {
		// TODO Auto-generated method stub
		return dao.chShow(id);
	}
	public ArrayList<SubjectVO> memberShow(String id) {
		// TODO Auto-generated method stub
		return dao.memberShow(id);
	}
	public void delete(String id, String stu, int count) {
		// TODO Auto-generated method stub
		dao.delete(id,stu,count);
	}	
}
