package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class T15_MenuTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		MenuBar menubar = new MenuBar();
		root.setTop(menubar);
		
		// File munu 만들어 보기 => new, save, exit
		Menu fileMenu = new Menu("File"); // 주메뉴
		
		MenuItem newMenuItem = new MenuItem("New"); // 부메뉴
		MenuItem saveMenuItem = new MenuItem("Save"); // 부메뉴
		MenuItem exitMenuItem = new MenuItem("Exit"); // 부메뉴
		
		//메뉴에 이벤트 설정
		exitMenuItem.setOnAction(e->{
			// 해당 메뉴를 클릭했을 때 처리할 내용 기술
			Platform.exit();
		});
		
		// 주 메뉴에 부메뉴들 추가
		fileMenu.getItems().addAll(newMenuItem, saveMenuItem, exitMenuItem);
		//getchildren 해서 넣는것들과 비슷함.
		
		//----------------------------------------------------------------------
		
		Menu webMenu = new Menu("Web");
		
		
		// 메뉴아이템은 그냥 메뉴아이템. 체크메뉴아이템은 메뉴 옆에 체크가 있다.
		CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
		htmlMenuItem.setSelected(false);
		
		
		CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
		cssMenuItem.setSelected(true);// true 로 넣어서 체크되어있게 설정했다.
		
		webMenu.getItems().addAll(htmlMenuItem, cssMenuItem);
		//------------------------------------------------------------------------
		
		
		
		// SQL메뉴에 튜토리얼메뉴를 넣었다. 메뉴에 메뉴아이템, 메뉴 둘 다 넣을 수 있음.????여기아닌듯
		Menu sqlMenu = new Menu("SQL");
		ToggleGroup tgroup = new ToggleGroup();
		
		
		
		//라디오는 무조건 1개만 선택가능한것. = 토글이랑 세트.
		RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
		mysqlItem.setToggleGroup(tgroup);
		mysqlItem.setSelected(true);
		
		RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
		oracleItem.setToggleGroup(tgroup);
		
		RadioMenuItem mssqlItem = new RadioMenuItem("MS-SQL");
		mssqlItem.setToggleGroup(tgroup);
		
		sqlMenu.getItems().addAll(mysqlItem, oracleItem, mssqlItem);
		
		
		
		
		// 부메뉴의 부메뉴 구성하기
		Menu tutorialMenu = new Menu("Tutorial");
		tutorialMenu.getItems().addAll(
				new CheckMenuItem("Java"),
				new CheckMenuItem("JavaFX"),
				new CheckMenuItem("Java Swing")
				);
		
		
		// 구분선넣음.
		sqlMenu.getItems().addAll(new SeparatorMenuItem(), tutorialMenu);
		
		// 주 메뉴들을 MenuBar에 추가시키기
		menubar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
		Scene scene = new Scene(root, 300, 250);
		
		primaryStage.setTitle("메뉴 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
