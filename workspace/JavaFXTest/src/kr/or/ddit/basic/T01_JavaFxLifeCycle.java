package kr.or.ddit.basic;
/**
 * JavaFX의 생애주기
 * @author PC-16
 *
 */

import javafx.application.Application;
import javafx.stage.Stage;

/*
 	Stage(무대) => Window창
 	Scene(장면) => 무대에는 하나의 장면이 배치된다.
 	
 	
 	-JavaFx가 실행되는 순서
 	
 	main()메서드 => launch()메서드 => 해당객체의 생성자 메서드
 	=> init()메서드 => star()메서드 => 사용 후 종료
 	=> stop()메서드
 	
 */
public class T01_JavaFxLifeCycle extends Application {
	// 순서대로 적어야하나???
	
	public T01_JavaFxLifeCycle() {
		System.out.println(Thread.currentThread().getName() + " : 생성자 메서드 호출");
									// 쓰레드 이름!
	}
	
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : init() 메서드 호출");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName() + " : start() 메서드 호출");
		primaryStage.show(); // 창보이기
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : stop() 메서드 호출");
	}
	// 얘가 자원반납 등 다함.
	
	

	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " : main() 메서드 호출");
		
		/*Application.*/launch(args);
		//이 페이지에 있으니까 생략가능(Application.)
	}

}
