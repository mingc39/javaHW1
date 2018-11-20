import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// UIRaito.java
// 반영 비율 설정 창
public class UIRaito extends JDialog {
	
	private static final long serialVersionUID = -6404147315974902973L;
	@SuppressWarnings("unused")
	private double[] raito;
	private JTextField attendance, midTest, finalTest, homework, quiz, pt, report, others;
	protected LinkedList<StudentEventListener> listeners;
	
	public UIRaito(double[] raito) {
		
		// 창 제목 설정
		setTitle("반영 비율 설정");
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
		center = new JPanel(new GridLayout(2, 1));
		
		// 첫째 줄
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
		
		// 둘째 줄
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
		
		attendance.setText(Double.toString(raito[0]));
		midTest.setText(Double.toString(raito[1]));
		finalTest.setText(Double.toString(raito[2]));
		homework.setText(Double.toString(raito[3]));
		quiz.setText(Double.toString(raito[4]));
		pt.setText(Double.toString(raito[5]));
		report.setText(Double.toString(raito[6]));
		others.setText(Double.toString(raito[7]));
		this.raito = raito;
		
		// 창 기본 설정
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
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
		protected void fireStudentEvent() {
			StudentEvent e = new StudentEvent(this, null, UIInputMode.NONE);
			
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
				double[] temp = new double[8];
				double total = 0;
				try {
					temp[0] = Double.parseDouble(attendance.getText());
					temp[1] = Double.parseDouble(midTest.getText());
					temp[2] = Double.parseDouble(finalTest.getText());
					temp[3] = Double.parseDouble(homework.getText());
					temp[4] = Double.parseDouble(quiz.getText());
					temp[5] = Double.parseDouble(pt.getText());
					temp[6] = Double.parseDouble(report.getText());
					temp[7] = Double.parseDouble(others.getText());
					for(double d : temp) {
						if((d > 100) || (d < 0)) throw new ScoreRangeException();
						total += d;
					}
					if(total != 1) throw new ScoreRangeException();
					for(int i = 0; i < 8; i++)
						raito[i] = temp[i];
					fireStudentEvent();
					dispose();
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "모든 항목은 실수로 입력되어야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} catch(ScoreRangeException exception) {
					JOptionPane.showMessageDialog(null, "점수의 범위를 초과하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "취소":
				dispose();
				break;
			}
		}
	}

}
