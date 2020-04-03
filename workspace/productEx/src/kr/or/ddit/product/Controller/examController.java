package kr.or.ddit.product.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.product.Service.ProductService;
import kr.or.ddit.product.Service.ProductServiceImpl;
import kr.or.ddit.product.Vo.ProductInfoVO;
import kr.or.ddit.product.Vo.ProductVO;

public class examController implements Initializable {
	
	@FXML
	private TableView<ProductInfoVO> tableView;
	@FXML
	private ComboBox<ProductVO> combo1;
	@FXML
	private ComboBox<ProductInfoVO> combo2;
    @FXML
    private TableColumn<ProductInfoVO, String> colName;
    @FXML
    private TableColumn<ProductInfoVO, String> colLgu;
    @FXML
    private TableColumn<ProductInfoVO, Integer> colCost;
    @FXML
    private TableColumn<ProductInfoVO, String> colid;
    @FXML
    private TableColumn<ProductInfoVO, Integer> colSale;
    @FXML
    private TableColumn<ProductInfoVO, String> colOutlien;
    @FXML
    private TableColumn<ProductInfoVO, String> colBuyer;
    @FXML
    private TableColumn<ProductInfoVO, String> colDetail;
    ProductService  pdserv = ProductServiceImpl.getInstance();
    ObservableList<ProductVO> obList;
    ObservableList<ProductInfoVO> obifList;
    ObservableList<ProductInfoVO> view;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<ProductVO> compd1 = pdserv.combo1List();
		obList = FXCollections.observableArrayList(compd1);
		obifList=FXCollections.observableArrayList();
		combo1.setItems(obList);
	
		ListView<ProductVO> list = new ListView<ProductVO>();
		
		combo1.setCellFactory(new Callback<ListView<ProductVO>, ListCell<ProductVO>>() {
			
			@Override
			public ListCell<ProductVO> call(ListView<ProductVO> param) {

				return new ListCell<ProductVO>() {
					@Override
					protected void updateItem(ProductVO item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
							setText(item.getLprod_nm());
						}
					};
				};
			}
		});
		combo1.setButtonCell(new ListCell<ProductVO>() {
			@Override
			protected void updateItem(ProductVO item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty) {
					setText(item.getLprod_nm());
				}
			}
		});
	}
	@FXML
	public void selectedbox1(ActionEvent event) {
		System.out.println("!!!!!!!!!!!");
		ProductVO mv = combo1.getValue();
		List<ProductInfoVO> compd2 = pdserv.combo2List(mv.getLprod_gu());
		obifList = FXCollections.observableArrayList(compd2);
		combo2.setItems(obifList);
		
		combo2.setCellFactory(new Callback<ListView<ProductInfoVO>, ListCell<ProductInfoVO>>() {
			
			@Override
			public ListCell<ProductInfoVO> call(ListView<ProductInfoVO> param) {
				
				return new ListCell<ProductInfoVO>() {
					@Override
					protected void updateItem(ProductInfoVO item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
							setText(item.getProd_name());
						}
					};
				};
			}
		});
		combo2.setButtonCell(new ListCell<ProductInfoVO>() {
			@Override
			protected void updateItem(ProductInfoVO item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty) {
					setText(item.getProd_name());
				}
			}
		});
	}
	
	@FXML public void selectedbox2(ActionEvent event) {
		ProductInfoVO info =combo2.getValue();
		view = FXCollections.observableArrayList(info);
		tableView.setItems(view);
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