package Student_Util;

import java.util.List;

public interface Student_Manege {
	//����
	public boolean add(String number,String name,int age);
	//ɾ��
	public boolean kill(String number);
	//�޸�
	public boolean reWrite(String number,String name,int age);
	//��ѯ
	public Student checkStudent(String number);
	public List<Student> checkStudents(String name);
}
