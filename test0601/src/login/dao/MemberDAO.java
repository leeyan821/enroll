package login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import login.vo.SubjectVO;

public class MemberDAO {
	private static MemberDAO dao = new MemberDAO();
	public MemberDAO() {}
	public static MemberDAO getInstance() {
		return dao;
	}
	public Connection con() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","say17405*");
		}catch(Exception e) {
			System.out.println("connect error"+e);
		}
		return conn;
	}
	public void close(Connection conn, PreparedStatement pstmt) {
		if(conn!=null) {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.print("Conn close error"+e);
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.print("Pstmt close error"+e);
			}
		}
	}	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null)
		{
			try {
				rs.close();
			}catch(Exception e) {
				System.out.print("rs close error"+e);
			}
		}
		close(conn,pstmt);
	}
	
	public boolean proLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = con();
			pstmt=conn.prepareStatement("select * from prof where id = ? and pwd = ?;");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next())
				result = true;
			else
				result = false;
		}catch(Exception e)
		{
			System.out.print("login error"+e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}		
		return result;
	}
	public boolean stuLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = con();
			pstmt=conn.prepareStatement("select * from student where id = ? and pwd = ?;");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next())
				result = true;
			else
				result = false;
		}catch(Exception e)
		{
			System.out.print("login error"+e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}		
		return result;
	}
	public SubjectVO search(String id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubjectVO member = null;
		try
		{
			conn = con();
			pstmt = conn.prepareStatement("select * from sub where id=?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				member = new SubjectVO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setCount(Integer.parseInt(rs.getString(4)));
			}
		}catch(Exception e) {
			System.out.print("Search error"+e);
		}finally
		{
			close(conn,pstmt,rs);
		}
		return member;
	}
	public boolean enroll(String id,String stu) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ucount = 0;
		int max = 0;
		boolean result = true;
		try
		{
			conn = con();
			pstmt = conn.prepareStatement("select count,max from sub where id = ?;");
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				ucount = rs.getInt(1);
				max = rs.getInt(2);
			}
			ucount = ucount + 1;
			if(ucount>max) {				
				result = false;
			}else {
				pstmt = conn.prepareStatement("update sub set count=? where id=?;");
				pstmt.setString(1, ucount+"");
				pstmt.setString(2, id);
				pstmt.executeUpdate();	
				
				pstmt = conn.prepareStatement("insert into enroll values(?,?);");
				pstmt.setString(1, id);
				pstmt.setString(2, stu);
				pstmt.executeUpdate();
			}
			
		}catch(Exception e)
		{
			System.out.print("enroll error"+e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}
		return result;
	}
	public void subEnroll(SubjectVO member, String pro) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try
		{
			conn = con();
			pstmt = conn.prepareStatement("insert into sub values(?,?,?,?,?);");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getMax()+"");
			pstmt.setString(4, cnt+"");
			pstmt.setString(5, pro);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.print("enroll"+e);
		}finally {
			close(conn, pstmt);
		}
	}
	public ArrayList<SubjectVO> show(String id) {
		// TODO Auto-generated method stub
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		Connection conn = null;
		ResultSet rs = null;	
		PreparedStatement pstmt = null;
			
		SubjectVO member = null;
		try {
			conn = con();
			pstmt = conn.prepareStatement("select * from sub where prof=?;");
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new SubjectVO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setMax(Integer.parseInt(rs.getString(3)));
				member.setCount(Integer.parseInt(rs.getString(4)));
				list.add(member);			
			}
		}catch(SQLException e) {
			System.out.println("show error"+e);
		}finally {	
			close(conn,pstmt,rs);
		}
		return list;
	}
	public ArrayList<SubjectVO> stuShow(String id) {
		// TODO Auto-generated method stub
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		Connection conn = null;
		ResultSet rs = null;	
		PreparedStatement pstmt = null;
			
		SubjectVO member = null;
		try {
			conn = con();
			pstmt = conn.prepareStatement("select b.id, b.name from enroll a, sub b where a.subject = b.id and a.student = ?;");
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new SubjectVO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				list.add(member);
			}
		}catch(SQLException e) {
			System.out.println("stuShow error"+e);
		}finally {	
			close(conn,pstmt,rs);
		}
		return list;
	}
	public boolean checkEnroll(String id, String stu) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = true;
		try
		{
			conn = con();
			pstmt = conn.prepareStatement("select * from enroll where student=? and subject=?;");
			pstmt.setString(1, stu);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = false;
			}
		}catch(Exception e)
		{
			System.out.print("check enroll error"+e);
		}
		finally
		{
			close(conn,pstmt,rs);
		}
		return result;
	}
	public ArrayList<SubjectVO> chShow(String id) {
		// TODO Auto-generated method stub
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		Connection conn = null;
		ResultSet rs = null;	
		PreparedStatement pstmt = null;
			
		SubjectVO member = null;
		try {
			conn = con();
			pstmt = conn.prepareStatement("select id,name from student,enroll where enroll.subject = ? and enroll.student=student.id;");
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new SubjectVO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				list.add(member);			
			}
		}catch(SQLException e) {
			System.out.println("chShow error"+e);
		}finally {	
			close(conn,pstmt,rs);
		}
		return list;
	}
	public ArrayList<SubjectVO> memberShow(String id) {
		// TODO Auto-generated method stub
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		Connection conn = null;
		ResultSet rs = null;	
		PreparedStatement pstmt = null;
			
		SubjectVO member = null;
		try {
			conn = con();
			pstmt = conn.prepareStatement("select name,count from sub where id=?;");
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new SubjectVO();
				member.setName(rs.getString(1));
				member.setCount(Integer.parseInt(rs.getString(2)));
				list.add(member);			
			}
		}catch(SQLException e) {
			System.out.println("member Show error"+e);
		}finally {	
			close(conn,pstmt,rs);
		}
		return list;
	}
	public void delete(String id, String stu,int count) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		count = count - 1;
		try
		{
			conn = con();
			pstmt = conn.prepareStatement("delete from enroll where subject= ? and student = ?;");
			pstmt.setString(1,id);
			pstmt.setString(2, stu);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("update sub set count=? where id=?;");
			pstmt.setString(1, count+"");
			pstmt.setString(2, id);
			pstmt.executeUpdate();	
		}catch(Exception e) {
			System.out.print("delete"+e);
		}finally {
			close(conn, pstmt);
		}
	}	
}
