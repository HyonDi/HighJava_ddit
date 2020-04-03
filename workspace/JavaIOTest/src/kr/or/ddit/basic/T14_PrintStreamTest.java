package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터기능을 제공하는 보조스트림
 * @author PC-16
 *
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fout = new FileOutputStream("d:/D_Other/print.txt");
		FileOutputStream fout2 = new FileOutputStream("d:/D_Other/print2.txt");
		
		PrintStream out = new PrintStream(fout);
		// fout 대신에 System.out 쓰면 파일에말고 콘솔에 내용표시됨
		out.print("안녕하세요, PringStream 입니다. \r\n");
		out.println("안녕하세요, PringStream 입니다.2");
		out.println("안녕하세요, PringStream 입니다.3");
		
		out.close();
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fout2, "UTF-8"));
		
		writer.print("안녕하세요, PrintWriter 입니다. \r\n");
		writer.println("안녕하세요, PrintWriter 입니다.2");
		writer.println("안녕하세요, PrintWriter 입니다.3");
		
		writer.close();
	}
}
