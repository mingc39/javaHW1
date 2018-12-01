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
// 입력 창
public class UIInput extends JDialog {
	
	// 변수 선언
	private static final long serialVersionUID = 5688138324038957536L;
	private int index;
	private int[][] attendance;
	private JTextField studentID, name;
	private JTextField[] textFields;
	private StudentTable st;
	private boolean edit = false;

	// 생성자
	// 수정 모드 생성자
	public UIInput(StudentTable st, int index) {
		draw("학생 수정", "학생을 수정합니다.", st.getScoreName());
		this.st = st;
		this.index = index;
		edit = true;
		
		Student student = st.getSelectedStudent();
		studentID.setText(Integer.toString(student.getStudentID()));
		name.setText(student.getName());
		for(int i = 0; i < textFields.length; i++) textFields[i].setText(Integer.toString(student.getScores()[i]));
		attendance = student.getAttendance();
	}
	// 추가 모드 생성자
	public UIInput(StudentTable st) {
		draw("학생 추가", "새 학생을 추가합니다.", st.getScoreName());
		this.st = st;
		edit = false;
	}
	
	// 창 그리기
	private void draw(String title, String info, String[] tableHeader) {
		
		// 창 제목 설정
		setTitle(title);
		// 레이 아웃 설정
		setLayout(new BorderLayout());
		
		// 변수
		JPanel center, south;
		JPanel panel, panel2;
		JLabel label;
		JTextField text;
		JButton button;
		Listener listener = new Listener();

		textFields = new JTextField[tableHeader.length];
		
		// 중앙 패널 생성
		center = new JPanel(new GridLayout(3, 1));
		
		// 첫째 줄
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		label = new JLabel(info);
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
		setVisible(true);
		
	}
	
	// 버튼 액션 리스너
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()) {
			case "확인":
				try {
					int scores[] = new int[textFields.length];
					for(int i = 0; i < textFields.length; i++) scores[i] = Integer.parseInt(textFields[i].getText());
					Student stu = new Student((Integer.parseInt(studentID.getText())), name.getText(), scores, attendance);
					if(edit) st.editStudent(stu, index);
					else st.addStudent(stu);
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
				if(!edit) dispose();
				if(JOptionPane.showConfirmDialog(null, "정말로 " + (st.getSelectedRow() + 1) + "번 학생을 삭제하시겠습니까?", "학생 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					st.removeStudent(index);
					dispose();
				}
				break;
			case "출석":
				if(attendance == null) attendance = new int[16][];
				new UIUCheck(attendance);
				break;
			}
		}
	}

}

