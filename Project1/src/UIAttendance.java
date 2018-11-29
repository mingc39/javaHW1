import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIAttendance extends JFrame {

	private JButton week, student, check;
	private JLabel label;
	private JPanel p;
	private JFrame f;

	public UIAttendance(Student[] stu) {
		f = this;
		setTitle("출석체크");
		p = new JPanel();
		week = new JButton("주별 출석체크");
		student = new JButton("학생별 출석체크");

		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				if (s.equals(week.getText())) {
					WeekCheck(stu);
				} else if (s.equals(student.getText())) {
					StudentCheck(stu);
				}
			}
		};

		week.addActionListener(al);
		student.addActionListener(al);
		p.add(week);
		p.add(student);
		
		this.add(p);

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
//		p.add(list);
//		p.add(check);
		p.add(ver,BorderLayout.SOUTH);
		this.add(p);
		f.pack();

	}
}
