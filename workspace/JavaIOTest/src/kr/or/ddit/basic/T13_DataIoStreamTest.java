package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T13_DataIoStreamTest {
	public static void main(String[] args) {
		try {FileOutputStream fout = // 파일 저장할때 쓰는 것.
				new FileOutputStream("d:/D_Other/test.dat");
		
		// DataOutputStream 은 출력용 데이터를 자료형에 맞게 출력해 준다.
		DataOutputStream dout = new DataOutputStream(fout);
		
		dout.writeUTF("홍길동"); // 문자열 데이터 출력(UTF-8)
		dout.writeInt(17); // 정수형 데이터 출력
		dout.writeFloat(3.14f); // 실수형(Float) 으로 출력
		dout.writeDouble(3.14); // 실수형(Double)으로 출력
		dout.writeBoolean(true); // 논리형으로 출력
		
		System.out.println("출력 완료...");
		//=======================================================
		// 출력한 자료 읽어오기
		FileInputStream fin = new FileInputStream("d:/D_Other/test.dat"); // 기본기능 
		DataInputStream din = new DataInputStream(fin); // 데이터사이즈에맞게 끊어읽어라. (보조스트림)
		
		System.out.println("문자열 자료 : " + din.readUTF());
		System.out.println("정수형 자료 : " + din.readInt());
		System.out.println("실수형(Float) 자료 : " + din.readFloat());
		System.out.println("실수형(Double) 자료 : " + din.readDouble());
		System.out.println("논리형 자료 : " + din.readBoolean());
		
		din.close(); // 보조스트림 닫기
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
