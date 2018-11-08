import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

// UIGraph.java
// 그래프 창
public class UIGraph extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -238794829285569602L;

	public UIGraph() {
		
		// 창 제목 설정
		setTitle("그래프");
		
		// 임시 텍스트
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JLabel("여기에 그래프를 그립시다."));
		
		// 창 기본 설정
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
		
	}

}
