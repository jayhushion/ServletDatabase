package hrb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("接收到表单的处理请求了");
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("option");
		String id=request.getParameter("stuid");
		String name = request.getParameter("stuname");
		String ageStr = request.getParameter("stuage");
		int age = Integer.parseInt(ageStr);		
		String genderStr = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobbies");
		String school = request.getParameter("school");
		
		Student stu = new Student();
		stu.setStudentName(name);
		stu.setStudentAge(age);
		stu.setStudentGender(genderStr);
		stu.setSchool(school);
		stu.setStudentId(id);
		
		String hobbiesstr = "";
		for(String h : hobbies){
			hobbiesstr = hobbiesstr + h+ "$";
		}
		stu.setHobbies(hobbiesstr);
		
		StudentDAOImpl dao = new StudentDAOImpl();
		
		if(option.compareTo("1")==0)
		{dao.save(stu);
		responseClient(response);
		}
		if(option.compareTo("2")==0)
		{dao.update(stu);
		responseClient(response);
		}
		if(option.compareTo("3")==0)
		{dao.delete(stu);
		responseClient(response);
		}
		if(option.compareTo("4")==0)
		{
		response.setContentType("text/html;charset=utf-8");//设置编码格式，以防前端页面出现中文乱码
		PrintWriter printWriter1 = response.getWriter();//创建输出流
		printWriter1.println("学生ID");
		printWriter1.println(dao.queryById(id).getStudentId());
		printWriter1.println("<p>");
		printWriter1.println("学生姓名");
		printWriter1.println(dao.queryById(id).getStudentName());
		printWriter1.println("<p>");
		printWriter1.println("学生年龄");
		printWriter1.println(dao.queryById(id).getStudentAge());
		printWriter1.println("<p>");
		printWriter1.println("性别");
if(dao.queryById(id).getStudentGender().compareTo("0")==9)
			
			printWriter1.println("男");
			else
				printWriter1.println("女");
		printWriter1.println("<p>");
		printWriter1.println("学生爱好");
		printWriter1.println(dao.queryById(id).getHobbies());
		printWriter1.println("<p>");
		printWriter1.println("学生所属学院");
		printWriter1.println(dao.queryById(id).getSchool());
		printWriter1.println("<p>");
		responseClient(response);
		}
		if(option.compareTo("5")==0)
		{	int size=dao.queryAll().size();
		for(int i=0;i<size;i++)
			{
			response.setContentType("text/html;charset=utf-8");//设置编码格式，以防前端页面出现中文乱码
			PrintWriter printWriter1 = response.getWriter();//创建输出流
			printWriter1.println("学生ID");
			printWriter1.println(dao.queryAll().get(i).getStudentId());
			printWriter1.println("<p>");
			
			printWriter1.println("学生姓名");
			printWriter1.println(dao.queryAll().get(i).getStudentName());
			printWriter1.println("<p>");
			printWriter1.println("学生年龄");
			printWriter1.println(dao.queryAll().get(i).getStudentAge());
			printWriter1.println("<p>");
			printWriter1.println("性别");
			if(dao.queryAll().get(i).getStudentGender().compareTo("0")==9)
			
			printWriter1.println("男");
			else
				printWriter1.println("女");
			
			printWriter1.println("<p>");
			printWriter1.println("学生爱好");
			printWriter1.println(dao.queryAll().get(i).getHobbies());
			printWriter1.println("<p>");
			printWriter1.println("学生所属学院");
			printWriter1.println(dao.queryAll().get(i).getSchool());
			printWriter1.println("<p>");
			printWriter1.println("<br/>");
			printWriter1.println("<br/>");
			
			}
		
		responseClient(response);
		}
		
		
		
	}
	
	
	private void responseClient(HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>响应页面</title>");
		pw.println("</head>");

		pw.println("<body>");
		pw.println("Request received（表单请求已接收） ");
	
		pw.println("</body>");
		pw.println("</html>");
	}
	
	

}
