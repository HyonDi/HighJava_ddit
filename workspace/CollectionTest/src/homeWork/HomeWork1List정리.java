package homeWork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeWork1List정리 {
	public static void main(String[] args) {
		List list1 = new ArrayList();
		
		//add(E e)
		//boolean
		list1.add("aaa");
		list1.add("bbb");
		list1.add("ccc");
		list1.add("ddd");
		list1.add("eee");
		
		
		//add(int index, E element);
		//void
		list1.add(1, 111); //추가될인덱스, 추가할 값.
		System.out.println("인덱스1에 111 add : " + list1);
		
		
		//remove(int index);
		//object
		list1.remove(2); //인덱스삭제
		System.out.println("인덱스2 삭제 : " + list1);
		
		
		//remove(Object o);
		//boolean
		list1.remove("aaa"); //값삭제
		System.out.println("aaa 삭제 : " + list1);		
		
		List<Integer> list2 = new ArrayList<>();
		List<Integer> list3 = new ArrayList<>();
		
		list2.add(111);
		list2.add(222);
		list2.add(333);
		list2.add(444);
		list2.add(555);	
		
		list3.add(666);
		list3.add(777);
		
		
		//addAll(Collection<? extends E> c);
		//boolean
		list2.addAll(list3);
		System.out.println("list2에 list3를 addAll : " + list2);
		
		
		//addAll(int index, Collection<? extends E> c);
		//boolean 
		list2.addAll(2, list3);
		System.out.println("인덱스2에 list3 addAll : " + list2);
		
		
		//removeAll(Collection<?> c);
		//boolean
		System.out.println("removeAll 전 : " + list2);
		list2.removeAll(list3);
		System.out.println("removeAll 후 : " + list2);
		// list 3에 있던 666, 777 삭제됐다.
		
		
		//size();
		//int
		System.out.println("list2 size : " + list2.size());
		
		
		//isEmpty();
		//boolean 
		System.out.println("list2에 값이 있나? : " + list2.isEmpty());
		
		List<String> list4 = new ArrayList<>();
		System.out.println("list4에 값이 있나? : " + list4.isEmpty());
		
		
		//contains(Object o);
		//boolean
		System.out.println("list2가 123 포함? : " + list2.contains(123));
		
		//containsAll(Collection<?> c);
		//boolean
		System.out.println("list2가 list3 포함? : " + list2.containsAll(list3));
		
		
		//iterator();
		//Iterator<E>
		Iterator it = list2.iterator();
		while(it.hasNext()) {//hasNext 는 리턴값 boolean. 다음인덱스 값이 있나?임.
			System.out.println("iterator : " + it.next());
		}
		//다음값이 있을때까지 다음값을 부르는데 마지막거는 왜나오지??
		//4번인덱스 뒤에는 값이 없는데...
		System.out.println("list2 0번 인덱스 : " + list2.get(0));
		System.out.println("list2 4번 인덱스 : " + list2.get(4));
		
		System.out.println(it.hasNext());
		//그냥 이건 false야. 왜??
		System.out.println("--------------------------------------");
		
		//listIterator();
		//ListIterator<E>
		ListIterator it2 = list2.listIterator();
		while(it2.hasNext()){ 
			System.out.println("listIterator 0번인덱스부터: " + it2.next());
		}
		System.out.println();
		while(it2.hasPrevious()){
			System.out.println("listIterator 마지막인덱스부터 : " + it2.previous());
		}
		System.out.println();
		
		
		//listIterator(int index);
		//ListIterator<E>
		ListIterator<Integer> it3 = list2.listIterator(1);
		while(it3.hasNext()){ 
			System.out.println("1번 인덱스 이후부터" + it3.next());
		} 
		System.out.println();
		while(it3.hasPrevious()){ 
			System.out.println("변함없음 : " + it3.previous());
		}
		System.out.println();
		
		System.out.println("----------------------------------------");
		
		//toArray();
		//Object[]
		List<String> strList = new ArrayList<>();
		strList.add("가");
		strList.add("나");
		strList.add("다");
		
		System.out.println("사이즈 : " + strList.size());
		String[] strArray = strList.toArray(new String[1]);
		//크기가 1인 배열에 strList를 담았다.
		System.out.println("크기 1인 배열에 담은 후 사이즈 : " + strArray.length);
		//동일하게 3이나옴. 크기를 맞지 않게 작성해도 모두 담길 수 있도록 해준다.
		
		
		//toArray(T[] a);
		//<T> T[]
		Integer[] intArray = list2.toArray(new Integer[] {1});
		//크기 1인배열에 담았다.
		System.out.println("크기 1인 배열에 담은 후 사이즈 : " + intArray.length);
		//이번에도 전부 출력되도록 5가 나옴.
		
		//boolean retainAll(Collection<?> c);
		List<String> retainAllList = new ArrayList<>();
		retainAllList.add("가");
		retainAllList.add("다");
		
		strList.retainAll(retainAllList);
		System.out.println("retainAll 후 : " + strList);
		//retainAllList에 있는것을 제외하고 모두 삭제.
		
		//equals(Object o);
		//boolean
		System.out.println(list1.equals(strList));
		
		List a = new ArrayList();
		List b = new ArrayList();
		
		a.add("aaa");
		b.add("bbb");
		
		System.out.println("a == b ? " + a.equals(b));
		

		
		//clear();
		//void 
		a.clear();
		System.out.println("a.clear 후 : " + a);
		b.removeAll(b);
		System.out.println("b.removeAll(b) 후 : " + b);
		//remove와 clear 의 차이점 : 파라미터가 있고없고인가??
		System.out.println("-----------------------------------------");
		
		a.add("aaa");
		b.add("aaa");
		
		//hashCode();
		//int 
		System.out.println("a hashcode : " + a.hashCode());
		System.out.println("b hashcode : " + b.hashCode());
		
		List<Integer> aa = new ArrayList<>();
		List<Integer> bb = new ArrayList<>();
		aa.add(123);
		bb.add(123);
		
		System.out.println(aa.hashCode());
		System.out.println(bb.hashCode());
		//같음. 
		
		
		//get(int index);
		//E
		System.out.println(list2.get(0));
		//값 읽어오기.
		
	    //set(int index, E element);
		//E 
		list2.set(0, 12345);
		System.out.println(list2);
		//값 수정하기.

		//indexOf(Object o);
		//int 
		list1.add("fff");
		System.out.println(list1);
		System.out.println("fff의 인덱스번호 : " + list1.indexOf("fff"));
		
		
		//lastIndexOf(Object o);
		//int
		System.out.println("fff lastIndexOf : " + list1.lastIndexOf("fff"));
		//무슨차이지??
		
		
		//subList(int fromindex, int toIndex)
		//List
		System.out.println(list1);
		System.out.println("subList : " + list1.subList(1, 3));
		//1번, 2번인덱스만 출력된다.
		
		//sort(Comparator C)
		//void
		ArrayList<Integer> sortList = new ArrayList<>();
		
		sortList.add(456);
		sortList.add(123);
		sortList.add(678);
		sortList.add(234);
		sortList.add(567);
		sortList.add(345);
		
		System.out.println("sort 전 : " + sortList);
		sortList.sort(null);
		System.out.println("sort 후 : " + sortList);
		
		
		
	}
	

}
