package kr.or.ddit.basic;
// xml파일 만들고, VO만들고, 컨트롤러만들고, 메인만듦.
//             MemberVO

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class T17_PagingTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PagingTest.fxml"));
		Parent root = loader.load(); //만들어진 객체를 여기에 답겠다는 뜻. 컨트롤까지도 다 만들어진거야.
		//객체랑 컨트롤러랑 fxml 태그붙어있는 주입까지 끝난것.
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Paging Test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
