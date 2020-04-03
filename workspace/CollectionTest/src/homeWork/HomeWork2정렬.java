package homeWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	  Student클래스를 만든다.
	  생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
	  
	  이 Student객체들은 List에 저장하여 관리한다.
	  List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
	  총점의 역순으로 정렬하는 부분을 프로그램 하시오.
	  (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
	  (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
	   총점 정렬기준은 외부클래스에서 제공하도록 한다.)
*/

public class HomeWork2정렬 {

	public static void main(String[] args) {
		HomeWork2정렬 stdTest = new HomeWork2정렬();
		List<Student> stdList = new ArrayList<Student>();
		
		stdList.add(new Student("201411789","강현지", 88, 74, 95));
		stdList.add(new Student("201512345","가나다", 100, 95, 75));
		stdList.add(new Student("201312345","나다라", 85, 74, 98));
		stdList.add(new Student("201212345","다라마", 85, 77, 99));
		
		stdTest.setRanking(stdList);
		
		System.out.println("-----------------정렬전 -----------------");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("=========================================");
		
		Collections.sort(stdList);
		System.out.println("-------------학번의 오름차순 정렬--------------");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("===========================================");
		
		Collections.sort(stdList, new SortByTotal());
		System.out.println("--------------------총점의 내림차순 정렬--------------------");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("=============================================");
		
	}

	
	
	public void setRanking(List<Student> stdList) {
		for(Student std : stdList) { //기준 rank 를 받을 학생.
			int rank = 1;
			for(Student std2 : stdList) { //비교대상.
				if(std.getTotal() < std2.getTotal() ) {
					rank++;
				}
			}
			std.setRank(rank);
		}
		
	}
}

class Student implements Comparable<Student>{
	private String num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int total;
	private int rank;
	
	public Student(String num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.total = kor + eng + mat;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	
	// 학번을 기준으로 오름차순이 되도록 설정하기
	@Override
	public int compareTo(Student std) {
		return num.compareTo(std.getNum());
	}
	
	//출력포맷
	@Override
	public String toString() {
		return "Student [num =" + num + ", name = " + name + ", kor = " + kor
				+", eng = " + eng + ", mat" + mat + ", total =" + total 
				+ ", rank = " + rank + "]";
	}
	
		}


class SortByTotal implements Comparator<Student>{
	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getTotal() == std2.getTotal()) {
			return std1.getNum().compareTo(std2.getNum()) * -1;
		} else {
			return Integer.compare(std1.getTotal(), std2.getTotal()) * -1;
		}

	}
		
}
