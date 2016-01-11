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
	//1.获取document对象
	Document document = Dom4jUtil.getParse(path);
	
	//添加
	public boolean add(String number,String name, int age) {
		/*
		 * 1.获取document对象
		 * 2.判断该学生是否存在（不存在：4-6	存在：3）
		 * 
		 * 3.打印“该学生已存在”，并结束
		 * 
		 * 4.依次创建标签
		 * 5.将标签添加到document对象中
		 * 
		 * 6.回写
		 * 
		 * 
		 * */


		//2.判断该学生是否已存在
		List<Node> list =document.selectNodes("//number");
		for (Node node : list) {
			if(node.getText().equals(number)){
				System.out.println("该学生已经存在");
				return false;
			}
		}
		//4.依次创建标签
		Element stu = DocumentHelper.createElement("stu");
		Element number_stu = stu.addElement("number");
		number_stu.setText(number);
		Element name_stu = stu.addElement("name");
		name_stu.setText(name);
		Element age_stu = stu.addElement("age");
		age_stu.setText(new Integer(age).toString());
		//将标签添加到document对象中
		Element root = document.getRootElement();
		root.add(stu);
		
		//回写
		Dom4jUtil.writeBack(path, document);
		return true;

	}

	@Override
	public boolean kill(String number) {
		/*
		 * 1.获取document对象
		 * 2.获取目标标签
		 * 3.判断是否为null（不符合：4		符合：5-6）
		 * 
		 * 4.打印“未找到该生。”，并结束。
		 * 
		 * 5.删除目标
		 * 
		 * 6.回写
		 * */

		//2.获取目标标签
		Node node = document.selectSingleNode("//number[text()='"+number+"']");
		//3.判断是否符合条件
		if(node==null){
			//4.打印“未找到该生”，并结束
			System.out.println("未找到该生");
			return false;
		}
		//5.删除目标
		Element child = node.getParent();
		child.getParent().remove(child);
		//6.回写
		Dom4jUtil.writeBack(path, document);
		
		return true;
	}

	@Override
	public boolean reWrite(String number, String name, int age) {
		/*
		 * 1.获取document对象
		 * 2.获取目标标签
		 * 3.判断目标标签是否存在（不存在：4 	存在：5-6）
		 * 
		 * 4.打印“该生不存在”，并结束
		 * 
		 * 5.依次替换目标标签
		 * 
		 * 6.回写
		 * */
		
		//2.获取目标标签
		Node node = document.selectSingleNode("//number[text()='"+number+"']");
		//3.判断目标标签是否存在
		if(node==null){
			//4.打印“该生不存在”，并结束
			System.out.println("该生不存在");
			return false;
		}
		
		//5.依次替换目标标签
		Element element = node.getParent();
		element.element("name").setText(name);
		element.element("age").setText(new Integer(age).toString());
		
		//6.回写
		Dom4jUtil.writeBack(path, document);
		return true;
	}

	@Override
	public Student checkStudent(String number) {
		/*
		 * 1.获取document对象
		 * 2.获取目标标签
		 * 3.判断目标标签是否存在（不存在：4 	存在：5）
		 * 
		 * 4.打印“该生不存在”，并结束
		 * 
		 * 5.获取到该学生的所有属性，并初始化学生对象
		 * 6.调用该对象的show方法并返回一个学生对象。
		 * 
		 */

		//2.获取目标标签
		Node node = document.selectSingleNode("//number[text()='"+number+"']");
		//3.判断目标标签是否存在
		if(node==null){
			//4.打印“该生不存在”，并结束
			System.out.println("该生不存在");
			return null;
		}
		
		//5.获取到该学生的所有属性，并初始化学生对象
		String name = node.getParent().elementText("name"); 
		int age = new Integer(node.getParent().elementText("age"));
//		int age = 150;
		
		Student student;
		try {
			student = new Student(number, name, age);
			
			//6.调用该对象的show方法并返回一个学生对象
			student.show();
			return student;			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());//打印错误信息
		}

		return null;
	}

	@Override
	public List<Student> checkStudents(String name) {
		/*
		 * 1.获取document对象
		 * 2.获取目标标签
		 * 3.判断目标标签是否存在（不存在：4 	存在：5）
		 * 
		 * 4.打印“该生不存在”，并结束
		 * 
		 * 5.获取到该学生的所有属性，并初始化学生对象
		 * 6.调用该对象的show方法并添加一个学生对象。
		 * 
		 * 7.返回学生List集合
		 */
		
		//2.获取目标标签
		List<Node> list = document.selectNodes("//name[text()='"+name+"']");
		//3.判断目标标签是否存在
		if(list.isEmpty()){
			//4.打印“该生不存在”，并结束
			System.out.println("未找到该学生");
			return null;
		}
		
		//5.获取到该生的所有属性，并初始化学生对象
		List<Student> students = new ArrayList();
		for (Node node : list) {
			String number = node.getParent().elementText("number"); 
			int age = new Integer(node.getParent().elementText("age"));
			
			Student student;
			try {
				student = new Student(number, name, age);
				
				//6.调用该对象的show方法并添加一个学生对象
				student.show();
				students.add(student);		
				
			} catch (Exception e) {
				System.out.println(e.getMessage());//打印错误信息
			}
		}
		//7.返回学生对象List集合
		return students;
	}



}
