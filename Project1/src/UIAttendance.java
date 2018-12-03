import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIAttendance extends JFrame {

	private static final long serialVersionUID = -4385594165417421902L;
	private JButton week, student, check;
	private JLabel label;
	private JPanel p, panel;
	private JFrame f;


	public UIAttendance(Student[] stu) {
		f = this;
		setTitle("�⼮üũ");
		panel = new JPanel();
		week = new JButton("�ֺ� �⼮üũ");
		student = new JButton("�л��� �⼮üũ");

		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				if (s.equals(week.getText())) {
					if (p != null) {
						p.removeAll();
					}
					WeekCheck(stu);
				} else if (s.equals(student.getText())) {
					if (p != null) {
						p.removeAll();
					}
					StudentCheck(stu);

				}
			}
		};

		week.addActionListener(al);
		student.addActionListener(al);
		panel.add(week);
		panel.add(student);

		this.add(panel);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		setVisible(true);
	}

	// Ư�� ���� ��� �л����� ������
	void WeekCheck(Student[] stu) {
		String[] w = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��", "13��", "14��", "15��",
				"16��" };
		JComboBox<String> list = new JComboBox<>(w);
		check = new JButton("Ȯ��");
		p = new JPanel();

		Box h = Box.createHorizontalBox();
		h.add(list);
		h.add(check);

		Box ver = Box.createVerticalBox();
		ver.add(h);
		p.add(ver);

		Box v = Box.createVerticalBox();
		ActionListener c = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (v != null) {
					v.removeAll();
				}

				v.add(new JLabel("�й�               �̸�"));

				for (int i = 0; i <stu.length; i++) {
					int a[][] = stu[i].getAttendance();
					String tue = "", thr = "";

					switch (a[list.getSelectedIndex()][0]) {
					case 0:
						tue = "�⼮";
						break;
					case 1:
						tue = "����";
						break;
					case 2:
						tue = "�Ἦ";
						break;
					}
					switch (a[list.getSelectedIndex()][1]) {
					case 0:
						thr = "�⼮";
						break;
					case 1:
						thr = "����";
						break;
					case 2:
						thr = "�Ἦ";
						break;
					}
					v.add(new JLabel(stu[i].getStudentID() + "     " + stu[i].getName() + "   ȭ����   " + tue
							+ "   �����   " + thr));

				}
				ver.add(v);
				p.add(v);
				f.pack();
			}
		};
		check.addActionListener(c);

		add(p, BorderLayout.SOUTH);
		f.pack();

	}

	// Ư�� �л��� ��� ��(16��) ��� ����
	void StudentCheck(Student[] stu) {
		String[] w = new String[stu.length];
		for (int i = 0; i < stu.length; i++) {
			w[i] = stu[i].getStudentID() + "  " + stu[i].getName();
		}
		JComboBox<String> list = new JComboBox<>(w);
		check = new JButton("Ȯ��");
		p = new JPanel();

		Box h = Box.createHorizontalBox();
		Box ver = Box.createVerticalBox();

		h.add(list);
		h.add(check);

		ver.add(h);

		p.add(ver);
		
		Box v = Box.createVerticalBox();
		ver.add(v);
		ActionListener c = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num[][] = stu[list.getSelectedIndex()].getAttendance();
				Box hor=Box.createHorizontalBox();
				
				for (int i = 0; i < 16; i++) {
					hor.add(new JLabel((i + 1) + "����"));
					for (int j = 0; j < 2; j++) {
						if (j == 0) {
							hor.add(new JLabel("ȭ���� "));
						} else {
							hor.add(new JLabel("����� "));
						}
						switch (num[i][j]) {
						case 0:
							label = new JLabel("�⼮");
							break;
						case 1:
							label = new JLabel("����");
							break;
						case 2:
							label = new JLabel("�Ἦ");
							break;
						}
						hor.add(label);
					}
					v.add(hor);
					
				}
				p.add(v);
				f.pack();
			}
		};
		check.addActionListener(c);

		this.add(p, BorderLayout.SOUTH);
		f.pack();
	}
}
