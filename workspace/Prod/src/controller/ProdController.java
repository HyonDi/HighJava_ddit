package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class ProdController {
	
	
	// 아이바티스 연결
	
	
/*이것도 있어야해??	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
*/
	@FXML private ComboBox lprodNameBox;
	@FXML private ComboBox prodNameBox;
	@FXML private TableView ProdTbView;
	@FXML private TableColumn p_id;
	@FXML private TableColumn p_name;
	@FXML private TableColumn p_lgu;
	@FXML private TableColumn p_buyer;
	@FXML private TableColumn p_cost;
	@FXML private TableColumn p_price;
	@FXML private TableColumn p_sale;
	@FXML private TableColumn p_outline;
	@FXML private TableColumn p_detail;
	@FXML public void ProdSelect(ActionEvent event) {}
	@FXML public void rodSelect(ActionEvent event) {}

}
