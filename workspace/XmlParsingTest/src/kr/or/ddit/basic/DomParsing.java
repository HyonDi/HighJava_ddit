package kr.or.ddit.basic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * 돔파싱예제. 
 * xml파일 파싱하는 방법에는 돔파싱, sax파싱 등이 있다.
 * 심플제이슨?? 제이슨오브젝트??
 * @author PC-16
 *
 */
public class DomParsing {
	public void parse() {
		try {
			// DOM document 객체 생성
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			// DOM파서로부터 입력받은 파일을 파싱하도록 요청
			DocumentBuilder builder = dbf.newDocumentBuilder();
			//돔빌더팩토리를 부르고 그안에있는 돔다큐멘트빌더를 불러왔다.
			
			// XML파일 지정
			String url = getClass().getResource("/kr/or/ddit/basic/book.xml").toExternalForm();
			// 같은디렉토리에있으면 /kr/or/ddit/basic/ 이거 생략하고 book.xml만 써도 된다.
			
			Document xmlDoc = null;
			
			// DOM 파서로부터 입력받은 파일을 파싱하도록 요청. 돔객체가 만들어진것.
			xmlDoc = builder.parse(url);
			
			// 루트 엘리먼트 접근
			Element root = xmlDoc.getDocumentElement();
			System.out.println("root.getTagName() => " + root.getTagName()); // booklist
			
			// Node > Element
			// 엔터키도 노드라고??
			
			
			
			// 하위 엘리먼트 접근
			NodeList bookNodeList = root.getElementsByTagName("book");// book이 여러개있을수있어서 List로 받았다.
			
			Node firstBookNode = bookNodeList.item(0); // 첫번째 아이템. 첫번째아이템은 리스트아니고 하나니까 Node야.
			Element bookElement = (Element)firstBookNode; // 엘리먼트의 메소드사용하고싶어서 element로 캐스팅했다.
			
			// element로, node로 사용할수있는 메서드에따라, 상황에따라 형변환을 할지말지 결정한다.
			
			
			// 속성 접근 방법1 : 엘리먼트 객체의 getAttribute 메서드 이용
			String isbn = bookElement.getAttribute("isbn");// isbn = 북아이디같은거로 정해놨다.
			System.out.println("bookElemnet.getAttribute(\"isbn\") => " + isbn);
			
			// 속성 접근 방법2 : 노드객체 getAttributes()메서드 이용(속성노드 접근)
			NamedNodeMap nodeMap = firstBookNode.getAttributes(); // Map!! 노드를 밸류로, 속성값을 키로 매핑한 맵이야.getAttributes()
			System.out.println("노드객체의 getAttributes()메서드 이용 : " + nodeMap.getNamedItem("isbn").getNodeValue());// 노드가 가진벨류를 꺼내왔다.
			
			// 텍스트(노드) 접근
			NodeList bookN1 = firstBookNode.getChildNodes();
			// 텍스트도 노드야(텍스트노드). 속성도 노드야(엘리먼트노드). 코멘트 주석도 노드야.(코멘트노드)...으로 파싱중임.
			
			// 인덱스 사용시 주의할 것.
			// 엔터키에 대항되는 부분이 읽힐수 있다.
			//(getChildNodes() 보다는, getElementsByTagName() 을 이용하는게 좋다.)
			Node titleNode = bookN1.item(1); // 공백문자때문에 인덱스 값을 1로 설정.
			Element titleElement = (Element)titleNode;
			System.out.println("titleElement.getTextContent() => " + titleElement.getTextContent()); // 자바초급
			
			// 전체 출력하기
			// 속성값 : isbn, kind
			// 엘리먼트 텍스트 값 : title, author, price
			System.out.println("---------------------------------------");
			for(int i=0; i<bookNodeList.getLength(); i++) {// 엘리먼트로가져온건엔터키text노드같은건 빼고가져온다. node에서 가져온건 다 포함.
				Node bookNode = bookNodeList.item(i);
				Element element = (Element) bookNode;
				String is = element.getAttribute("isbn");
				String ki = element.getAttribute("kind");
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String author = element.getElementsByTagName("author").item(0).getTextContent();
				String price = element.getElementsByTagName("price").item(0).getTextContent();
				String str = String.format("%8s %10s %20s %10s %8s", is, ki, title, author, price);
				
				System.out.println(str);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DomParsing parse = new DomParsing();
		parse.parse();
	}
}
