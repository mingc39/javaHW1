import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private JPanel p = new JPanel(new BorderLayout()), panel;
	private JFrame f;
	private Box box = Box.createHorizontalBox();


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

		this.add(panel, BorderLayout.NORTH);
		this.add(p, BorderLayout.CENTER);

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
		//p = new JPanel();

		JPanel h = new JPanel();
		h.add(list);
		h.add(check);
		p.add(h, BorderLayout.NORTH);

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
					if(a == null) a = new int[16][];
					if(a[list.getSelectedIndex()] == null) a[list.getSelectedIndex()] = new int[] { 2, 2 };
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
				//ver.add(v);
				p.add(v, BorderLayout.CENTER);
				f.pack();
			}
		};
		check.addActionListener(c);

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

		JPanel h = new JPanel(new FlowLayout());
		Box ver = Box.createVerticalBox();

		h.add(list);
		h.add(check);

		p.add(h, BorderLayout.NORTH);

		ActionListener c = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num[][] = stu[list.getSelectedIndex()].getAttendance();
				box.removeAll();
				JPanel hor;
				
				if (ver != null) {
					ver.removeAll();
				}
				
				if(num == null) num = new int[16][];
				for (int i = 0; i < 16; i++) {
					if(num[i] == null) num[i] = new int[] { 2, 2 };
					hor = new JPanel(new FlowLayout());
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
						ver.add(hor);
					}
				}
				p.add(ver, BorderLayout.CENTER);
				f.pack();
			}
		};
		check.addActionListener(c);

		f.pack();
	}
}
