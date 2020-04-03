package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

//Help> 맨아래2번째 이클립스 마켓플레이스 > 검색 : properties> properties Editor 다운로드받는다. //한글사용을 위함.다운안받으면 한글 유니코드로 적어야함.
// 수업에선 한글안씀.

//소스폴더 res를 만든다.
//src는 자바파일 넣기위함. res 는 다른 거. 쿼리나 텍스트파일, 환경설정정보같은것. 자바코드와 분리되는 모든 것.
/**
 * 외부의 properties파일을 읽어와 Properties객체로 처리하기
 * (프로퍼티스객체를 이용한 외부파일 로딩.)
 * 
 * 환경설정..자바..ㅁ뽑아냄....
 */
public class T06_PropertiesTest {
	public static void main(String[] args) {
		//읽어온 정보를 저장할 Properties 객체 생성.
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File객체 생성
		File file = new File("res/db.properties");
		
		try {
			//파일 읽기를 수행할 fileInputStream객체 생성
			FileInputStream fin = new FileInputStream(file);
			
			//Properties객체로 파일 내용 읽기
			prop.load(fin); // 파일 내용을 읽어와 key와 value 값으로 분류한 후 Properties객체에 담아준다.
			
			// 읽어온 자료 출력하기

			// key값만 읽어와 Enumeration객체로 변환한다.
			Enumeration<String> keys = 
						(Enumeration<String>)prop.propertyNames();
			
			// key값 개수만큼 반복해서 값 출력하기
			
			//keys.hasMoreElements() => 다음 포인터 위치에 자료가 있으면 true, 그렇지 않으면 false
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " => " + value);
			}
			System.out.println("출력 끝...");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
