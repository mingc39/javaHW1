import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// UIInput.java
// 입력 창
public class UIInput extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5688138324038957536L;

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
		
		// 중앙 패널 생성
		center = new JPanel(new GridLayout(3, 1));
		
		// 첫째 줄
		panel = new JPanel();
		label = new JLabel("학번");
		panel.add(label);
		text = new JTextField(10);
		panel.add(text);
		label = new JLabel("이름");
		panel.add(label);
		text = new JTextField(10);
		panel.add(text);
		button = new JButton("출석");
		panel.add(button);
		center.add(panel);
		
		// 둘째 줄
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("출석");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("중간 시험");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("기말 시험");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("과제 점수");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// 셋째 줄
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("퀴즈 점수");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("발표 점수");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("보고서 점수");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("기타 점수");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// 아래쪽 패널 생성
		south = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		button = new JButton("확인");
		south.add(button);
		button = new JButton("취소");
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

}
