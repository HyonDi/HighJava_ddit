package kr.or.ddit.structural.decorator;

/**
 * 실행할 메인있는 부분!
 * @author PC-16
 *
 */
public class DecoratorPatternDemo {
	public static void main(String[] args) {
		Shape circle = new Circle();
		
		Shape redCircle = new RedShapeDecorator(new Circle());
		// 기본스트림: Circle.  보조스트림 : RedShapeDecorator   인것과 비슷하다.
		
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		
		System.out.println("일반 원 그리기 시작...");
		circle.draw();
		System.out.println();
		
		System.out.println("경계선이 빨간색인 원 그리기 시작...");
		redCircle.draw();
		System.out.println();
		
		System.out.println("경계선이 빨간색인 사각형 그리기 시작...");
		redRectangle.draw();
		System.out.println();
	}
}
