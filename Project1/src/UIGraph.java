import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

// UIGraph.java
// �׷��� â
public class UIGraph extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -238794829285569602L;

	public UIGraph() {
		
		// â ���� ����
		setTitle("�׷���");
		
		// �ӽ� �ؽ�Ʈ
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JLabel("���⿡ �׷����� �׸��ô�."));
		
		// â �⺻ ����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
		
	}

}
