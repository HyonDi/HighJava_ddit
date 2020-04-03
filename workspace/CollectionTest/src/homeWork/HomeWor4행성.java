package homeWork;

public class HomeWor4행성 {
/*

원 면적 : 지름제곱 곱하기 3.14
행성의 면적구하기 숙제!
구 겉넓이 : 4*파이*반지릅제곱

 * 	문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	화성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)
	*/
	
	public enum Planet{
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 
		천왕성(25362), 해왕성(24622);
		
		private int 반지름;
		
		private Planet(int data) {
			반지름 = data;
		}
		
		public long get반지름() {
			return 반지름;
		}
		
	}
	
	public static void main(String[] args) {
		Planet[] planetArr = Planet.values();
		for(int i = 0; i < planetArr.length; i++) {
			System.out.println(planetArr[i].name() + "의 면적 \t= " 
		+ Math.round(4*Math.PI*Math.pow(planetArr[i].get반지름(), 2)) + "km2");
		}
	}
	
	/*.values 는 그 객체들을 모두 불러오는 것.
	객체들모음인 planetArr 의 i번째의 이름 : planetArr[i].name()
	planetArr의 괄호속 내용 : planetArr[i].get반지름()
	*/
}
