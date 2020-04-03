package kr.or.ddit.creational.builder;

abstract public class Burger implements Item{

// 아이템의메서드를 구현하지않을거야.
	
	@Override
	public Packing packing() {
		
		return new Wrapper();
	}
	
	@Override
	abstract public float price();

}
