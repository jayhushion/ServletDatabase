package hrb.servlet;

import java.util.List;

public interface StudentDAO {
	
	/**
	 * �����ݱ�����һ����¼
	 * @param stu
	 */
	public void save(Student stu);
	
	
	/**
	 * ����һ����¼
	 * @param stu
	 */
	public void update(Student stu);
	
	/**
	 * ɾ��һ����¼
	 * @param stu
	 */
	public void delete(Student stu);
	
	
	/**
	 * ����������ѯһ����¼
	 * @param id
	 * @return
	 * 
	 */
	
	public Student queryById(String id) throws Exception;
	
	/**
	 * ��ѯ���м�¼
	 * @return
	 * 
	 */
	public List<Student> queryAll() throws Exception;

}
