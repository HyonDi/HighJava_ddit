package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.ProdService;
import service.ProdServiceImpl;
import vo.ProdVO;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ProdController implements Initializable{
	
	@FXML ComboBox<ProdVO> com1;
	@FXML ComboBox<ProdVO> com2;
	@FXML TableView<ProdVO> table;
	@FXML TableColumn<ProdVO, String> prod_id;
	@FXML TableColumn<ProdVO, String> prod_name;
	@FXML TableColumn<ProdVO, String> prod_lgu;
	@FXML TableColumn<ProdVO, String> prod_buyer;
	@FXML TableColumn<ProdVO, Integer> prod_cost;
	@FXML TableColumn<ProdVO, Integer> prod_price;
	@FXML TableColumn<ProdVO, Integer> prod_sale;
	@FXML TableColumn<ProdVO, String> prod_outline;
	@FXML TableColumn<ProdVO, String> prod_detail;

	private ObservableList<ProdVO> com1Data;
	private ObservableList<ProdVO> com2Data;
	private ObservableList<ProdVO> listView;
	
	ProdService service = ProdServiceImpl.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		prod_lgu.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
		prod_buyer.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
		prod_cost.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
		prod_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
		prod_sale.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
		prod_outline.setCellValueFactory(new PropertyValueFactory<>("prod_outline"));
		prod_detail.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));
		
		com1Data = FXCollections.observableArrayList(service.getCom1List());
		com1.setItems(com1Data);
		
		//combo1의 데이터를 정렬
		com1.setCellFactory(new Callback<ListView<ProdVO>, ListCell<ProdVO>>() {
			
			@Override
			public ListCell<ProdVO> call(ListView<ProdVO> param) {
				return new ListCell<ProdVO>() {
					@Override
					protected void updateItem(ProdVO item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
							setText(item.getLprod_nm());
						}
					}
				};
			}
		});
		
		//바깥에 보이는 com1의 내용
		com1.setButtonCell(new ListCell<ProdVO>() {
			@Override
			protected void updateItem(ProdVO item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty) {
					setText(item.getLprod_nm());
				}
			}
		});
		
	}

	@FXML public void com1Selected(ActionEvent event) {
		//lprod에서 데이터 받기
		ProdVO pv = com1.getValue();
		
		//combo2에 prod_lgu = #lg#에 들어갈 lprod 데이터를 보내서 같은 값의 데이터를 받음
		com2Data = FXCollections.observableArrayList(service.getCom2List(pv.getLprod_gu()));
		com2.setItems(com2Data);
		
		//combo2에 데이터 정렬
		com2.setCellFactory(new Callback<ListView<ProdVO>, ListCell<ProdVO>>() {

			@Override
			public ListCell<ProdVO> call(ListView<ProdVO> param) {
				return new ListCell<ProdVO>(){
					@Override
					protected void updateItem(ProdVO item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
							setText(item.getProd_name());
						}
					}
				};
			}
		});
		
		//바깥에 보이는 com2의 내용
		com2.setButtonCell(new ListCell<ProdVO>() {
			@Override
			protected void updateItem(ProdVO item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty) {
					setText(item.getProd_name());
				}
			}
		});
	}

	@FXML public void com2Selected(ActionEvent event) {
		//테이블에 리스트 출력하기
		ProdVO pv2 = com2.getValue();
		listView = FXCollections.observableArrayList(pv2);
		table.setItems(listView);
	}

	

}
