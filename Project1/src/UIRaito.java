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
// �ݿ� ���� ���� â
public class UIRaito extends JDialog {
	
	private static final long serialVersionUID = -6404147315974902973L;
	private double[] raito;
	private JTextField[] textFields;
	private StudentTable studentTable;
	
	public UIRaito(StudentTable studentTable) {
		
		// â ���� ����
		setTitle("�ݿ� ���� ����");
		// ���� �ƿ� ����
		setLayout(new BorderLayout());
		
		// ����
		JPanel center, south, panel2;
		JPanel panel = new JPanel();
		JButton button;
		Listener listener = new Listener();
		String[] name = studentTable.getScoreName();
		
		raito = studentTable.getRaito();
		this.studentTable = studentTable;
		textFields = new JTextField[raito.length];
		
		// �߾� �г� ����
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
		
		// �Ʒ��� �г� ����
		south = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		button = new JButton("Ȯ��");
		button.addActionListener(listener);
		south.add(button);
		button = new JButton("���");
		button.addActionListener(listener);
		south.add(button);
		
		// �����ӿ� �г� �߰�
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		// â �⺻ ����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	// ��ư �׼� ������
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()) {
			case "Ȯ��":
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
					JOptionPane.showMessageDialog(null, "��� �׸��� �Ǽ��� �ԷµǾ�� �մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				} catch(ScoreRangeException exception) {
					JOptionPane.showMessageDialog(null, "������ ������ �ʰ��Ͽ����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "���":
				dispose();
				break;
			}
		}
	}

}
