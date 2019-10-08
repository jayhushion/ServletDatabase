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
//		System.out.println("���յ����Ĵ���������");
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
		response.setContentType("text/html;charset=utf-8");//���ñ����ʽ���Է�ǰ��ҳ�������������
		PrintWriter printWriter1 = response.getWriter();//���������
		printWriter1.println("ѧ��ID");
		printWriter1.println(dao.queryById(id).getStudentId());
		printWriter1.println("<p>");
		printWriter1.println("ѧ������");
		printWriter1.println(dao.queryById(id).getStudentName());
		printWriter1.println("<p>");
		printWriter1.println("ѧ������");
		printWriter1.println(dao.queryById(id).getStudentAge());
		printWriter1.println("<p>");
		printWriter1.println("�Ա�");
if(dao.queryById(id).getStudentGender().compareTo("0")==9)
			
			printWriter1.println("��");
			else
				printWriter1.println("Ů");
		printWriter1.println("<p>");
		printWriter1.println("ѧ������");
		printWriter1.println(dao.queryById(id).getHobbies());
		printWriter1.println("<p>");
		printWriter1.println("ѧ������ѧԺ");
		printWriter1.println(dao.queryById(id).getSchool());
		printWriter1.println("<p>");
		responseClient(response);
		}
		if(option.compareTo("5")==0)
		{	int size=dao.queryAll().size();
		for(int i=0;i<size;i++)
			{
			response.setContentType("text/html;charset=utf-8");//���ñ����ʽ���Է�ǰ��ҳ�������������
			PrintWriter printWriter1 = response.getWriter();//���������
			printWriter1.println("ѧ��ID");
			printWriter1.println(dao.queryAll().get(i).getStudentId());
			printWriter1.println("<p>");
			
			printWriter1.println("ѧ������");
			printWriter1.println(dao.queryAll().get(i).getStudentName());
			printWriter1.println("<p>");
			printWriter1.println("ѧ������");
			printWriter1.println(dao.queryAll().get(i).getStudentAge());
			printWriter1.println("<p>");
			printWriter1.println("�Ա�");
			if(dao.queryAll().get(i).getStudentGender().compareTo("0")==9)
			
			printWriter1.println("��");
			else
				printWriter1.println("Ů");
			
			printWriter1.println("<p>");
			printWriter1.println("ѧ������");
			printWriter1.println(dao.queryAll().get(i).getHobbies());
			printWriter1.println("<p>");
			printWriter1.println("ѧ������ѧԺ");
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
		pw.println("<title>��Ӧҳ��</title>");
		pw.println("</head>");

		pw.println("<body>");
		pw.println("Request received���������ѽ��գ� ");
	
		pw.println("</body>");
		pw.println("</html>");
	}
	
	

}
