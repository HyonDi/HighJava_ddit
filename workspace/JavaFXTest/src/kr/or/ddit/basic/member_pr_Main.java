package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class member_pr_Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("member_pr.fxml"));
		
/*		ObservableList<member_pr_VO> data = FXCollections.observableArrayList(
				new member_pr_VO("guswl123", "강현지", "1234-1234", "청주")
				);
*/		
		Scene scene = new Scene(root);
		primaryStage.setTitle("회원관리 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
