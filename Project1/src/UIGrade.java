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
public class UIGrade extends JDialog {
	
	private static final long serialVersionUID = -6404147315974902973L;
	private double[] grade;
	private JTextField[] gradeTextField;
	protected LinkedList<StudentEventListener> listeners;
	
	public UIGrade(double[] grade, String[] name, int count) {
		
		// â ���� ����
		setTitle("���� ���� ����");
		// ���� �ƿ� ����
		setLayout(new BorderLayout());
		
		// ����
		JPanel center, south;
		JPanel panel, panel2;
		JButton button;
		Listener listener = new Listener();
		gradeTextField = new JTextField[8];
		
		listeners = new LinkedList<>();
		
		// �߾� �г� ����
		center = new JPanel(new GridLayout(0, 1));
		panel = new JPanel();
		for(int i = 0; i < count; i++) {
			if(i % 4 == 0) panel = new JPanel(new GridLayout(1, 4));
			panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			panel2.add(new JLabel(name[i]));
			gradeTextField[i] = new JTextField(5);;
			panel2.add(gradeTextField[i]);
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
		
		for(int i = 0; i < count; i++)
			gradeTextField[i].setText(Double.toString(grade[i]));
		this.grade = grade;
		
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
				double[] temp = new double[grade.length];
				double total = 0;
				try {
					for(int i = 0; i < grade.length; i++)
						temp[i] = Double.parseDouble(gradeTextField[i].getText());
					for(double d : temp) {
						if((d > 1) || (d < 0)) throw new ScoreRangeException();
						total += d;
					}
					if(total != 1) throw new ScoreRangeException();
					for(int i = 0; i < 8; i++)
						grade[i] = temp[i];
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
