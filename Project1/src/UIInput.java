import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// UIInput.java
// �Է� â
public class UIInput extends JDialog {
	
	// ���� ����
	private static final long serialVersionUID = 5688138324038957536L;
	private int index;
	private int[][] attendance;
	private JTextField studentID, name;
	private JTextField[] textFields;
	private StudentTable st;
	private boolean edit = false;

	// ������
	// ���� ��� ������
	public UIInput(StudentTable st, int index) {
		draw("�л� ����", "�л��� �����մϴ�.", st.getScoreName());
		this.st = st;
		this.index = index;
		edit = true;
		
		Student student = st.getSelectedStudent();
		studentID.setText(Integer.toString(student.getStudentID()));
		name.setText(student.getName());
		for(int i = 0; i < textFields.length; i++) textFields[i].setText(Integer.toString(student.getScores()[i]));
		attendance = student.getAttendance();
	}
	// �߰� ��� ������
	public UIInput(StudentTable st) {
		draw("�л� �߰�", "�� �л��� �߰��մϴ�.", st.getScoreName());
		this.st = st;
		edit = false;
	}
	
	// â �׸���
	private void draw(String title, String info, String[] tableHeader) {
		
		// â ���� ����
		setTitle(title);
		// ���� �ƿ� ����
		setLayout(new BorderLayout());
		
		// ����
		JPanel center, south;
		JPanel panel, panel2;
		JLabel label;
		JTextField text;
		JButton button;
		Listener listener = new Listener();

		textFields = new JTextField[tableHeader.length];
		
		// �߾� �г� ����
		center = new JPanel(new GridLayout(3, 1));
		
		// ù° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		label = new JLabel(info);
		panel2.add(label);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�й�");
		panel2.add(label);
		text = new JTextField(10);
		studentID = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�̸�");
		panel2.add(label);
		text = new JTextField(10);
		name = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		button = new JButton("�⼮");
		button.addActionListener(listener);
		panel2.add(button);
		button = new JButton("����");
		button.addActionListener(listener);
		panel2.add(button);
		panel.add(panel2);
		
		center.add(panel);
		
		for(int i = 0; i < tableHeader.length; i++) {
			if(i % 4 == 0) panel = new JPanel(new GridLayout(1, 4));
			panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			panel2.add(new JLabel(tableHeader[i]));
			textFields[i] = new JTextField(5);
			panel2.add(textFields[i]);
			panel.add(panel2);
			if(i % 4 == 3) center.add(panel);
		}
		
		center.add(panel);
		
		// �Ʒ��� �г� ����
		south = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		button = new JButton("Ȯ��");
		button.addActionListener(listener);
		south.add(button);
		button = new JButton("���");
		button.addActionListener(listener);
		south.add(button);
		
		// �����ӿ� �г� �߰�
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		// â �⺻ ����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	// ��ư �׼� ������
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()) {
			case "Ȯ��":
				try {
					int scores[] = new int[textFields.length];
					for(int i = 0; i < textFields.length; i++) scores[i] = Integer.parseInt(textFields[i].getText());
					Student stu = new Student((Integer.parseInt(studentID.getText())), name.getText(), scores, attendance);
					if(edit) st.editStudent(stu, index);
					else st.addStudent(stu);
					dispose();
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "�̸��� ������ ��� �׸��� ������ �ԷµǾ�� �մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				} catch(ScoreRangeException exception) {
					JOptionPane.showMessageDialog(null, "������ ������ �ʰ��Ͽ����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "���":
				dispose();
				break;
			case "����":
				if(!edit) dispose();
				if(JOptionPane.showConfirmDialog(null, "������ " + (st.getSelectedRow() + 1) + "�� �л��� �����Ͻðڽ��ϱ�?", "�л� ����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					st.removeStudent(index);
					dispose();
				}
				break;
			case "�⼮":
				if(attendance == null) attendance = new int[16][];
				new UIUCheck(attendance);
				break;
			}
		}
	}

}

