package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class T03_ProgrammingLayout extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// HBox 컨테이너 생성
		HBox hbox = new HBox(); // 가로로배치되도록하는 컨테이너.
		
		// 안쪽 여백 설정하기
		// Insets 객체는 값을 주는 순서가 위, 오른쪽, 아래, 왼쪽 순으로 설정한다.
		hbox.setPadding(new Insets(10, 10, 10, 10));
		// 그냥 숫자 10 넣으면 안돼.inset객체에 넣어서 줘야함.
		
		hbox.setSpacing(50); // 컨트롤과 컨트롤 사이의 간격
		
		//한 즐의 데이터를 입력하는 컨트롤 : TextField객체
		TextField textField = new TextField();
		textField.setPrefWidth(200); // TextField의 너비 크기 설정
		
		Button btn = new Button("확   인"); // 버튼 객체 생성
		
		// HBox에 추가하기
		hbox.getChildren().addAll(textField, btn);
//		hbox.getChildren().addAll(btn, textField);
		// 이렇게하면 순서가 바뀐다.
		
		//컨테이너에 넣었으면(위코드) Scene에 넣어야함.
		
		// Scene 객체 생성
		Scene scene = new Scene(hbox);
		
		primaryStage.setTitle("자바 코드를 이용한 레이아웃 설정하기");
		primaryStage.setScene(scene); // Scene 추가
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
