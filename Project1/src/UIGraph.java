import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class UIGraph extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -238794829285569602L;

	public UIGraph() {
		
		setTitle("�׷���");
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JLabel("���⿡ �׷����� �׸��ô�."));
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
		
	}

}
