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
// 입력 창
public class UIInput extends JFrame {
	
	private static final long serialVersionUID = 5688138324038957536L;
	private JTextField studentID, name, attendance, midTest, finalTest, homework, quiz, pt, report, others;
	protected LinkedList<StudentEventListener> listeners;

	public UIInput() {
		
		// 창 제목 설정
		setTitle("입력");
		// 레이 아웃 설정
		setLayout(new BorderLayout());
		
		// 변수
		JPanel center, south;
		JPanel panel, panel2;
		JLabel label;
		JTextField text;
		JButton button;
		Listener listener = new Listener();

		listeners = new LinkedList<>();
		
		// 중앙 패널 생성
		center = new JPanel(new GridLayout(3, 1));
		
		// 첫째 줄
		panel = new JPanel();
		label = new JLabel("학번");
		panel.add(label);
		text = new JTextField(10);
		studentID = text;
		panel.add(text);
		label = new JLabel("이름");
		panel.add(label);
		text = new JTextField(10);
		name = text;
		panel.add(text);
		button = new JButton("출석");
		panel.add(button);
		center.add(panel);
		
		// 둘째 줄
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("출석");
		button.addActionListener(listener);
		panel2.add(label);
		text = new JTextField(5);
		attendance = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("중간 시험");
		panel2.add(label);
		text = new JTextField(5);
		midTest = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("기말 시험");
		panel2.add(label);
		text = new JTextField(5);
		finalTest = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("과제 점수");
		panel2.add(label);
		text = new JTextField(5);
		homework = text;
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// 셋째 줄
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("퀴즈 점수");
		panel2.add(label);
		text = new JTextField(5);
		quiz = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("발표 점수");
		panel2.add(label);
		text = new JTextField(5);
		pt = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("보고서 점수");
		panel2.add(label);
		text = new JTextField(5);
		report = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("기타 점수");
		panel2.add(label);
		text = new JTextField(5);
		others = text;
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// 아래쪽 패널 생성
		south = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		button = new JButton("확인");
		button.addActionListener(listener);
		south.add(button);
		button = new JButton("취소");
		button.addActionListener(listener);
		south.add(button);
		
		// 프레임에 패널 추가
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		// 창 기본 설정
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
			case "확인":
				fireStudentEvent(new Student(Integer.parseInt(studentID.getText()), name.getText(), Integer.parseInt(attendance.getText()), Integer.parseInt(midTest.getText()), Integer.parseInt(finalTest.getText()), Integer.parseInt(homework.getText()), Integer.parseInt(quiz.getText()), Integer.parseInt(pt.getText()), Integer.parseInt(report.getText()), Integer.parseInt(others.getText())));
				dispose();
				break;
			case "취소":
				dispose();
				break;
			case "출석":
				break;
			}
			
		}
		
	}

}
