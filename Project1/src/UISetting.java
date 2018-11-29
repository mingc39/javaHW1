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

// UISetting.java
// 비율 설정 창
public abstract class UISetting extends JDialog {
	
	private static final long serialVersionUID = 7691434086657835506L;
	protected double[] raito;
	protected String[] name;
	private JTextField[] textFields;
	protected StudentTable studentTable;
	
	// 생성자
	public UISetting(StudentTable studentTable) {
		
		// 창 제목 설정
		setTitle("학점 비율 설정");
		// 레이 아웃 설정
		setLayout(new BorderLayout());
		
		// 변수
		JPanel center, south, panel2;
		JPanel panel = new JPanel();
		JButton button;
		Listener listener = new Listener();
		
		this.studentTable = studentTable;
		
		setName();
		setData();
		
		textFields = new JTextField[raito.length];
		
		// 중앙 패널 생성
		center = new JPanel(new GridLayout(0, 1));
		
		for(int i = 0; i < name.length; i++) {
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
	
	// 새로운 설정 적용
	protected abstract void applySetting();
	// 항목 이름 가져오기
	protected abstract void setName();
	// 기존 설정값 가져오기
	protected abstract void setData();
	
	// 버튼 액션 리스너
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()) {
			case "확인":
				double total = 0;
				try {
					// 새로운 설정 값들이 유효한지 확인
					for(int i = 0; i < raito.length; i++) raito[i] = Double.parseDouble(textFields[i].getText());
					for(double d : raito) {
						if((d > 1) || (d < 0)) throw new ScoreRangeException();
						total += d;
					}
					if(total != 1) throw new ScoreRangeException();
					
					// 새로운 설정 적용
					applySetting();
					studentTable.refresh();
					
					// 창 닫기
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
