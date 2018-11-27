import java.util.Random;

// Main.java
public class Main {
	
	public static void main(String[] args) {
		
		//new UIMain(new StudentTable());
		// TODO �� �κ��� ���� �ڵ�, �Ʒ� �κ��� �׽�Ʈ�� ���� 30���� �л� ������ ���Ƿ� ����
		new UIMain(testData(new StudentTable(), 30));
		
	}
	
	// �־��� �� ��ŭ�� �л��� ���Ƿ� �����Ͽ� �־��� StudentTable�� �߰��� �� ��ȯ
	public static StudentTable testData(StudentTable st, int num) {
		Random rand = new Random();
		for(int i = 1; i <= num; i++)
			st.addStudent(new Student(rand.nextInt(90000000) + 10000000, i + "�� �л�", rand.nextInt(101), rand.nextInt(101), rand.nextInt(101),
					rand.nextInt(101), rand.nextInt(101), rand.nextInt(101), rand.nextInt(101), rand.nextInt(101) ));
		st.refresh();
		return st;
	}

}
