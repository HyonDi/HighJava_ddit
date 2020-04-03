package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class T10_ComboGugudanMain extends Application{
// 프로그래밍레이아웃 방식? 자바파일로만 만드는 것. = 주석된 윗부분.
// 화면구성하는 fxml 파일이있고, 이걸 제어하는 controller 가 따로있는 것 =  아랫부분 + combogugudan.fxml + combogugudanContorller.java
// 매핑할수있는 컨트롤러 1개밖에안돼. 비슷하다해서 다른데서 컨트롤러를 여기저기서쓸 수 없음 딱한군데서만 사용 가능. 화면하나당 컨트롤러 하나.
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	/*	

		// 가장바깥에 사용한 컨테이너 : borderpane.
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 300);
		
		// hbox만듬
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(10);
		
		// 출력할 textarea
		TextArea txtResult = new TextArea();
		txtResult.setPrefSize(200, 200);
		
		// button.settext 해도 똑같음.
		Button btnDan = new Button("출력");
		
		ComboBox<Integer> cmbDan = new ComboBox<>();
		cmbDan.setPrefWidth(150);
		
		// 생성동시에 초기화
		ObservableList<Integer> list = FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9
				);
		
		cmbDan.setItems(list);
		
		btnDan.setOnAction(e -> {
			int dan = cmbDan.getSelectionModel().getSelectedItem();
			// combobox.getvalue 해서 가져와도되고 getselectionModel 이 관리하는 getselecteditem 으로 해도 된다.
			// selectedindex 도 있다. obsevableList 니까 아이템마다 인덱스 번호 가지고 있음.
			
			txtResult.setText(dan + "단\n\n");
			for(int i = 1; i <= 9 ; i ++) {
				int r = dan * i;
				txtResult.appendText(dan + " * " + i + " = " + r + "\n");
				// append : 추가한다는 의미. 기존내용을 두고 그 밑으로 쓰는 것. set 은 '=' 이고 append는 '+=' 임.
			}
		});
		
		hbox.getChildren().addAll(cmbDan, btnDan);
		
		root.setTop(hbox);
		root.setCenter(txtResult);
*/
	
		Parent root = FXMLLoader.load(getClass().getResource("ComboGugudan.fxml"));
		// parent 대신 borderpain 으로 해도 됨. 더 많이 담을수있는 상위클래스로 적은것뿐이다.
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("콤보박스 구구단");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
