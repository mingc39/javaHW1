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
import javax.swing.JPanel;
import javax.swing.JTextField;

// UIInput.java
// �Է� â
public class UIInput extends JFrame {
	
	private static final long serialVersionUID = 5688138324038957536L;
	private JTextField studentID, name, attendance, midTest, finalTest, homework, quiz, pt, report, others;
	protected LinkedList<StudentEventListener> listeners;

	public UIInput() {
		
		// â ���� ����
		setTitle("�Է�");
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
		
		// �߾� �г� ����
		center = new JPanel(new GridLayout(3, 1));
		
		// ù° ��
		panel = new JPanel();
		label = new JLabel("�й�");
		panel.add(label);
		text = new JTextField(10);
		studentID = text;
		panel.add(text);
		label = new JLabel("�̸�");
		panel.add(label);
		text = new JTextField(10);
		name = text;
		panel.add(text);
		button = new JButton("�⼮");
		panel.add(button);
		center.add(panel);
		
		// ��° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�⼮");
		button.addActionListener(listener);
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
	
	public void addStudentEventListener(StudentEventListener l) {
		listeners.add(l);
	}
	
	public void removeStudentEventListener(StudentEventListener l) {
		listeners.remove(l);
	}
	
	protected void fireStudentEvent(Student student) {
		StudentEvent e = new StudentEvent(this, student);
		Iterator<StudentEventListener> l = listeners.iterator();
		while(l.hasNext()) {
			((StudentEventListener)l.next()).studentEvent(e);
		}
	}
	
	class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			switch(((JButton)e.getSource()).getText()) {
			case "Ȯ��":
				fireStudentEvent(new Student(Integer.parseInt(studentID.getText()), name.getText(), Integer.parseInt(attendance.getText()), Integer.parseInt(midTest.getText()), Integer.parseInt(finalTest.getText()), Integer.parseInt(homework.getText()), Integer.parseInt(quiz.getText()), Integer.parseInt(pt.getText()), Integer.parseInt(report.getText()), Integer.parseInt(others.getText())));
				dispose();
				break;
			case "���":
				dispose();
				break;
			case "�⼮":
				break;
			}
			
		}
		
	}

}
