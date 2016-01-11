package Student_Util;

import java.util.List;

public interface Student_Manege {
	//增加
	public boolean add(String number,String name,int age);
	//删除
	public boolean kill(String number);
	//修改
	public boolean reWrite(String number,String name,int age);
	//查询
	public Student checkStudent(String number);
	public List<Student> checkStudents(String name);
}
