import java.util.Random;

// Main.java
public class Main {
	
	public static void main(String[] args) {
		
		//new UIMain(new StudentTable());
		// TODO 윗 부분은 원래 코드, 아래 부분은 테스트를 위해 30명의 학생 정볼르 임의로 생성
		new UIMain(testData(new StudentTable(), 30));
		
	}
	
	// 주어진 수 만큼의 학생을 임의로 생성하여 주어진 StudentTable에 추가한 후 반환
	public static StudentTable testData(StudentTable st, int num) {
		Random rand = new Random();
		for(int i = 1; i <= num; i++)
			st.addStudent(new Student(rand.nextInt(90000000) + 10000000, i + "번 학생", rand.nextInt(101), rand.nextInt(101), rand.nextInt(101),
					rand.nextInt(101), rand.nextInt(101), rand.nextInt(101), rand.nextInt(101), rand.nextInt(101) ));
		st.refresh();
		return st;
	}

}
