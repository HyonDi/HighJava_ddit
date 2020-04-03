package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// sciene builder로 borderpane 켜놓고 구성한번 해보세요.
/*
 	* BoarderPane (pane : 빠네. 그 공간말하는듯)
  - top, bottom, left, right, center 셀에 먼트롤을 배치하는 컨테이너
  	각 셀에는 하나의 컨트롤 또는 컨테이너만 배치
  	
  - top, bottom, left, right 에 배치하지 않으면 center에 배치된 컨트롤이 사방으로 자동 확장.
  
 */
public class T05_BoarderPaneTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Borderpane이 HBox Vbox같은 거.(컨테이너)
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 200);
		
		ToolBar toolBar = new ToolBar(
				new Button("첫번째 버튼"),
				new Button("두번째 버튼")
			);
		
		TextArea txtArea = new TextArea();
		
		root.setTop(toolBar); // Top영역에 ToolBar추가하기
		root.setCenter(txtArea); // Center영역에 TextArea 추가하기.
		
		
		//borderpane을 하나 더 만듦
		BorderPane bottom = new BorderPane();
		bottom.setPadding(new Insets(10));
		
		// textfield랑 버튼 만듬.
		TextField txtField = new TextField();// 한줄짜리
		Button btn1 = new Button("확인");
		
		//새로만든 borderpane 인 bottom에 textfield랑 button 추가시킴.
		bottom.setCenter(txtField);
		bottom.setRight(btn1);
		// 센터랑 right만 줌. left, top, bottom 자리가 없는데 center에서 확장되어 사라진다.
		// center 가 잡아먹음!
		
		
		//새로만든 borderpane 을 아까만든 borderpane인 root의 bottom 에 추가시킴.
		root.setBottom(bottom);
		//bottom 자리에 (setBottom의 파라미터자리에) node면 다 들어갈 수 있다.
		
		
		// 씬에 root 을 넣음.
		Scene scene = new Scene(root);
		
		// 스테이지에 이름 넣고 씬넣고 show() 함.
		primaryStage.setTitle("BorderPane 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
