package hrb.servlet;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.*;

public class StudentDAOImpl implements StudentDAO {

	private static String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=lb";
	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String username="sa";
	private static String password="123456";
	
	
	public static void main(String[] args) {
		Connection c = new StudentDAOImpl().openConnection();
		System.out.println(c);
	}
	
	private Connection openConnection(){
		Connection conn = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	private String generateId(int length){
		Random numGen = new Random();;

		 char[] numbers = ("0123456789").toCharArray();;
		
			  if (length < 1) {
			   return null;
			  }
			  char[] randBuffer = new char[length];
			  for (int i = 0; i < randBuffer.length; i++) {
			   randBuffer[i] = numbers[numGen.nextInt(9)];
			  }
			  return new String(randBuffer);
			 

	}
	
	public void save(Student stu) {
		// TODO Auto-generated method stub
		Connection conn = openConnection();
		if(conn != null){			
			try{
				String sql  = "insert into student(studentid, studentname, studentage, "
						+ "hobbies, school, studentgender)"+
									"values(?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, generateId(5));
				
				pst.setString(2,stu.getStudentName());
				pst.setInt(3,stu.getStudentAge());
				pst.setString(4, stu.getHobbies());
				pst.setString(5, stu.getSchool());
				pst.setString(6, stu.getStudentGender());
				pst.executeUpdate();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	

	@Override
	public void update(Student stu) {
		// TODO Auto-generated method stub
		Connection conn = openConnection();
		if(conn != null){			
			try{
				
				String sql  = "UPDATE student SET studentname=?,studentage=?, hobbies=?, school=?, studentgender=? WHERE studentid=?";
				PreparedStatement pst = conn.prepareStatement(sql);
//				
		
				pst.setString(1,stu.getStudentName());
				pst.setInt(2,stu.getStudentAge());
				pst.setString(3, stu.getHobbies());
				pst.setString(4, stu.getSchool());
				pst.setString(5, stu.getStudentGender());
				pst.setString(6, stu.getStudentId());
				pst.executeUpdate();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(Student stu) {
		// TODO Auto-generated method stub
		Connection conn = openConnection();
		if(conn != null){			
			try{
				String sql = "DELETE FROM student WHERE studentid = ? ";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, stu.getStudentId());
				pst.executeUpdate();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Student queryById(String id)  {
		// TODO Auto-generated method stub
		Connection conn = openConnection();
		ResultSet rs = null;
		PreparedStatement pst = null;	
			try{
				String sql = "SELECT * FROM student WHERE studentid = ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				rs=pst.executeQuery();
				if (rs.next())
				{
					Student stu =new Student();
					stu.setStudentId(rs.getString("studentid"));
					stu.setStudentName(rs.getString("studentname"));
					stu.setStudentAge(rs.getInt("studentage"));
					stu.setStudentGender(rs.getString("studentgender"));
					stu.setHobbies(rs.getString("hobbies"));
					stu.setSchool(rs.getString("school"));
					return stu;
					
				}
				else
				{
					return null;
				}
				
			} catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	

	@Override
	public List<Student> queryAll() {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM student";
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			conn = openConnection();
			preStmt = conn.prepareStatement(sql);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				//设置数据库中表参数 否则报错java.sql.SQLException: Column 'id' not found.
				Student stu =new Student();
				stu.setStudentId(rs.getString("studentid"));
				stu.setStudentName(rs.getString("studentname"));
				stu.setStudentAge(rs.getInt("studentage"));
				stu.setStudentGender(rs.getString("studentgender"));
				stu.setHobbies(rs.getString("hobbies"));
				stu.setSchool(rs.getString("school"));
				
				list.add(stu);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}

			return list;
	}
	}
	


