package hrb.servlet;

import java.util.List;

public interface StudentDAO {
	
	/**
	 * 向数据表新增一条记录
	 * @param stu
	 */
	public void save(Student stu);
	
	
	/**
	 * 更新一条记录
	 * @param stu
	 */
	public void update(Student stu);
	
	/**
	 * 删除一条记录
	 * @param stu
	 */
	public void delete(Student stu);
	
	
	/**
	 * 根据主键查询一条记录
	 * @param id
	 * @return
	 * 
	 */
	
	public Student queryById(String id) throws Exception;
	
	/**
	 * 查询所有记录
	 * @return
	 * 
	 */
	public List<Student> queryAll() throws Exception;

}
