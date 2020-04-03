package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class T16_DialogTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox(10);
		root.setPadding(new Insets(10)); //인셋(10)
		root.setAlignment(Pos.CENTER); // 포지션 . 센터
		
		// 파일 열기 창
		Button btnFileOpen = new Button("Open FileChooser 실행");
		btnFileOpen.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			
			// 확장자별로 파일 구분하는 필터 등록하기
			fileChooser.getExtensionFilters().addAll( // 필터를 추가합니다.
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("Image Files", 
									"*.png","*.jpg","*.gif"),
				new ExtensionFilter("Audio Files", "*.wav","*.mp3"),
				new ExtensionFilter("All Files", "*.*")
			);
			
			// Dialog창에서 '열기'버튼을 누르면 선택한 파일 정보가 반환되고
			// '취소'버튼을 누르면 null이 반환된다.
			File selectFile = fileChooser
								.showOpenDialog(primaryStage); //열기버튼누르는곳
			if(selectFile != null) {
				// 이 영역에서 파일내용을 읽어오는 작업을 수행한다.
				System.out.println("OPEN : " 
							+ selectFile.getPath());
			}
			
		});
			
		Button btnFileSave = 
				new Button("SAVE FileChooser 실행");
		btnFileSave.setOnAction(e2->{
			FileChooser fileChooser2 = new FileChooser();
			fileChooser2.getExtensionFilters().addAll( //필터추가
			  new ExtensionFilter("All Files", "*.*")		
			);
			
			File selFile = fileChooser2
					.showSaveDialog(primaryStage);
			if(selFile != null) {
				// 이 곳에서 선택한 파일을 이용한 저장 작업을 수행한다.
				System.out.println("SAVE: " 
								+ selFile.getPath());
			}
		});
		
		// 폴더(디렉토리)만 선택하는 Dialog창
		Button btnDirectory = 
				new Button("Directory Chooser 실행");
		btnDirectory.setOnAction(e3->{
			DirectoryChooser dirChooser = 
					new DirectoryChooser();
			File selDir = dirChooser.showDialog(primaryStage);
			if(selDir != null) {
				System.out.println("Directory : " + selDir);
			}
		});
		//---------------------------------------------------
		Button btnPopup = new Button("Popup 실행");
		btnPopup.setOnAction(e4->{
			// Popup창에 나타낼 컨트롤들 구성 시작...
			HBox popRoot = new HBox();
			
			ImageView imgView = new ImageView();
			imgView.setImage(
				new Image(getClass()
						.getResource("./images/ok.png")
						.toString()));
			imgView.setFitWidth(30);
			imgView.setFitHeight(30);
			
			Label lbMsg = new Label("메시지가 왔습니다.");
			HBox.setMargin(lbMsg, new Insets(0, 5, 0, 5));
			
			popRoot.getChildren().addAll(imgView, lbMsg);
			// 구성 끝...
			
			// Popup객체 생성 후 위에서 구성한 컨트롤들 추가 후 보이기
			Popup popup = new Popup();
			popup.setX(1000);
			popup.setY(400);
			popup.getContent().add(popRoot); // poproot = hbox. poproot 자리에는 node 면 다 들어갈수있어. hbox에는 동그라미아이콘이랑 레이블 들어있음
			popup.setAutoHide(true); // 한번 위로올리고 자동으로 숨기는 기능. true 를 false로 해놓으면 다른 곳클릭해도 계속 맨위(z좌표맨위)에 떠있음.
 			popup.show(primaryStage);
		});
		
// 성적관리문제를 위한 부분 시작. dailog중 가장 중요한 부분!!!***
		// 사용자가 만든 임의의 창 나타내기
		Button btnCustom = new Button("사용자 정의 창 실행");
		btnCustom.setOnAction(e->{
			// 새 창 띄우기
			
			// 1. Stage객체 생성 . 우리는 여태 파라미터로 날라오는 프라이머리스테이지만 사용했었는데
			// 						여기서 새로 만든다.
			Stage dialog = new Stage(StageStyle.UTILITY); // 창 모양을 심플하게할지 어떻게할지 정하는 부분. decorated, transparent 등등 있음. 하나하나실행해보렴.
			
			// 2. 모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);// 모달리티. 모달 속성을 주면, 부모에서 자식창을띄우면 자식창을 닫기전까지 부모창에 접근할 수 없는 것.
			
			// 3. 부모창 지정 (프라이머리스테이지로 지정했음.)
			dialog.initOwner(primaryStage); // 모달속성 줬으면 부모창이 뭔지 알려줘야대.
			
			dialog.setTitle("사용자 정의 창"); 
			
			// 4. 자식창이 나타날 컨테이너 객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("myDialog.fxml"));
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			
			// 부모창에서 FXML로 만든 자식창의 컨트롤객체 얻기
			TextField txtName = (TextField) parent.lookup("#txtName");
			PasswordField pass = (PasswordField) parent.lookup("#pass"); // look up : 찾는다. 아이디가 pass인 것을! parent 안에서!
			// lookup  안쓰고 controller 만들어서 처리해도 됩니다.
			
			Button btnOk = (Button) parent.lookup("#btnOk"); // 버튼을 가져왔으니까 셋온액션도 처리할수있다.
			
			btnOk.setOnAction(e2->{
				System.out.println("이름 " + txtName.getText());
				System.out.println("비밀번호 " + pass.getText());
			});
			
			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction(e3->{
				dialog.close(); // 창이 닫힙니다.
			});
			
			// 5. Scene 객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);
			
			// 6. Stage 객체에 Scene객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false); // 크기 고정
			dialog.show();
		});
// 끝!!--(성적관리문제를 위한 부분 시작. dailog중 가장 중요한 부분!!!***)
		
		
		root.getChildren().addAll(btnFileOpen, btnFileSave, 
				btnDirectory, btnPopup, btnCustom);
		
		Scene scene = new Scene(root, 800, 100);
		primaryStage.setTitle("Dialog창 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
