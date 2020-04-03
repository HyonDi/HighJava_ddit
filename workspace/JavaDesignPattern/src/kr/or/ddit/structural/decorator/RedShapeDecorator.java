package kr.or.ddit.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator{

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}
	
	@Override
	public void draw() {
		decoratedShape.draw(); // 기본기능.
		setRedBorder(decoratedShape); // 아래선언한 빨강테두리기능을 적용시킨다.
		
	}

	// 새로운 기능! 빨강테두리
	private void setRedBorder(Shape decoratedShape) {
		System.out.println("경계선 색을 빨간색으로 칠하기.");
	}
	
}
