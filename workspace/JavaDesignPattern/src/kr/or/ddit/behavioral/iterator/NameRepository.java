package kr.or.ddit.behavioral.iterator;

public class NameRepository implements Container{
		
	public String names[] = {"강현철","이지형","정보람","이병훈","연은주","이승재","김지선"};
	
	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}
	
	// 접근제어를 위한 내부클래스 정의. 구현체.NameIterator() 얘를 만들어. 
	private class NameIterator implements Iterator {

		// 인덱스관리를 위함.
		int index;
		
		@Override
		public boolean hasNext() {
			if(index < names.length) { 
				// name이라는 스트링 배열에 name.length를 체크. length보다 index가 작다 = 가져올것이 있다.
				return true;
			}
			return false;
		}

		@Override
		public Object next() {// 여기선 실제로 값을 꺼내서 String으로 보여주면돼.
			if(this.hasNext()) {
				return names[index++]; // 값을 가져오고 1개 증가시킨다.
			}
			return null;
		}
		
	}

}
