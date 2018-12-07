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
		setTitle("출석체크");
		panel = new JPanel();
		week = new JButton("주별 출석체크");
		student = new JButton("학생별 출석체크");

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

	// 특정 주의 모든 학생들의 출결상태
	void WeekCheck(Student[] stu) {
		String[] w = { "1주", "2주", "3주", "4주", "5주", "6주", "7주", "8주", "9주", "10주", "11주", "12주", "13주", "14주", "15주",
				"16주" };
		JComboBox<String> list = new JComboBox<>(w);
		check = new JButton("확인");
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

				v.add(new JLabel("학번               이름"));

				for (int i = 0; i <stu.length; i++) {
					int a[][] = stu[i].getAttendance();
					if(a == null) a = new int[16][];
					if(a[list.getSelectedIndex()] == null) a[list.getSelectedIndex()] = new int[] { 2, 2 };
					String tue = "", thr = "";

					switch (a[list.getSelectedIndex()][0]) {
					case 0:
						tue = "출석";
						break;
					case 1:
						tue = "지각";
						break;
					case 2:
						tue = "결석";
						break;
					}
					switch (a[list.getSelectedIndex()][1]) {
					case 0:
						thr = "출석";
						break;
					case 1:
						thr = "지각";
						break;
					case 2:
						thr = "결석";
						break;
					}
					v.add(new JLabel(stu[i].getStudentID() + "     " + stu[i].getName() + "   화요일   " + tue
							+ "   목요일   " + thr));

				}
				//ver.add(v);
				p.add(v, BorderLayout.CENTER);
				f.pack();
			}
		};
		check.addActionListener(c);

		f.pack();

	}

	// 특정 학생의 모든 주(16주) 출결 상태
	void StudentCheck(Student[] stu) {
		String[] w = new String[stu.length];
		for (int i = 0; i < stu.length; i++) {
			w[i] = stu[i].getStudentID() + "  " + stu[i].getName();
		}
		JComboBox<String> list = new JComboBox<>(w);
		check = new JButton("확인");

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
					hor.add(new JLabel((i + 1) + "주차"));
					for (int j = 0; j < 2; j++) {
						if (j == 0) {
							hor.add(new JLabel("화요일 "));
						} else {
							hor.add(new JLabel("목요일 "));
						}
						switch (num[i][j]) {
						case 0:
							label = new JLabel("출석");
							break;
						case 1:
							label = new JLabel("지각");
							break;
						case 2:
							label = new JLabel("결석");
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
