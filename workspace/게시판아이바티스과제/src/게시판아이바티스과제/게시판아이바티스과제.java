package 게시판아이바티스과제;

import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class 게시판아이바티스과제 {
public static void main(String[] args) {
	/*iBatis를 이용하여 DB자료를 처리하는 작업 순서.
	1. iBatis의 환경설정파일을 읽어와 실행시킨다.*/
	
	try {
		//1-1. xml문서 읽어오기
		//설정파일의 인코딩 설정
		
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		
		// 1-2. 위에서 읽어온 Reader 객체를 이용해 실제 작업을 진행할 객체 생성
		SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		rd.close();
		
		// 2-1. insert 작업
		System.out.println();
	}
}
}
