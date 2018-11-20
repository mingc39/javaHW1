import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// UIInput.java
// �Է� â
public class UIInput extends JFrame {
	
	// ���� ����
	private static final long serialVersionUID = 5688138324038957536L;
	private int row;
	private JLabel info;
	private JTextField studentID, name, attendance, midTest, finalTest, homework, quiz, pt, report, others;
	protected LinkedList<StudentEventListener> listeners;
	private UIInputMode mode;

	// ������
	// ���� ��� ������
	public UIInput(Student student, int row) {
		
		// â ����
		this();
		setTitle("�л� ����");
		
		// ���� ����
		this.row = row;
		mode = UIInputMode.EDIT;
		studentID.setText(Integer.toString(student.getStudentID()));
		name.setText(student.getName());
		attendance.setText(Integer.toString(student.getAttendance()));
		midTest.setText(Integer.toString(student.getMidTest()));
		finalTest.setText(Integer.toString(student.getFinalTest()));
		homework.setText(Integer.toString(student.getHomework()));
		quiz.setText(Integer.toString(student.getQuiz()));
		pt.setText(Integer.toString(student.getPt()));
		report.setText(Integer.toString(student.getReport()));
		others.setText(Integer.toString(student.getOthers()));
		info.setText(row + "�� �л��� �����մϴ�.");
		
	}
	// �߰� ��� ������
	public UIInput() {
		
		// â ���� ����
		setTitle("�л� �߰�");
		// ���� �ƿ� ����
		setLayout(new BorderLayout());
		
		// ����
		JPanel center, south;
		JPanel panel, panel2;
		JLabel label;
		JTextField text;
		JButton button;
		Listener listener = new Listener();

		listeners = new LinkedList<>();
		mode = UIInputMode.ADD;
		
		// �߾� �г� ����
		center = new JPanel(new GridLayout(3, 1));
		
		// ù° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		label = new JLabel("�� �л��� �߰��մϴ�.");
		info = label;
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
		
		// ��° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�⼮");
		panel2.add(label);
		text = new JTextField(5);
		attendance = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�߰� ����");
		panel2.add(label);
		text = new JTextField(5);
		midTest = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�⸻ ����");
		panel2.add(label);
		text = new JTextField(5);
		finalTest = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		homework = text;
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// ��° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		quiz = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("��ǥ ����");
		panel2.add(label);
		text = new JTextField(5);
		pt = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		report = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("��Ÿ ����");
		panel2.add(label);
		text = new JTextField(5);
		others = text;
		panel2.add(text);
		panel.add(panel2);
		
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
		setResizable(false);
		setVisible(true);
		
	}
	
	// �̺�Ʈ ������ �߰�
	public void addStudentEventListener(StudentEventListener l) {
		listeners.add(l);
	}
	
	// �̹�Ʈ ������ ����
	public void removeStudentEventListener(StudentEventListener l) {
		listeners.remove(l);
	}
	
	// �̺�Ʈ �߻�
	protected void fireStudentEvent(Student student) {
		StudentEvent e;
		if((mode == UIInputMode.EDIT) || (mode == UIInputMode.DELETE)) e = new StudentEvent(this, student, mode, row);
		else e = new StudentEvent(this, student, mode);
		
		Iterator<StudentEventListener> l = listeners.iterator();
		while(l.hasNext()) {
			((StudentEventListener)l.next()).studentEvent(e);
		}
	}
	
	// ��ư �׼� ������
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()) {
			case "Ȯ��":
				try {
					fireStudentEvent(new Student(Integer.parseInt(studentID.getText()), name.getText(), Integer.parseInt(attendance.getText()), Integer.parseInt(midTest.getText()), Integer.parseInt(finalTest.getText()), Integer.parseInt(homework.getText()), Integer.parseInt(quiz.getText()), Integer.parseInt(pt.getText()), Integer.parseInt(report.getText()), Integer.parseInt(others.getText())));
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
				if(mode == UIInputMode.ADD) dispose();
				// Yes or Yes! - "NO �������� ���߹��� ���մϴ�" - https://youtu.be/mAKsZ26SabQ
				else if(JOptionPane.showOptionDialog(null, "������ " + (row + 1) + "�� �л��� �����Ͻðڽ��ϱ�?", "�л� ����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] {"��(Y)", "��(Y)"}, "��(Y)") != JOptionPane.CLOSED_OPTION) {
				//else if(JOptionPane.showConfirmDialog(null, "������ " + (row + 1) + "�� �л��� �����Ͻðڽ��ϱ�?", "�л� ����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					mode = UIInputMode.DELETE;
					fireStudentEvent(null);
					dispose();
				}
				break;
			case "�⼮":
				break;
			}
		}
	}

}
