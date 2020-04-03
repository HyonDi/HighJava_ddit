package kr.or.ddit.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class Meal {
	// 복합객체
	private List<Item> items = new ArrayList<Item>();
	
	// 항목추가
	public void addItem(Item item) {
		items.add(item);
	}
	
	
	// 가격조회
	public float getCost() {
		float cost = 0.0f;
		
		for(Item item : items) {
			cost += item.price();
		}
		return cost;
	}
	
	// 식사항목 구성 보기
	public void showItems() {
		for(Item item : items) {
			System.out.print("Item : " + item.name());
			System.out.print(", Packing : " + item.packing().pack());
			System.out.println(", Price : " + item.price());
		}
	}
	
}

