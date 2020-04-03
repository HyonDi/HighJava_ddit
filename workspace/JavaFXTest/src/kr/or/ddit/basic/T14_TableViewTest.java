package kr.or.ddit.basic;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class T14_TableViewTest extends Application{

	// 시작하기전에 외부 클래스를 정의합니다 (메인밑에)
	// 중요중요함 
	// MVC패턴했던거 이거로바꾼다고???
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TableView 에 나타낼 데이터 구성하기.
		// memberdata list로 관리한것처럼 observable list로 관리한다.
		ObservableList<Member> data = 
				FXCollections.observableArrayList(
						new Member("홍길동", "gildong", 22, "2222-2222", "대전"),
						new Member("홍길서", "gilseo", 33, "3333-3333", "서울"),
						new Member("홍길남", "gilnam", 44, "4444-4444", "부산"),
						new Member("홍길복", "gilbok", 55, "5555-5555", "광주")
				);
		
		//root 니까 얘가 매앤 바깥 컨트롤일걸.
		BorderPane root = new BorderPane();
		
		//TableView에 데이터 세팅하기(파라미터로 데이터를 받는다.)
		TableView<Member> table = new TableView<>(data);
		
		//T는 타입이고, S는??		
		// 해당 컬럼에 나타낼 데이터 연결하기
		// (출력할 객체의 멤버변수와 출력할 컬럼을 매칭시킨다.)
		
		
		TableColumn<Member, String> nameCol = 
				new TableColumn<>("이름");
		TableColumn<Member, String> korNameCol = 
				new TableColumn<>("한글이름");
		korNameCol.setStyle("-fx-alignment: CENTER;"); // 중앙정렬
		korNameCol.setCellValueFactory(new PropertyValueFactory<>("korName"));
		
		TableColumn<Member, String> engNameCol = new TableColumn<>("영어이름");
		engNameCol.setCellValueFactory(new PropertyValueFactory<>("engName"));
		
		nameCol.getColumns().addAll(korNameCol, engNameCol); //컬럼 병합한것임.
		// 컬럼안에 컬럼을 담을 수 있다.getcolumns 로  관리중인 obsrvablelist를 불러오고, Addall 로 namecol 안에 담긴 컬럼을 가져올 수 있음.
		
		
				//테이블타입,  실제 데이터타입
		TableColumn<Member, Integer> ageCol = new TableColumn<>("나이");// 헤더에 보여질 컬럼 이름. 생성자에 넣어 컬럼을 생성한다.
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		// 얘는?? 셀에 벨류값을 어떻게 만들지, 세팅해주는 부분. 우리는 propertyvaluefactory객체를 만들어 파라미터를 넣어줬다. 멤버객체 프로퍼티값. 게터세터 접근해서 들어갈수있는 그 값.
		// 아이바티스에서 속성값 ## 사이에 넣은것처럼. 파라미터로 "age" 넣었다.
		
		TableColumn<Member, String> telCol = new TableColumn<>("전화번호");
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		
		TableColumn<Member, String> addrCol = new TableColumn<>("주소"); // 여기는 씬빌더로 가능.
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr")); // 여기는 자바코드로 추가시켜야함.
		
		// 생성된 각 컬럼들을 TableView에 추가한다.
		table.getColumns().addAll(nameCol, ageCol, telCol, addrCol);
		
		// table.setItems(data); // 생성자에 넣어도 되고 이렇게 setitems 해도 된다.
		
		//--------------------------------------------------
		// 여기부턴 이제 부수적인부분. 
		
		GridPane grid = new GridPane();
		Text txt1 = new Text("한글이름");
		Text txt2 = new Text("영어이름");
		Text txt3 = new Text("나   이");
		Text txt4 = new Text("전화번호");
		Text txt5 = new Text("주   소");
		
		TextField txtKorName = new TextField();
		TextField txtEngName = new TextField();
		TextField txtAge = new TextField();
		TextField txtTel = new TextField();
		TextField txtAddr = new TextField();
		
		grid.add(txt1, 1, 1); //첫째줄 첫째칸에 넣음.
		grid.add(txt2, 2, 1); // 첫째줄 둘재칸
		grid.add(txt3, 3, 1);
		grid.add(txt4, 4, 1);
		grid.add(txt5, 5, 1);
		
		grid.add(txtKorName, 1, 2);
		grid.add(txtEngName, 2, 2);
		grid.add(txtAge, 3, 2);
		grid.add(txtTel, 4, 2);
		grid.add(txtAddr, 5, 2);
		
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 0, 10, 0));
		//--------------------------------------------------
		
		VBox vbox = new VBox(10); // 스페이싱
		vbox.setPadding(new Insets(10)); // 패딩
		
// 추가
		Button btnAdd = new Button("추가");
		btnAdd.setOnAction(e->{
			if(txtKorName.getText().isEmpty() || txtEngName.getText().isEmpty()
					||  txtAge.getText().isEmpty()  // empty = null/""??
					||  txtTel.getText().isEmpty()
					||  txtAddr.getText().isEmpty()) {
				//System.out.println("빈 항목이 있습니다.");
				errMsg("작업오류", "빈 항목이 있습니다.");
				
				return; // 바로 끝!
			}
			
			if(!Pattern.matches("^[0-9]+$", txtAge.getText())) { 
				// 정규식사용하려면 pattern.mathces 를 사용해야한다!무조건 문자한개(^)올수있는데 0~9사이에 하나올수있다.
				// +가있어서 1개 이상올수있다로 바뀜. $는 끝. ^는 시작. = 무조건 숫자만 들어와야한다는 의미.
				//System.out.println("데이터 오류");
				errMsg("데이터 오류", "나이는 정수형으로 입력하세요.");
				txtAge.requestFocus(); // 해당 객체에 포커스 주기. 나이에 숫자말고 다른게들어가면 블럭씌워지고 파랑색 씌여져서 포커싱되게하는것임.
				return;
			}
			
			
			data.add(new Member(txtKorName.getText(), txtEngName.getText(), Integer.parseInt(txtAge.getText()), txtTel.getText(), txtAddr.getText()));
			// System.out.println("정보 추가 성공...");
			infoMsg("작업 결과", txtKorName.getText() + "님 정보를 추가했습니다. ");
			
			txtKorName.clear();
			txtEngName.clear();
			txtAge.clear();
			txtTel.clear();
			txtAddr.clear();
		});
//추가끝
		
// 수정
		Button btnEdit = new Button("수정");
		btnEdit.setOnAction(e->{
			if(txtKorName.getText().isEmpty() || txtEngName.getText().isEmpty()
					||  txtAge.getText().isEmpty()  // empty = null/""??
					||  txtTel.getText().isEmpty()
					||  txtAddr.getText().isEmpty()) {
				
				// System.out.println("빈 항목이 있습니다.");
				errMsg("작업오류", "빈 항목이 있습니다.");
				
				
				return; // 바로 끝!
			}
			
			if(!Pattern.matches("^[0-9]+$", txtAge.getText())) { 
				// 정규식사용하려면 pattern.mathces 를 사용해야한다!무조건 문자한개(^)올수있는데 0~9사이에 하나올수있다.
				// +가있어서 1개 이상올수있다로 바뀜. $는 끝. ^는 시작. = 무조건 숫자만 들어와야한다는 의미.
				//System.out.println("데이터 오류");
				errMsg("데이터 오류", "나이는 정수형으로 입력하세요.");
				
				txtAge.requestFocus(); // 해당 객체에 포커스 주기. 나이에 숫자말고 다른게들어가면 블럭씌워지고 파랑색 씌여져서 포커싱되게하는것임.
				return;
			}
			// 입력이랑 다른 부분 여기임. add 가 아니라 set. 인덱스번호 = table.getSelectionModel().getSelectedIndex() // 이거 콤보박스할때 썼었대.
			// 마우스로 클릭해서 테이블이 가지고있는 모델들 중 선택된 모델의 인덱스를 가져온것.
			data.set(table.getSelectionModel().getSelectedIndex(),
					new Member(txtKorName.getText(), txtEngName.getText()
					, Integer.parseInt(txtAge.getText()), txtTel.getText(), txtAddr.getText()));
			
			//System.out.println("정보 수정 성공...");
			infoMsg("작업 결과", txtKorName.getText() + "님 정보를 수정했습니다. ");
			
			
			txtKorName.clear();
			txtEngName.clear();
			txtAge.clear();
			txtTel.clear();
			txtAddr.clear();
		});
		
		
//수정끝
		
		
//삭제
		Button btnDel = new Button("삭제");
		btnDel.setOnAction(e->{
			if(table.getSelectionModel().isEmpty()) {
				errMsg("작업 오류", "삭제할 자료를 선택한 후 삭제하세요.");
				return;
			}
			
			data.remove(table.getSelectionModel().getSelectedIndex());
			
			infoMsg("작업 결과", txtKorName.getText() + "님 정보를 삭제했습니다. ");
		});
		
//삭제끝
		
// TableView 를 클릭했을 때 처리 ( 맨아래 바텀의 grid 속에 클릭한모델의 벨류를 넣어준다.
			
		table.setOnMouseClicked(e->{
			// tableView에서 선택한 줄의 데이터를 가져온다.
			Member mem = table.getSelectionModel().getSelectedItem();
			
			txtKorName.setText(mem.getKorName());
			txtEngName.setText(mem.getEngName());
			txtAge.setText(String.valueOf(mem.getAge()));
			txtTel.setText(mem.getTel());
			txtAddr.setText(mem.getAddr());
		});
		
		
// TableView 를 클릭했을 때 처리 끝
		
		
// 속성연습
		Button btnTest1 = new Button("속성 연습1");
		btnTest1.setOnAction(e->{
			// TextField, TextArea 등 문자를 입력하는 객체를 
			// ReadOnly를 설정하는 메서드 => SetEditable()
			// 이 메서드에 true 를 설정하면 '입력 가능'
			// false 를 설정하면 '읽기전용' 이 된다.
			txtKorName.setEditable(false);
			txtEngName.setEditable(false);
			
			// 객체를 비활성화 또는 활성화 시키는 메서드 => setDisabled()
			// 이 메서드에 true를 설정하면 '비활성화'
			// false 를 설정하면 '활성화' 된다.
			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			
			// 입력상자에 흐릿하게 나타내는 메세지
			txtKorName.setPromptText("한글 이름 입력");
			txtAddr.requestFocus(); // 포커스 주기.
		});
		
		Button btnTest2 = new Button("속성 연습2");
		btnTest2.setOnAction(e->{
			txtKorName.setEditable(true);
			txtEngName.setEditable(true);
			
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
			
		});
// 속성연습 끝		
		
		
		// 버튼추가시킨부분
		vbox.getChildren().addAll(btnAdd);
		vbox.getChildren().addAll(btnEdit);
		vbox.getChildren().addAll(btnDel);
		vbox.getChildren().addAll(btnTest1,btnTest2);
		
		
		root.setCenter(table);
		root.setRight(vbox);
		root.setBottom(grid);
		root.setPadding(new Insets(10));
		
		Scene scene = new Scene(root, 600, 400);
		
		primaryStage.setTitle("TableView 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// 정보 변경(수정/입력/삭제)실패시 메세지를 띄워주자.
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
		
	}
	
	// 정보 변경(수정/입력/삭제)성공시 메세지를 띄워주자.
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
		
	}
	
	
	
	public class Member {
		private String korName;
		private String engName;
		private int age;
		private String tel;
		private String addr;
		
		// 생성자랑 게터세터 만든다.
		// 멤버변수 = 필드 ??
		
		public Member(String korName, String engName, int age, String tel, String addr) {
			super();
			this.korName = korName;
			this.engName = engName;
			this.age = age;
			this.tel = tel;
			this.addr = addr;
		}
		
		public String getKorName() {
			return korName;
		}
		public void setKorName(String korName) {
			this.korName = korName;
		}
		public String getEngName() {
			return engName;
		}
		public void setEngName(String engName) {
			this.engName = engName;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		

		
	}
	
}
