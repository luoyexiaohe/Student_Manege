package Student_Util;

public class Student {
	private String number;
	private String name;
	private int age;

	public Student(String number,String name,int age) throws Exception{
		if(age<0 || age>100){
			throw new RuntimeException("�������δ��ʼ���ɹ�");
		}
		this.number=number;
		this.name=name;
		this.age=age;
	}
	public void show() {
		System.out.println("ѧ�ţ�"+this.number);
		System.out.println("���֣�"+this.name);
		System.out.println("���䣺"+this.age);
	}
}
