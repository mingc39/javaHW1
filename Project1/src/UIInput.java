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
// 입력 창
public class UIInput extends JFrame {
	
	// 변수 선언
	private static final long serialVersionUID = 5688138324038957536L;
	private int row;
	private JLabel info;
	private JTextField studentID, name, attendance, midTest, finalTest, homework, quiz, pt, report, others;
	protected LinkedList<StudentEventListener> listeners;
	private UIInputMode mode;

	// 생성자
	// 수정 모드 생성자
	public UIInput(Student student, int row) {
		
		// 창 생성
		this();
		setTitle("학생 수정");
		
		// 변수 설정
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
		info.setText(row + "번 학생을 수정합니다.");
		
	}
	// 추가 모드 생성자
	public UIInput() {
		
		// 창 제목 설정
		setTitle("학생 추가");
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
		mode = UIInputMode.ADD;
		
		// 중앙 패널 생성
		center = new JPanel(new GridLayout(3, 1));
		
		// 첫째 줄
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		label = new JLabel("새 학생을 추가합니다.");
		info = label;
		panel2.add(label);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("학번");
		panel2.add(label);
		text = new JTextField(10);
		studentID = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("이름");
		panel2.add(label);
		text = new JTextField(10);
		name = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		button = new JButton("출석");
		button.addActionListener(listener);
		panel2.add(button);
		button = new JButton("삭제");
		button.addActionListener(listener);
		panel2.add(button);
		panel.add(panel2);
		
		center.add(panel);
		
		// 둘째 줄
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("출석");
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
	
	// 이벤트 리스너 추가
	public void addStudentEventListener(StudentEventListener l) {
		listeners.add(l);
	}
	
	// 이번트 리스너 제거
	public void removeStudentEventListener(StudentEventListener l) {
		listeners.remove(l);
	}
	
	// 이벤트 발생
	protected void fireStudentEvent(Student student) {
		StudentEvent e;
		if((mode == UIInputMode.EDIT) || (mode == UIInputMode.DELETE)) e = new StudentEvent(this, student, mode, row);
		else e = new StudentEvent(this, student, mode);
		
		Iterator<StudentEventListener> l = listeners.iterator();
		while(l.hasNext()) {
			((StudentEventListener)l.next()).studentEvent(e);
		}
	}
	
	// 버튼 액션 리스너
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()) {
			case "확인":
				try {
					fireStudentEvent(new Student(Integer.parseInt(studentID.getText()), name.getText(), Integer.parseInt(attendance.getText()), Integer.parseInt(midTest.getText()), Integer.parseInt(finalTest.getText()), Integer.parseInt(homework.getText()), Integer.parseInt(quiz.getText()), Integer.parseInt(pt.getText()), Integer.parseInt(report.getText()), Integer.parseInt(others.getText())));
					dispose();
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "이름을 제외한 모든 항목은 정수로 입력되어야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} catch(ScoreRangeException exception) {
					JOptionPane.showMessageDialog(null, "점수의 범위를 초과하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "취소":
				dispose();
				break;
			case "삭제":
				if(mode == UIInputMode.ADD) dispose();
				// Yes or Yes! - "NO 선택지는 존중받지 못합니다" - https://youtu.be/mAKsZ26SabQ
				else if(JOptionPane.showOptionDialog(null, "정말로 " + (row + 1) + "번 학생을 삭제하시겠습니까?", "학생 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] {"예(Y)", "예(Y)"}, "예(Y)") != JOptionPane.CLOSED_OPTION) {
				//else if(JOptionPane.showConfirmDialog(null, "정말로 " + (row + 1) + "번 학생을 삭제하시겠습니까?", "학생 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					mode = UIInputMode.DELETE;
					fireStudentEvent(null);
					dispose();
				}
				break;
			case "출석":
				break;
			}
		}
	}

}
