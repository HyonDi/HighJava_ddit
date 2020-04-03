package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	public static void main(String[] args) {
		// File객체 만들기 연습
		
		// 1. new File(String 파일 또는 경로명)
		// => 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 '\'(윈도우)를 사용하거나 '/'(윈도우, 그외)를 사용할 수 있다.
		//txt파일이 만들어진게 아니고 객체만 만들어짐
		File file = new File("d:\\D_Other\\test.txt"); //이클립스에서는 \를 특수문자로 받아들여서 \\ 두개로 입력
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("----------------------------------------------");
		
		File file2 = new File("d:/D_Other");
		//File file2 = new File("e:D_Other/test.txt");
		System.err.print(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		}else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("-----------------------------------------------");
		
		// 2. new File(File parent, String child)
		// => 'parent'디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "bytes");
		
		// 3. new File(String parent, String child)
		File file4 = new File("\\D_Other\\test\\..", "test.txt"); // '\\..' = D_Other
		System.out.println("절대 경로 : " + file4.getAbsolutePath()); //파라미터에 넣지 않은 드라이브 경로(e:)까지 나온다 : 폴더가 저장된 드라이브가 바뀌면 에러난다
		System.out.println("경로 : " + file4.getPath()); //파라미터에 넣은 경로가 그대로 나온다
		try {
			System.out.println("표준 경로 : " + file4.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------------------------");
		
		/*
		 * 디렉토리(폴더) 만들기
		 *  1. mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 *  		   => 중간의 경로가 모두 미리 만들어져 있어야 한다.
		 *  
		 *  2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어준다.
		 *  			=> 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환
		 *  
		 */
		
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) { //D_Other가 없는 경우 실패
			System.out.println(file5.getName() + " 만들기 성공!");
		}else {
			System.out.println(file5.getName() + " 만들기 실패!!!");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdirs()) { //D_Other에 없는 test/java/src도 만들어낸다
			System.out.println(file6.getName() + " 만들기 성공!");
		}else {
			System.out.println(file6.getName() + " 만들기 실패!!!");
		}
	}
}
