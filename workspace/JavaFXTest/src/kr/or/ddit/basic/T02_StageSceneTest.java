package kr.or.ddit.basic;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*
 * 자바코드로만 개발.
 * 장점 : 세세하게 조정가능.
 * 단점 : 협력어려움.
 * 개발완료 후 간단한 레이아웃변경이나 스타일변경이라도 자바소스 수정 후 재컴파일.
 */
public class T02_StageSceneTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception { //요기가 메인이야
		// JavaFx application Thread 가 진행시킴. ui단 그림을 그려줌.
		
		VBox root = new VBox(); // 컨트롤들을 세로로 배치해주는 컨테이너. 수직배치 레이아웃.
			//<-> HBox : 수평배치한다.
		
		root.setPrefWidth(650); // VBox의 너비(윈도우 창인가보다)
		root.setPrefHeight(150); //VBox의 높이
		root.setAlignment(Pos.CENTER); // 컨트롤들을 가운데 정렬 Center 는 이늄타입임
		root.setSpacing(20); // 컨트롤과 컨트롤 사이의 간격. 마진같은거네.
		// 컨트롤 : 컨테이너에 담겨지는 것. 버튼, 이미지, 차트, 테이블 등등등.
		// 컨테이너에 컨테이너도 넣을 수 있어.
		
		
		Label label = new Label(); // Label객체 생성(컨트롤)
		label.setText("안녕하세요, JavaFX 입니다.");
		label.setFont(new Font(50)); // Font객체를 이용하여 글자크기 설정
		//setFont 가 파라미터로 font객체를 받기때문에 저렇게 넣어야함.
		
//		gui프로그램 : 이벤트방식 진행.
//		event handler
		
		Button btn = new Button();
		btn.setText("확   인");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			// 함수같아. 파라미터로 EventHandler객체 :인터페이스:함수적인터페이스임.
//									가진 추상메서드가 handle 하나야.
			
			@Override
			public void handle(ActionEvent event) {
				// 처리할 내용을 기술하는 영역
				Platform.exit(); // 프로그램 종료. 종료할때는 platform 이라하네
				
			}
		});
		
		
		//--
		btn.setOnAction(/*(ActionEvent event*/ e/*)*/->{
			Platform.exit(); });
		// 함수적인터페이스라 이렇게 사용할 수 있다.
		btn.setOnAction(e-> Platform.exit());
		// 이렇게 줄일수도 있음.
		
		//--
		
		
		
		
		
		// VBox에 컨트롤들 추가하기
		root.getChildren().add(label);
		root.getChildren().add(btn);
		// root = vBox
		//getChilderen returntype : observableList<node>
		//observableList<node> 에 접근하기위해 getChilderen 했고, 
		//label, btn은  observableList<node>를 통해 관리되고있다.
		
//		node ->  parent
		
		
		
		
		
		
		// VBox 를 루트 컨테이너로 하는 Scene 객체 생성
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Stage와 Scene 연습"); // 창 제목
		primaryStage.setScene(scene); // Stage에 Scene 설정
		primaryStage.show(); // 창 보이기
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
