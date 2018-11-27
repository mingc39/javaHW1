import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// UIStatistics.java
// ��� ǥ�� â
public class UIStatistics extends JDialog {
	
	private static final long serialVersionUID = -3217063290629952967L;
	private int totals[], studentCount;
	private String[] itemNames;
	
	public UIStatistics(Student[] students) {
		
		// â ���� ����
		setTitle("��� ����");
		
		// ��� �׸�
		itemNames = new String[] {
				"�⼮", "�߰� ����", "�⸻ ����", "����", "����", "��ǥ", "����", "��Ÿ"
		};
		// �հ� ���
		totals = new int[itemNames.length];
		for(@SuppressWarnings("unused") int i : totals) i = 0;
		studentCount = students.length;
		int[] scores;
		for(Student s : students) {
			scores = s.getScores();
			for(int i = 0; i < itemNames.length; i++)
				totals[i] += scores[i];
		}
		
		// ��� ���� ���
		add(new JLabel("<html>��� ����<br>�л� ��: " + studentCount + "</html>", SwingConstants.CENTER), BorderLayout.NORTH);
		
		// �л��� �� �� ���� ��� ��� ��踦 0���� ǥ��
		// �л��� �� �� ���� ��� ��� �հ谡 0�̹Ƿ� �л� ���� 1�� �ٲ㵵 ����� 0���� ǥ�õ�. 
		if(studentCount == 0) {
			JOptionPane.showMessageDialog(null, "�л��� �����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			studentCount = 1;
		}
		
		// ��� ��� �� ���
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		for(int i = 0; i < itemNames.length; i++) {
			panel.add(new JLabel(itemNames[i] + ": ", SwingConstants.RIGHT));
			panel.add(new JLabel(Double.toString( (double) (totals[i] / studentCount) )));
		}
		add(panel, BorderLayout.CENTER);
		
		// â �⺻ ����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}

}
