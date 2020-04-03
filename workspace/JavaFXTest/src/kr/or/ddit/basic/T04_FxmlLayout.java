package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class T04_FxmlLayout extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// fxml 을 읽어와 현재 Stage에 적용하기
		
		// Parent 객체는 모든 컨테이너의 조상 객체
		// 방법 1
/*		Parent root = FXMLLoader.load(getClass()
				.getResource("FxmlLayout.fxml"));
		// object 밑에 node 밑에 parent 있는데 여기서 parent 객체를 리턴.(FXMLloader 가)
		// 그래서 parent 밑에있는 다른애들도 다 받을 수 있음. button, text 등 컨트롤들 받을수있나봄.
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Fxml문서를 이용한 레이아웃 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
*/
		
		//방법 2
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlLayout.fxml"));
		Parent root = loader.load();
		
 
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Fxml문서를 이용한 레이아웃 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		/*
		 * 방법 1, 2 다른부분 : 
		 * 방법1은 static으로만들어서 사용.
		 * 방법2는 new 해서 instance객체 만들어서 담고 그 객체의 인스턴스 메서드 호출도 해야함. 더 길어진다.
		 * */
//		loader.getController();  : 해당 fxml의 컨트롤러를 가져올 수 있다.
		/*따라서 2번방법은 메서드를 통해 컨트롤러까지 가져올 수 있다.*/
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
