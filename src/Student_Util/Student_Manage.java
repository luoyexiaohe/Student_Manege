package Student_Util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import dom4jutil.Dom4jUtil;

public class Student_Manage implements Student_Manege{
	
	String path = "src/Student.xml";
	//1.��ȡdocument����
	Document document = Dom4jUtil.getParse(path);
	
	//���
	public boolean add(String number,String name, int age) {
		/*
		 * 1.��ȡdocument����
		 * 2.�жϸ�ѧ���Ƿ���ڣ������ڣ�4-6	���ڣ�3��
		 * 
		 * 3.��ӡ����ѧ���Ѵ��ڡ���������
		 * 
		 * 4.���δ�����ǩ
		 * 5.����ǩ��ӵ�document������
		 * 
		 * 6.��д
		 * 
		 * 
		 * */


		//2.�жϸ�ѧ���Ƿ��Ѵ���
		List<Node> list =document.selectNodes("//number");
		for (Node node : list) {
			if(node.getText().equals(number)){
				System.out.println("��ѧ���Ѿ�����");
				return false;
			}
		}
		//4.���δ�����ǩ
		Element stu = DocumentHelper.createElement("stu");
		Element number_stu = stu.addElement("number");
		number_stu.setText(number);
		Element name_stu = stu.addElement("name");
		name_stu.setText(name);
		Element age_stu = stu.addElement("age");
		age_stu.setText(new Integer(age).toString());
		//����ǩ��ӵ�document������
		Element root = document.getRootElement();
		root.add(stu);
		
		//��д
		Dom4jUtil.writeBack(path, document);
		return true;

	}

	@Override
	public boolean kill(String number) {
		/*
		 * 1.��ȡdocument����
		 * 2.��ȡĿ���ǩ
		 * 3.�ж��Ƿ�Ϊnull�������ϣ�4		���ϣ�5-6��
		 * 
		 * 4.��ӡ��δ�ҵ�������������������
		 * 
		 * 5.ɾ��Ŀ��
		 * 
		 * 6.��д
		 * */

		//2.��ȡĿ���ǩ
		Node node = document.selectSingleNode("//number[text()='"+number+"']");
		//3.�ж��Ƿ��������
		if(node==null){
			//4.��ӡ��δ�ҵ���������������
			System.out.println("δ�ҵ�����");
			return false;
		}
		//5.ɾ��Ŀ��
		Element child = node.getParent();
		child.getParent().remove(child);
		//6.��д
		Dom4jUtil.writeBack(path, document);
		
		return true;
	}

	@Override
	public boolean reWrite(String number, String name, int age) {
		/*
		 * 1.��ȡdocument����
		 * 2.��ȡĿ���ǩ
		 * 3.�ж�Ŀ���ǩ�Ƿ���ڣ������ڣ�4 	���ڣ�5-6��
		 * 
		 * 4.��ӡ�����������ڡ���������
		 * 
		 * 5.�����滻Ŀ���ǩ
		 * 
		 * 6.��д
		 * */
		
		//2.��ȡĿ���ǩ
		Node node = document.selectSingleNode("//number[text()='"+number+"']");
		//3.�ж�Ŀ���ǩ�Ƿ����
		if(node==null){
			//4.��ӡ�����������ڡ���������
			System.out.println("����������");
			return false;
		}
		
		//5.�����滻Ŀ���ǩ
		Element element = node.getParent();
		element.element("name").setText(name);
		element.element("age").setText(new Integer(age).toString());
		
		//6.��д
		Dom4jUtil.writeBack(path, document);
		return true;
	}

	@Override
	public Student checkStudent(String number) {
		/*
		 * 1.��ȡdocument����
		 * 2.��ȡĿ���ǩ
		 * 3.�ж�Ŀ���ǩ�Ƿ���ڣ������ڣ�4 	���ڣ�5��
		 * 
		 * 4.��ӡ�����������ڡ���������
		 * 
		 * 5.��ȡ����ѧ�����������ԣ�����ʼ��ѧ������
		 * 6.���øö����show����������һ��ѧ������
		 * 
		 */

		//2.��ȡĿ���ǩ
		Node node = document.selectSingleNode("//number[text()='"+number+"']");
		//3.�ж�Ŀ���ǩ�Ƿ����
		if(node==null){
			//4.��ӡ�����������ڡ���������
			System.out.println("����������");
			return null;
		}
		
		//5.��ȡ����ѧ�����������ԣ�����ʼ��ѧ������
		String name = node.getParent().elementText("name"); 
		int age = new Integer(node.getParent().elementText("age"));
//		int age = 150;
		
		Student student;
		try {
			student = new Student(number, name, age);
			
			//6.���øö����show����������һ��ѧ������
			student.show();
			return student;			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());//��ӡ������Ϣ
		}

		return null;
	}

	@Override
	public List<Student> checkStudents(String name) {
		/*
		 * 1.��ȡdocument����
		 * 2.��ȡĿ���ǩ
		 * 3.�ж�Ŀ���ǩ�Ƿ���ڣ������ڣ�4 	���ڣ�5��
		 * 
		 * 4.��ӡ�����������ڡ���������
		 * 
		 * 5.��ȡ����ѧ�����������ԣ�����ʼ��ѧ������
		 * 6.���øö����show���������һ��ѧ������
		 * 
		 * 7.����ѧ��List����
		 */
		
		//2.��ȡĿ���ǩ
		List<Node> list = document.selectNodes("//name[text()='"+name+"']");
		//3.�ж�Ŀ���ǩ�Ƿ����
		if(list.isEmpty()){
			//4.��ӡ�����������ڡ���������
			System.out.println("δ�ҵ���ѧ��");
			return null;
		}
		
		//5.��ȡ���������������ԣ�����ʼ��ѧ������
		List<Student> students = new ArrayList();
		for (Node node : list) {
			String number = node.getParent().elementText("number"); 
			int age = new Integer(node.getParent().elementText("age"));
			
			Student student;
			try {
				student = new Student(number, name, age);
				
				//6.���øö����show���������һ��ѧ������
				student.show();
				students.add(student);		
				
			} catch (Exception e) {
				System.out.println(e.getMessage());//��ӡ������Ϣ
			}
		}
		//7.����ѧ������List����
		return students;
	}



}
