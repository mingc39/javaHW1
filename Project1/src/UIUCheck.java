import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class UIUCheck extends JFrame {

	JPanel p;
	JLabel week, tue, thr;
	ButtonGroup check;
	JRadioButton attend, absent, late; // �⼮ �Ἦ ���� ���� ��ư
	String w = ""; // 1~16�� �� ��Ÿ�� ���ڿ�
	String str = "", result = "";
	Box ver, days, info, box;
	JButton save; // �����ư
	int[][] a = new int[16][2];
	int tf;
	int nextint = 0;
	int nextint2 = 0;

	public UIUCheck(Student[] stu) {
		setTitle("���� ����-�Է��� �⼮�Դϴ�.");

		box = Box.createVerticalBox();
		save = new JButton("����");

		for (int i = 1; i < 17; i++) {
			p = new JPanel();
			tue = new JLabel("ȭ����");
			thr = new JLabel("�����");

			attend = new JRadioButton("�⼮");
			absent = new JRadioButton("�Ἦ               ");
			late = new JRadioButton("����");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);

			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String s = e.getActionCommand();
					if (s.equals(attend.getText())) {
						tf = 0;
					} else if (s.equals(late.getText())) {
						tf = 1;
					} else if (s.equals(absent.getText())) {
						tf = 2;
					}
					a[nextint++][0] = tf;

				}
			};
			attend.addActionListener(al);
			absent.addActionListener(al);
			late.addActionListener(al);

			w = i + "��               ";
			week = new JLabel(w);

			days = Box.createHorizontalBox();
			days.add(tue);

			ver = Box.createVerticalBox();
			ver.add(days);

			info = Box.createHorizontalBox();
			info.add(attend);
			info.add(late);
			info.add(absent);
			ver.add(info);

			p.add(week);
			p.add(ver);

			days = Box.createHorizontalBox();
			days.add(thr);

			attend = new JRadioButton("�⼮");
			late = new JRadioButton("����");
			absent = new JRadioButton("�Ἦ               ");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);

			ActionListener aal = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String s = e.getActionCommand();
					if (s.equals(attend.getText())) {
						tf = 0;
					} else if (s.equals(late.getText())) {
						tf = 1;
					} else if (s.equals(absent.getText())) {
						tf = 2;
					}
					a[nextint2++][1] = tf;
				}
			};
			attend.addActionListener(aal);
			absent.addActionListener(aal);
			late.addActionListener(aal);

			ver = Box.createVerticalBox();
			ver.add(days);
			info = Box.createHorizontalBox();
			info.add(attend);
			info.add(late);
			info.add(absent);
			ver.add(info);

			p.add(ver);
			box.add(p);
		}

		ActionListener sal = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 2; j++) {
						System.out.println(a[i][j]);
					}
				}
				
				
			}
		};

		save.addActionListener(sal);
		this.add(box, BorderLayout.CENTER);
		p = new JPanel();
		p.add(save, BorderLayout.CENTER);
		this.add(p, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		setVisible(true);
	}

	public UIUCheck(Student[] stu, int index) {
		setTitle("���� ����-������ �⼮�Դϴ�.");

		box = Box.createVerticalBox();
		save = new JButton("����");

		for (int i = 1; i < 17; i++) {
			p = new JPanel();
			tue = new JLabel("ȭ����");
			thr = new JLabel("�����");

			attend = new JRadioButton("�⼮");
			absent = new JRadioButton("�Ἦ               ");
			late = new JRadioButton("����");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);

			ActionListener al = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String s = e.getActionCommand();
					if (s.equals(attend.getText())) {
						tf = 0;
					} else if (s.equals(late.getText())) {
						tf = 1;
					} else if (s.equals(absent.getText())) {
						tf = 2;
					}
					a[nextint++][0] = tf;

				}
			};
			attend.addActionListener(al);
			absent.addActionListener(al);
			late.addActionListener(al);

			w = i + "��               ";
			week = new JLabel(w);

			days = Box.createHorizontalBox();
			days.add(tue);

			ver = Box.createVerticalBox();
			ver.add(days);

			info = Box.createHorizontalBox();
			info.add(attend);
			info.add(late);
			info.add(absent);
			ver.add(info);

			p.add(week);
			p.add(ver);

			days = Box.createHorizontalBox();
			days.add(thr);

			attend = new JRadioButton("�⼮");
			late = new JRadioButton("����");
			absent = new JRadioButton("�Ἦ               ");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);
			
			ActionListener aal = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String s = e.getActionCommand();
					if (s.equals(attend.getText())) {
						tf = 0;
					} else if (s.equals(late.getText())) {
						tf = 1;
					} else if (s.equals(absent.getText())) {
						tf = 2;
					}
					a[nextint2++][1] = tf;
				}
			};
			attend.addActionListener(aal);
			absent.addActionListener(aal);
			late.addActionListener(aal);


			ver = Box.createVerticalBox();
			ver.add(days);
			info = Box.createHorizontalBox();
			info.add(attend);
			info.add(late);
			info.add(absent);
			ver.add(info);

			p.add(ver);
			box.add(p);
		}
		ActionListener sal = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stu[index].setAttandence(a);
//				System.out.println(stu[index].getAttendance());
				System.out.println(stu[index].getAttendanceScore());
			}
		};

		save.addActionListener(sal);


		this.add(box, BorderLayout.CENTER);
		p = new JPanel();
		p.add(save, BorderLayout.CENTER);
		this.add(p, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		setVisible(true);
	}

}
