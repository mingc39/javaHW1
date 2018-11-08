import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// UIInput.java
// �Է� â
public class UIInput extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5688138324038957536L;

	public UIInput() {
		
		// â ���� ����
		setTitle("�Է�");
		// ���� �ƿ� ����
		setLayout(new BorderLayout());
		
		// ����
		JPanel center, south;
		JPanel panel, panel2;
		JLabel label;
		JTextField text;
		JButton button;
		
		// �߾� �г� ����
		center = new JPanel(new GridLayout(3, 1));
		
		// ù° ��
		panel = new JPanel();
		label = new JLabel("�й�");
		panel.add(label);
		text = new JTextField(10);
		panel.add(text);
		label = new JLabel("�̸�");
		panel.add(label);
		text = new JTextField(10);
		panel.add(text);
		button = new JButton("�⼮");
		panel.add(button);
		center.add(panel);
		
		// ��° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�⼮");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�߰� ����");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�⸻ ����");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// ��° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("��ǥ ����");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("��Ÿ ����");
		panel2.add(label);
		text = new JTextField(5);
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// �Ʒ��� �г� ����
		south = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		button = new JButton("Ȯ��");
		south.add(button);
		button = new JButton("���");
		south.add(button);
		
		// �����ӿ� �г� �߰�
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		// â �⺻ ����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		
	}

}
