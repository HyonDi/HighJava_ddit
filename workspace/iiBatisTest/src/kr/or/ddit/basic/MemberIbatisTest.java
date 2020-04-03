package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberIbatisTest {
public static void main(String[] args) {
	
	
	//iBatis를 이용하여 DB자료를 처리하는 작업 순서
	//1. iBatis의 환경설정파일을 읽어와 실행시킨다.
	try {
		// 1-1. xml문서 읽어오기
		// 설정파일의 인코딩 설정
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
												//iBatis 설정파일(기능들의 설정파일)
												//iBatis객체를 만들때 이 파일을 참조해 필요한 객체를 만들기 위해서 읽어온다.
		
		
		// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작없을 진행할 객체 생성
		SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		rd.close(); //Reader객체 닫기
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
		
		// 2-1. insert작업 연습
		System.out.println("Insert 작업 시작...");
		
		// 1) 저장할 데이터를 VO에 담는다. (MVCTest의 MemberVO클래스 복사해서 현재패키지에 붙여넣기함.)
		MemberVO mv = new MemberVO();
		mv.setMem_id("d001");
		mv.setMem_name("강감찬");
		mv.setMem_tel("010-1111-2222");
		mv.setMem_addr("대전시 중구 대흥동");
		
		// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
		// 형식) smc.insert("namespace값.id값", 파라미터객체);
		// 		반환값 : 성공하면 **null이 반환된다. **주의!
		
		Object obj = smc.insert("memberTest.insertMember", mv);
		if(obj == null) {
			System.out.println("Insert 작업 성공!");
		} else {
			System.out.println("Insert작업 실패!!!");
		}
		System.out.println("------------------------------------");
		
		// (강의소스의 member.xml 을 복사해 현패키지에 붙여넣었다.)
		//이제 쿼리문작성하러 member.xml로 갑니다.
		
		// 2-2. update작업 연습
		System.out.println("update작업 시작...");
		
		MemberVO mv2 = new MemberVO();
		mv.setMem_id("d001");
		mv.setMem_name("홍수환");
		mv.setMem_tel("010-9999-9999");
		mv.setMem_addr("대전시 동구 대동");
		
		//update()메서드 반환값은 성공한 레코드 수이다.
		int cnt = smc.update("memberTest.updateMember", mv);
		
		if(cnt > 0) {
			System.out.println("Update 작업 성공!");
		}else {
			System.out.println("Update 작업 실패!!!");
		}
		System.out.println("------------------------------------");
		
		//쿼리작성하러 member.xml 로 갑니다.
		
		// 2-3. delete 연습
/*		System.out.println("Delete 작업 시작...");
		
		// delete 메서드의 반환 값 : 성공한 레코드 수
		
		int cnt2 = smc.delete("memberTest.deleteMember","d001");왜 mv 안넣고 스트링을 넣는걸까?
		
		if(cnt2 > 0) {
			System.out.println("Delete 작업 성공!");
		} else {
			System.out.println("Delete 작업 실패!!!");
		}
		System.out.println("------------------------------------");
		
		*/
		// 2-4. select 연습
	/*	방법이 결과가 1개인경우와 여러건인경우 2가지로 나뉘어진다.
		여러건인경우, 리스트에 담는과정을 알아서 해주는 쿼리포리스트 메서드를 사용해야한다.
		리스트로 받고싶은경우 이렇게 하고, 오브젝트로 받고싶으면 그리하면됨.
	*/
		
		
		// 1) 응답의 결과가 여러개일 경우
		System.out.println("Select 연습 시작 (결과가 여러개인 경우)...");
		List<MemberVO> memList;
		
		// 응답 결과가 여러개일 경우에는 queryForList메서드를 사용한다.
		// 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를
		// List에 추가해 주는 작업을 자동으로 수행한다.
		memList = smc.queryForList("memberTest.getMemberAll");
		for(MemberVO memVO : memList) {
			System.out.println("ID : " + memVO.getMem_id());
			System.out.println("이름 : " + memVO.getMem_name());
			System.out.println("전화 : " + memVO.getMem_tel());
			System.out.println("주소 : " + memVO.getMem_addr());
		}
		System.out.println("Select(결과가 여러개인 경우) 출력 끝!");
		//쿼리작성하러 갑니다.
		
		// 2) 응답이 1개일 경우
		System.out.println("Select 연습 시작 (결과가 1개일 경우)...");
		
		// 응답 결과가 1개가 확실할 경우에는 queryForObject메서드를 사용한다.
		MemberVO mv3 = (MemberVO) smc.queryForObject("memberTest.getMember","d002");
		
		for(MemberVO memVO : memList) {
			System.out.println("ID : " + mv3.getMem_id());
			System.out.println("이름 : " + mv3.getMem_name());
			System.out.println("전화 : " + mv3.getMem_tel());
			System.out.println("주소 : " + mv3.getMem_addr());
		}
		//쿼어리만들
		
	}catch(IOException e) {
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}

	
}
}
