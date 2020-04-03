package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class T12_RadioButtonTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// 라디오 버튼을 묶음으로 처리할 객체 생성 : ToggleGroup 이 필요하다. 라디오버튼은 2개동시에 못눌러. 무조건 하나만선택할수있음.
		ToggleGroup group = new ToggleGroup();
		
		ImageView icon = new ImageView();
		
		RadioButton rb1 = new RadioButton("HOME");
		rb1.setToggleGroup(group); // 라디오 버튼을 그룹에 추가.
									// 그룹에 추가하지않으면 다른그룹이어서 동시선택가능하다. 하나만선택할수있게하려면 꼭 그룹으로 묶어야해.
		rb1.setUserData("Home"); // 선택했을 때의 값을 나타내기 위한 데이터 설정
									// 눈에보이진않지만 나중에 getuserdata 로 사용자정보 받아올 수 있다.
		
		RadioButton rb2 = new RadioButton("CALENDAR");
		rb2.setToggleGroup(group);
		rb2.setUserData("Calendar");
		
		RadioButton rb3 = new RadioButton("CONTACTS");
		rb3.setToggleGroup(group);
		rb3.setUserData("Contacts");
		
		// 그룹 내에서 RadioButton 중 하나가 선택되었을 때 처리하기
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// 선택된 항목(라디오 버튼) 이 있는지 검사.
				if(group.getSelectedToggle().getUserData() != null) {
					// .getSelectedToggle().getUserData() 로 접근. 우리가 String으로 세팅해논거야 userdata는.
					String url = group.getSelectedToggle().getUserData().toString();
					
					Image img = new Image(
							getClass().getResourceAsStream("images/" + url + ".jpg"));
					icon.setImage(img);
				}
			}
		});
		
		rb1.setSelected(true); //선택된 상태로 설정하기
		
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		
		vbox.getChildren().addAll(rb1, rb2, rb3);
		vbox.setSpacing(10);
		
		hbox.getChildren().addAll(vbox, icon);
		hbox.setSpacing(50);
		hbox.setPadding(new Insets(10));
		
		Scene scene = new Scene(hbox, 250, 150);
		primaryStage.setTitle("라디오버튼 예제");
		primaryStage.setScene(scene);
		primaryStage.show();
				
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
