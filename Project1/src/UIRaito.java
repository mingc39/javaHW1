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
// �ݿ� ���� ���� â
public class UIRaito extends JDialog {
	
	private static final long serialVersionUID = -6404147315974902973L;
	@SuppressWarnings("unused")
	private double[] raito;
	private JTextField attendance, midTest, finalTest, homework, quiz, pt, report, others;
	protected LinkedList<StudentEventListener> listeners;
	
	public UIRaito(double[] raito) {
		
		// â ���� ����
		setTitle("�ݿ� ���� ����");
		// ���� �ƿ� ����
		setLayout(new BorderLayout());
		
		// ����
		JPanel center, south;
		JPanel panel, panel2;
		JLabel label;
		JTextField text;
		JButton button;
		Listener listener = new Listener();
		
		listeners = new LinkedList<>();
		
		// �߾� �г� ����
		center = new JPanel(new GridLayout(2, 1));
		
		// ù° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�⼮");
		panel2.add(label);
		text = new JTextField(5);
		attendance = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�߰� ����");
		panel2.add(label);
		text = new JTextField(5);
		midTest = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("�⸻ ����");
		panel2.add(label);
		text = new JTextField(5);
		finalTest = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		homework = text;
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
		// ��° ��
		panel = new JPanel(new GridLayout(1, 4));
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		quiz = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("��ǥ ����");
		panel2.add(label);
		text = new JTextField(5);
		pt = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("���� ����");
		panel2.add(label);
		text = new JTextField(5);
		report = text;
		panel2.add(text);
		panel.add(panel2);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		label = new JLabel("��Ÿ ����");
		panel2.add(label);
		text = new JTextField(5);
		others = text;
		panel2.add(text);
		panel.add(panel2);
		
		center.add(panel);
		
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
		
		attendance.setText(Double.toString(raito[0]));
		midTest.setText(Double.toString(raito[1]));
		finalTest.setText(Double.toString(raito[2]));
		homework.setText(Double.toString(raito[3]));
		quiz.setText(Double.toString(raito[4]));
		pt.setText(Double.toString(raito[5]));
		report.setText(Double.toString(raito[6]));
		others.setText(Double.toString(raito[7]));
		this.raito = raito;
		
		// â �⺻ ����
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	// �̺�Ʈ ������ �߰�
		public void addStudentEventListener(StudentEventListener l) {
			listeners.add(l);
		}
		
		// �̹�Ʈ ������ ����
		public void removeStudentEventListener(StudentEventListener l) {
			listeners.remove(l);
		}
		
		// �̺�Ʈ �߻�
		protected void fireStudentEvent() {
			StudentEvent e = new StudentEvent(this, null, UIInputMode.NONE);
			
			Iterator<StudentEventListener> l = listeners.iterator();
			while(l.hasNext()) {
				((StudentEventListener)l.next()).studentEvent(e);
			}
		}
	
	// ��ư �׼� ������
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()) {
			case "Ȯ��":
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
