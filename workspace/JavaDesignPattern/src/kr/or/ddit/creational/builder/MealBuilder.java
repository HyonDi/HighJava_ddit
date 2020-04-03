package kr.or.ddit.creational.builder;
/**
 * 식사를 만들어줄 빌더
 * 얘가 복합객체를 어떻게 구성할지 하니까 이부분이 빌더패턴. 
 * 복합객체를 생성하는 내용이라  생성패턴으로 들어감.
 * @author PC-16
 *
 */
public class MealBuilder {
	
	// 채식주의자 식사 준비
	public Meal prepareVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new VegBurger());// 밀이라는 객체에 버거라는 객체와 코크라는 객체를 넣었음. = 복합객체. = 밀
		meal.addItem(new Coke());
		
		return meal;
	}
	
	// 비채식주의자 식사 준비
	public Meal prepareNonVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new ChickenBurger());
		meal.addItem(new Pepsi());
		
		return meal;
	}
	
	
	
}
