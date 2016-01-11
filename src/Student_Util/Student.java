package Student_Util;

public class Student {
	private String number;
	private String name;
	private int age;

	public Student(String number,String name,int age) throws Exception{
		if(age<0 || age>100){
			throw new RuntimeException("年龄错误，未初始化成功");
		}
		this.number=number;
		this.name=name;
		this.age=age;
	}
	public void show() {
		System.out.println("学号："+this.number);
		System.out.println("名字："+this.name);
		System.out.println("年龄："+this.age);
	}
}
