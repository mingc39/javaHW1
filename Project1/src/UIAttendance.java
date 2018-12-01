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

	private JButton week, student, check;
	private JLabel label;
	private JPanel p, panel;
	private JFrame f;

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

		this.add(panel);

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
				label = new JLabel("학번               이름");
				v.add(label);
				for (int i = 0; i < stu.length; i++) {
					label = new JLabel(stu[i].getStudentID() + "     " + stu[i].getName());
					v.add(label);

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

	// 특정 학생의 모든 주(16주) 출결 상태
	void StudentCheck(Student[] stu) {
		String[] w = new String[stu.length];
		for (int i = 0; i < stu.length; i++) {
			w[i] = stu[i].getStudentID() + "  " + stu[i].getName();
		}
		JComboBox<String> list = new JComboBox<>(w);
		check = new JButton("확인");
		p = new JPanel();

		Box h = Box.createHorizontalBox();
		h.add(list);
		h.add(check);
		Box ver = Box.createVerticalBox();
		ver.add(h);

		p.add(ver);
		ActionListener c = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num[][] = stu[list.getSelectedIndex()].getAttendance();
				for(int i=0;i<16;i++) {
					for(int j=0;j<2;j++) {
						switch(num[i][j]) {
						case 0:
							label=new JLabel("출석");
							break;
						case 1:
							label=new JLabel("지각");
							break;
						case 2:
							label=new JLabel("결석");
							break;
						}
						p.add(label);
					}
				}
				
			}
		};
		check.addActionListener(c);

		this.add(p, BorderLayout.SOUTH);
		f.pack();
	}
}
