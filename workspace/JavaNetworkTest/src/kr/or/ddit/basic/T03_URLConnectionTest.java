package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class T03_URLConnectionTest {
	public static void main(String[] args) throws IOException {
		// URL Connection => 애플리케이션과  url간의 통신연결읠 위한 추상클래스
		
		// 특정 서버(예:네이버서버)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// Header정보 가져오기
		
		// URLConnection객체 구하기
		URLConnection urlCon = url.openConnection();
		
		System.out.println("Content-Type : " + urlCon.getContentType());
		System.out.println("Content Encoding : " + urlCon.getContentEncoding());
		System.out.println("Content : " + urlCon.getContent());
		
		// 전체 Header 정보 출력하기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// Header의 key값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("------------------------------------------------------");
		
		// 해당 호스트의 페이지 내용 가져오기
		
		// content 부분 맨 뒤에 inputstream@3c09711b 부분. @ 나온것 : 객체가 날아왔지만 확인불가.
		// 아래 주석으로 확인가능.
		// 파일을 읽어오기 위한 스트림 생성
		// 방법 1 => URLConnection 의 getInputStream 메서드 이용하기
		// InputStream is = urlCon.getInputStream();
		// 방법 2 => URL객체의 openStream()메서드 이용하기
		// InputStream is = url.openStream();
		
		InputStream is = url.openConnection().getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		// 커넥션맺으면 url커넥션객체가 오고, 그 속에있는 getinputstream 을 리턴받았다??
		// 1번 방법 이용한 것임. inputstream만있으면 한땀한땀 read해오면 된다.
		// text파일. 바이너리파일이 아님. 그래서 buffereReader 보조스트림 사용했다.
		
		// 내용 출력하기
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			System.out.println(str);
		}
		
		// 스트림 닫기
		br.close();
	}
}
