import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class UIGraph extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -238794829285569602L;

	public UIGraph() {
		
		setTitle("그래프");
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JLabel("여기에 그래프를 그립시다."));
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
		
	}

}
