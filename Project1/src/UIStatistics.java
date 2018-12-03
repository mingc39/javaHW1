import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// UIStatistics.java
// 통계 표시 창
public class UIStatistics extends JDialog {
	
	private static final long serialVersionUID = -3217063290629952967L;
	
	public UIStatistics(StudentTable studentTable) {
		
		// 창 제목 설정
		setTitle("통계 정보");
		
		// 변수
		Student[] students = studentTable.getStudents();
		int[] totals;
		int studentCount;
		String[] itemNames = studentTable.getScoreName();;
		
		// 합계 계산
		totals = new int[itemNames.length];
		for(int i = 0; i < totals.length; i++) totals[i] = 0;
		studentCount = students.length;
		int[] scores;
		for(Student s : students) {
			scores = s.getScores();
			for(int i = 0; i < itemNames.length; i++)
				totals[i] += scores[i];
		}
		
		// 통계 정보 출력
		add(new JLabel("<html>통계 정보<br>학생 수: " + studentCount + "</html>", SwingConstants.CENTER), BorderLayout.NORTH);
		
		// 학생이 한 명도 없는 경우 모든 통계를 0으로 표시
		// 학생이 한 명도 없는 경우 모든 합계가 0이므로 학생 수를 1로 바꿔도 평균은 0으로 표시됨. 
		if(studentCount == 0) {
			JOptionPane.showMessageDialog(null, "학생이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			studentCount = 1;
		}
		
		// 평균 계산 및 출력
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		for(int i = 0; i < itemNames.length; i++) {
			panel.add(new JLabel(itemNames[i] + ": ", SwingConstants.RIGHT));
			//panel.add(new JLabel(Double.toString( ((double) totals[i]) / studentCount )));
			panel.add(new JLabel(String.format("%.2f", ((double) totals[i]) / studentCount ) ));
		}
		add(panel, BorderLayout.CENTER);
		
		// 창 기본 설정
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}

}
