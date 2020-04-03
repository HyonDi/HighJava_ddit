package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
/**
 * combogugudan.fxml 의 컨트롤러
 * @author PC-16
 *
 */
// 로딩되는 과정에서 얘가 컨트롤러 객체를 생성하면서 ... 여길 실행해줌. 초기화작업하는 곳.
// 씬빌더로 만든 fxml 안의 기능을 여기서 구현할것이다.
// fxml로더로 fxml 파일안의 태그들을 전부 객체화시킨다. 힙메모리에 올려줌.
// 객체만드는과정에 컨트롤러객체가있으면 (컨트롤러객체가 여기임) 로더를 통해만들어진 객체에 기능을 부여하기위해
// 그 객체.setonaction 같은거



// 모든기능을 initailize 에 넣으면 너무 길어지니까 fxml파일을 수정해야함.
// @FXML
//public void btnDanClicked(ActionEvent event) 여기에 따로 빼기로했음.

//inirailizable 임플안하고 메서드에 어노테이션 FXML 붙이고 파라미터빼고...initialize 만 해도 된다는데 뭔말인지모르겠음.
public class ComboGugudanController implements Initializable{
	
//	fxml.loader 가 생성됐을 때 여기서만들어진 객체들을 자바파일에있는  fxml어노테이션이 붙어있는 곳에 찾아가서 주입시킴.
	@FXML 
	private ComboBox<Integer> cmbDan;
	
	@FXML 
	private Button btnDan;
	
	@FXML 
	private TextArea txtResult;


	//요기가 실행되는 부분엔 반드시 위 fxml어노테이션붙은 변수에 객체들이 담겨있다. 안들어있으면 오타임.
	//initialize 로 각 객체에 접근 가능해진다.
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		
		
		ObservableList<Integer> list = FXCollections.observableArrayList(
				1,2,3,4,5,6,7,8,9
				);
		
		cmbDan.setItems(list);
		
		
		
/*		btnDan.setOnAction(e -> {
			int dan = cmbDan.getSelectionModel().getSelectedItem();
			// combobox.getvalue 해서 가져와도되고 getselectionModel 이 관리하는 getselecteditem 으로 해도 된다.
			// selectedindex 도 있다. obsevableList 니까 아이템마다 인덱스 번호 가지고 있음.
			
			txtResult.setText(dan + "단\n\n");
			for(int i = 1; i <= 9 ; i ++) {
				int r = dan * i;
				txtResult.appendText(dan + " * " + i + " = " + r + "\n");
				// append : 추가한다는 의미. 기존내용을 두고 그 밑으로 쓰는 것. set 은 '=' 이고 append는 '+=' 임.
			}
		});*/
		
	}

	// fxml에 onAction부분 ctrl+1 해서 생긴 메서드.
	//어노테이션 fxml은 변수, 메서드에도 붙을 수 있음. 필드 메서드 둘다. 클래스에는 안돼.
	//이게 이벤트핸들러.
	
	@FXML
	public void btnDanClicked(ActionEvent event) {
		int dan = cmbDan.getSelectionModel().getSelectedItem();
		
		txtResult.setText(dan + "단\n\n");
		for(int i = 1; i <= 9 ; i ++) {
			int r = dan * i;
			txtResult.appendText(dan + " * " + i + " = " + r + "\n");
		}
	}

}
