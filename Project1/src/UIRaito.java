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

// UIRaito.java
// 반영 비율 설정 창
public class UIRaito extends JDialog {
	
	private static final long serialVersionUID = -6404147315974902973L;
	private double[] raito;
	private JTextField[] textFields;
	private StudentTable studentTable;
	
	public UIRaito(StudentTable studentTable) {
		
		// 창 제목 설정
		setTitle("반영 비율 설정");
		// 레이 아웃 설정
		setLayout(new BorderLayout());
		
		// 변수
		JPanel center, south, panel2;
		JPanel panel = new JPanel();
		JButton button;
		Listener listener = new Listener();
		String[] name = studentTable.getScoreName();
		
		raito = studentTable.getRaito();
		this.studentTable = studentTable;
		textFields = new JTextField[raito.length];
		
		// 중앙 패널 생성
		center = new JPanel(new GridLayout(2, 1));
		
		for(int i = 0; i < raito.length; i++) {
			if(i % 4 == 0) panel = new JPanel(new GridLayout(1, 4));
			panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			panel2.add(new JLabel(name[i]));
			textFields[i] = new JTextField(5);
			textFields[i].setText(Double.toString(raito[i]));
			panel2.add(textFields[i]);
			panel.add(panel2);
			if(i % 4 == 3) center.add(panel);
		}
		
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
				double total = 0;
				try {
					for(int i = 0; i < raito.length; i++) raito[i] = Double.parseDouble(textFields[i].getText());
					for(double d : raito) {
						if((d > 1) || (d < 0)) throw new ScoreRangeException();
						total += d;
					}
					if(total != 1) throw new ScoreRangeException();
					studentTable.setRaito(raito);
					studentTable.refresh();
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
