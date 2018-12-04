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
		int[][] attendance = new int[16][2];
		int[] scores = new int[8];
		for(int i = 1; i <= num; i++) {
			for(int j = 0; j < 16; j++)
				for(int k = 0; k < 2; k++)
					attendance[j][k] = rand.nextInt(3);
			for(int j = 0; j < 8; j++)
				scores[j] = rand.nextInt(101);
			st.addStudent(new Student(rand.nextInt(90000000) + 10000000, String.format("%02d�� �л�", i), scores, attendance ));
		}
		st.refresh();
		return st;
	}

}
