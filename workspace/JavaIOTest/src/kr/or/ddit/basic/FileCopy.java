package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) throws IOException {
		
		FileInputStream fin = new FileInputStream("d:/D_Other/음식.jpg");
		FileOutputStream fout = new FileOutputStream("d:/D_Other/복사본_pome.jpg");
		
		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(fout);
		
		
		
		
		int c;
		
		while((c = fin.read()) != -1) {
			bout.write(c);
		}
		bout.close();
		System.out.println("복사완료");
		
		fin.close();
		fout.close();
		
		// 그 바이트로 쪼개는 getbyte 이거..없어도되나보다.
		
	}
}
