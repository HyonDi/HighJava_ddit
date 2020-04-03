package kr.or.ddit.basic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class member_pr_Controller implements Initializable {

	@FXML private TextField txtId;
	@FXML private TextField txtName;
	@FXML private TextField txtTel;
	@FXML private TextField txtAddr;
	
	@FXML private Button btnAdd;
	@FXML private Button btnEdit;
	@FXML private Button btnDel;
	@FXML private Button btnSubmit;
	@FXML private Button btnCancel;
	@FXML private TableView<member_pr_VO> memTableView;
	@FXML private TableColumn<member_pr_VO, String> memId;
	@FXML private TableColumn<member_pr_VO, String> memName;
	@FXML private TableColumn<member_pr_VO, String> memTel;
	@FXML private TableColumn<member_pr_VO, String> memAddr;
	ObservableList<member_pr_VO> data = null;
	
	member_pr_VO mem;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		data = FXCollections.observableArrayList(
				new member_pr_VO("guswl123", "강현지", "1234-1234", "청주")
				);
		
		
		memTableView.setItems(data);
		
		memId.setCellValueFactory(new PropertyValueFactory<>("id"));
		memName.setCellValueFactory(new PropertyValueFactory<>("name"));
		memTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		memAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		
		//흐린글씨
		txtId.setPromptText("회원 ID");
		txtName.setPromptText("회원 이름");
		txtTel.setPromptText("회원 전화");
		txtAddr.setPromptText("회원 주소");
		
		
		// 마우스로 테이블 선택했을 때
		memTableView.setOnMouseClicked(e->{
				member_pr_VO mem = memTableView.getSelectionModel().getSelectedItem();
				
				txtId.setText(mem.getId());
				txtName.setText(mem.getName());
				txtTel.setText(mem.getTel());
				txtAddr.setText(mem.getAddr());
		});
		
		btnSubmit.setDisable(true);
		btnCancel.setDisable(true);
		
		txtId.setDisable(true);
		txtName.setDisable(true);
		txtTel.setDisable(true);
		txtAddr.setDisable(true);
	}
	
	// 추가버튼 누르고 확인누르면 정보들어가고
	// 수정버튼 누르고 확인누르면 수정완료되고
	// 수정 눌렀다가 취소누르면 취소되게해야함!!!
	// 추가
	boolean editchk = false;
	boolean addchk = false;
	boolean delchk = false;
	
	@FXML public void btnAddClicked(ActionEvent event) {
		txtId.setDisable(false);
		txtName.setDisable(false);
		txtTel.setDisable(false);
		txtAddr.setDisable(false);
		
		btnSubmit.setDisable(false);
		btnCancel.setDisable(false);
		btnAdd.setDisable(true);
		btnEdit.setDisable(true);
		btnDel.setDisable(true);
		
		addchk=true;
		editchk = false;
		delchk = false;
	}
	
	// 수정
	@FXML public void btnEditClicked(ActionEvent event) {
		btnSubmit.setDisable(false);
		btnCancel.setDisable(false);
		
		btnAdd.setDisable(true);
		btnEdit.setDisable(true);
		btnDel.setDisable(true);
		
		memTableView.setOnMouseClicked(e->{
			member_pr_VO mem = memTableView.getSelectionModel().getSelectedItem();
			
			txtId.setText(mem.getId());
			txtName.setText(mem.getName());
			txtTel.setText(mem.getTel());
			txtAddr.setText(mem.getAddr());
			
			txtId.setDisable(false);
			txtName.setDisable(false);
			txtTel.setDisable(false);
			txtAddr.setDisable(false);
			
			addchk = false;
			editchk = true;
			delchk = false;
			
		});
	}
	
	
	
	// 삭제
	@FXML public void btnDelClicked(ActionEvent event) {
		btnSubmit.setDisable(false);
		btnCancel.setDisable(false);
		
		btnAdd.setDisable(true);
		btnEdit.setDisable(true);
		btnDel.setDisable(true);
		
		memTableView.setOnMouseClicked(e->{
			member_pr_VO mem = memTableView.getSelectionModel().getSelectedItem();
			
			txtId.setText(mem.getId());
			txtName.setText(mem.getName());
			txtTel.setText(mem.getTel());
			txtAddr.setText(mem.getAddr());
			
			addchk = false;
			editchk = false;
			delchk = true;
		});
	}
	
	
	
	// 확인버튼
	@FXML public void btnSubmitClicked(ActionEvent event) {
		
		if(addchk) {
			// 추가시 수행될 부분.
			if(txtId.getText().isEmpty() || txtName.getText().isEmpty()
					|| txtTel.getText().isEmpty() || txtAddr.getText().isEmpty()) {
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			data.add(new member_pr_VO(txtId.getText(), txtName.getText(),
					txtTel.getText(), txtAddr.getText()));
			infoMsg("작업 결과", txtName.getText() + "님 정보를 추가했습니다. ");
			
			txtId.clear();
			txtName.clear();
			txtTel.clear();
			txtAddr.clear();
			
			txtId.setDisable(true);
			txtName.setDisable(true);
			txtTel.setDisable(true);
			txtAddr.setDisable(true);
			
			btnSubmit.setDisable(true);
			btnCancel.setDisable(true);
			
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
			btnDel.setDisable(false);
		}
		
		
		if(editchk) {
			//수정시 수행될 부분.
			if(txtId.getText().isEmpty() || txtName.getText().isEmpty()
					|| txtTel.getText().isEmpty() || txtAddr.getText().isEmpty()) {
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			data.set(memTableView.getSelectionModel().getSelectedIndex(), new member_pr_VO(txtId.getText(), txtName.getText(),
					txtTel.getText(), txtAddr.getText()));
			infoMsg("작업 결과", txtName.getText() + "님 정보를 수정했습니다. ");
			
			txtId.clear();
			txtName.clear();
			txtTel.clear();
			txtAddr.clear();
			
			txtId.setDisable(true);
			txtName.setDisable(true);
			txtTel.setDisable(true);
			txtAddr.setDisable(true);
			
			btnSubmit.setDisable(true);
			btnCancel.setDisable(true);
			
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
			btnDel.setDisable(false);
		}
		
		
		if(delchk) {
			// 삭제시 수행될 부분.
			if(memTableView.getSelectionModel().isEmpty()) {
				errMsg("작업 오류", "삭제할 자료를 선택한 후 삭제하세요.");
				return;
			}
			data.remove(memTableView.getSelectionModel().getSelectedIndex());
			infoMsg("작업 결과", txtName.getText() + "님 정보를 삭제했습니다. ");
			
			txtId.clear();
			txtName.clear();
			txtTel.clear();
			txtAddr.clear();
			
			txtId.setDisable(true);
			txtName.setDisable(true);
			txtTel.setDisable(true);
			txtAddr.setDisable(true);
			
			btnSubmit.setDisable(true);
			btnCancel.setDisable(true);
			
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
			btnDel.setDisable(false);
		}
	}
	
	
	// 취소버튼
	@FXML public void btnCancelClicked(ActionEvent event) {
		txtId.clear();
		txtName.clear();
		txtTel.clear();
		txtAddr.clear();
		
		txtId.setDisable(true);
		txtName.setDisable(true);
		txtTel.setDisable(true);
		txtAddr.setDisable(true);
		
		btnSubmit.setDisable(true);
		btnCancel.setDisable(true);
		btnAdd.setDisable(false);
		btnEdit.setDisable(false);
		btnDel.setDisable(false);
	}
	
	
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
}