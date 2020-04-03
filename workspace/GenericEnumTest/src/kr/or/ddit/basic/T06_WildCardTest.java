package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

//특수문자를 와일드문자라고 부른다고  정규표현식의 특수기호????// 

/*
 * 와일드 카드 예제
 * 
  <? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능
  <? super T>   => 와일드 카드의 하한 제한. T와 그 조상들만 가능(Object 제외.) //super 가 생겼다.(Object 제외!!) 
  <?>           => 모든타입이 가능 <? extends Object>와 동일
  
 ※ 와일드카드와 타입파라미터의 특징:
1. 동일한 파라미터 타입으로 강제하고 싶은 경우.
   (타입 파라미터가 한개만 사용될 경우에는 둘 중 아무거나 사용해도 동일함)
ex) public static <T extends Number> void copy(List<T> dest, List<T> src) => 메서드의 파라미터타입을 동일한 타입으로 강제함. (복사대상, 복사원본)
    public static void copy(List<? extends Number> dest, List<? extends Number> src) => 동일 타입으로 강제하지 않음.(unsafe type)

2. Type parameters 는 하한 제한만 가능 (와일드 카드는 상한, 하한 가능)
ex) public void print(List<? super Integer> list)  // OK
    public <T super Integer> void print(List<T> list)  // 컴파일에러

*/

public class T06_WildCardTest {
	public static void main(String[] args) {
		//5. 
		FruitBox<Fruit> fruitBox = new FruitBox<>(); // 과일상자 . 제너릭은 객체를 만드는시점에 타입을 말해줘야한다. 
		FruitBox<Apple> appleBox = new FruitBox<>(); // 사과상자
		
		fruitBox.add(new Apple());//과일박스는 사과, 포도 모두들어갈 수 있다.
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());//사과박스니까 사과만 넣을 수 있다.
		appleBox.add(new Apple());
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice(appleBox); // 와일드카드를 이용한 지네릭타입의 확장으로 에러 해결가능
		
	}
}

/*
 * 쥬서
 */
class Juicer{//6. 
//	static void makeJuice(FruitBox<Fruit> box) { // 제너릭 타입 객체를 파라미터에 사용시 문제점 발생함.
		                                         // 지네릭 타입이 다른 것만으로는 오버로딩이 성립하지 않음 => 컴파일 후 제거됨.=> 메서드 중복정의
		                                         // 와일드 카드를 이용하여 해결가능.
    
	static void makeJuice(FruitBox<? extends Fruit> box) {  // 와일드카드를 이용
//	static <T extends Fruit> void makeJuice(FruitBox<T> box) { // 지네릭 메서드(제한된 타입 파라미터) 이용
															//와일드, 제한된타입파라미터 모두 사용가능!!상위관련된걸 하기위해선 와일드카드만 사용가능하다.
		
		//지네릭이랑 와일드카드 모두 fruitbox가 제너릭클래스여서임
		
		String fruitListStr = ""; // 과일목록 
		
		int cnt = 0; 
		for(Fruit f : box.getFruitList()){
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		
		System.out.println(fruitListStr + "=> 쥬스완성!");
		
	}

}

/*
 * 과일
 *///1.
class Fruit{
	private String name;
    
	public Fruit() {
	}
	
	public Fruit(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "과일(" + name + ")";
	}
}

/*
 * 사과
 *///2. 과일을 익스텐즈해서 만들었다. 부모 : 과일
class Apple extends Fruit {
	public Apple() {
		super("사과");
	}
}

/*
 *  포도
 *///3. 부모 : 과일
class Grape extends Fruit {
	public Grape() {
		super("포도");
	}
}

/*
 * 과일상자
 *///4. 제너릭 클래스. 특정타입만을 담기위해
class FruitBox<T>{
	
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<>();
	}
	
	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}

/*
 * 쥬스
 */
class Juice {

	private String name;

	public Juice(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "쥬스[" + name + "]";
	}
}
