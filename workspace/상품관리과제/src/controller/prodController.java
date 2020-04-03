package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.ProdService;
import service.ProdServiceImpl;
import vo.ProdMainVO;
import vo.ProdVO;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class prodController implements Initializable {

	@FXML private ComboBox<ProdVO> combox1;
	@FXML private ComboBox<ProdMainVO> combox2;
	@FXML private TableView<ProdMainVO> tableView;
	@FXML private TableColumn<ProdMainVO, String> colid;
	@FXML private TableColumn<ProdMainVO, String> colName;
	@FXML private TableColumn<ProdMainVO, String> colLgu;
	@FXML private TableColumn<ProdMainVO, String> colBuyer;
	@FXML private TableColumn<ProdMainVO, Integer> colCost;
	@FXML private TableColumn<ProdMainVO, Integer> colSale;
	@FXML private TableColumn<ProdMainVO, String> colOutlien;
	@FXML private TableColumn<ProdMainVO, String> colDetail;
	
	// 서비스 객체생성해두고
	ObservableList<ProdVO> com1List;
	ObservableList<ProdMainVO> com2List;
	ObservableList<ProdMainVO> listview;
	
	ProdService ps = ProdServiceImpl.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<ProdVO> comboxL1 = ps.combox1List();
		com1List = FXCollections.observableArrayList(comboxL1);
		combox1.setItems(com1List);
		
		com2List = FXCollections.observableArrayList();
		
		ListView<ProdVO> list = new ListView<ProdVO>();
		
		//combox1 데이터 정렬
		combox1.setCellFactory(new Callback<ListView<ProdVO>, ListCell<ProdVO>>() {

			@Override
			public ListCell<ProdVO> call(ListView<ProdVO> param) {
				
				return new ListCell<ProdVO>() {
					@Override
					protected void updateItem(ProdVO item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
							setText(item.getLprod_nm());
						}
					}; 
				};
			}
		});
			
		// combox1 내용
		combox1.setButtonCell(new ListCell<ProdVO>() {
			@Override
			protected void updateItem(ProdVO item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty) {
					setText(item.getLprod_nm());
				}
			}
		});
	}

	@FXML public void selectedbox1(ActionEvent event) {
		// lpord에서 데이터 받기
		ProdVO pv = combox1.getValue();
		
		List<ProdMainVO> comboxL2 = ps.combox2List(pv.getLprod_gu());
		com2List = FXCollections.observableArrayList(comboxL2);
		combox2.setItems(com2List);
		
		combox2.setCellFactory(new Callback<ListView<ProdMainVO>, ListCell<ProdMainVO>>() {

			@Override
			public ListCell<ProdMainVO> call(ListView<ProdMainVO> param) {
				
				return new ListCell<ProdMainVO>() {
					
					@Override
					protected void updateItem(ProdMainVO item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
							setText(item.getProd_name());
						}
					};
				};
				
			}
			
		});
	
		combox2.setButtonCell(new ListCell<ProdMainVO>() {
			@Override
			protected void updateItem(ProdMainVO item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty) {
					setText(item.getProd_name());
				}
			}
			
		});
	
	}
	@FXML public void selectedbox2(ActionEvent event) {
		ProdMainVO main = combox2.getValue();
		listview = FXCollections.observableArrayList(main);
		tableView.setItems(listview);
		
		colid.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		colLgu.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
		colCost.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
		colSale.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
		colOutlien.setCellValueFactory(new PropertyValueFactory<>("prod_outLine"));
		colBuyer.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
		colDetail.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));
	}
		
}
