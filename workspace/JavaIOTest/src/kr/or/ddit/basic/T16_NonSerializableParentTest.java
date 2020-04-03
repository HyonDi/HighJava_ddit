package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
  부모 클래스가 Serializble 인터페이스를 구현하고 있지 않을 경우
  부모객체의 필드값 처리 방법
  
	  1. 부모클래스가 Serializable 인터페이스를 구현하도록 해야 한다.
	  2. 자식클래스에 writeObject()와 readObject()메서드를 이용하여
	  		부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
 */

/**
 * api extends 한 클래스에 Serializble 필요한 경우
 * @author PC-16
 *
 */
public class T16_NonSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializaTest.bin");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		// 차일드 객체
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child); // 직렬화 . 이때 private으로 되어있는 writeObject이름의 메서드를 자동실행한다. 
		oos.flush(); // 생략가능
		oos.close();
		
		FileInputStream fis = new FileInputStream("d:/D_Other/nonSerializaTest.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Child child2 = (Child) ois.readObject(); // 역직렬화
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		ois.close();
	}
}

/**
 * Serializable을 구현하지 않은 부모 클래스.
 * @author PC-16
 *
 */
class Parent {
	private String parentName;
	
	public String getParentName() {
		return parentName;
	}
	
	public void setParentName(String parentName) {
		 this.parentName = parentName;
	}
}


/**
 * 직렬화를 구현한 자식 클래스.
 * @author PC-16
 *
 */
class Child extends Parent implements Serializable {
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화 될 때 자동으로 호출 됨.
	 * (접근 제한자가 private가 아니면 자동호출되지 않음)
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		// ObjectOutputStream 객체의 메서드를 이용하여 부모객체 필드값 처리.
		out.writeUTF(getParentName()); // 부모이름으로 write하면 디스크에 저장된다.
		out.defaultWriteObject(); 
	}
	
	
	/**
	 * 역직렬화가 될때 자동으로 호출 됨.
	 * (접근 제한자가 private가 아니면 자동 호출되지 않음.)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		// ObjectInputStream객체의 메서드를 이용하여 부모객체 필드값 처리
		setParentName(in.readUTF());
		in.defaultReadObject();
	}
	
}