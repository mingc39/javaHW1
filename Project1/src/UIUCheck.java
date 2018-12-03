import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class UIUCheck extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel p;
	JLabel week, tue, thr;
	ButtonGroup check;
	JRadioButton attend, absent, late; // �⼮ �Ἦ ���� ���� ��ư
	String w = ""; // 1~16�� �� ��Ÿ�� ���ڿ�
	String str = "", result = "";
	Box ver, days, info, box;
	JButton save; // �����ư
	Student student;
	int[][] attendance, a = new int[16][2];
	int tf;
	int nextint = 0;
	int nextint2 = 0;

	// ���� �ִ� ������
	public UIUCheck(int[][] attendance) {
		setTitle("�⼮üũ");

		box = Box.createVerticalBox();
		save = new JButton("����");

		// �л� ��� ���� �޾ƿ���
		/*
		 * ���� ���� attendance: �Ű������� �޾ƿ� ���� �⼮ �� a: ȭ�鿡 ǥ���ϰ� ����ڷκ��� �Է¹��� �⼮ ��
		 */
		this.attendance = attendance;
		a = attendance.clone();
		for (int i = 0; i < 16; i++)
			if (a[i] != null)
				a[i] = a[i].clone();

		for (int i = 1; i < 17; i++) {
			p = new JPanel();
			tue = new JLabel("ȭ����");
			thr = new JLabel("�����");

			// �ش� ������ �⼮ ������ ������ ������ �Ἦ���� �ʱ�ȭ
			if (a[i - 1] == null) {
				a[i - 1] = new int[] { 2, 2 };
			}

			attend = new JRadioButton("�⼮");
			absent = new JRadioButton("�Ἦ               ");
			late = new JRadioButton("����");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);
			// ������ �⼮����� ���� �ڽ��� üũ
			switch (a[i - 1][0]) {
			case 0:
				attend.setSelected(true);
				break;
			case 1:
				late.setSelected(true);
				break;
			case 2:
				absent.setSelected(true);
				break;
			}

			attend.addActionListener(new listener(i - 1, 0));
			absent.addActionListener(new listener(i - 1, 0));
			late.addActionListener(new listener(i - 1, 0));

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
			// ������ �⼮����� ���� �ڽ��� üũ
			switch (a[i - 1][1]) {
			case 0:
				attend.setSelected(true);
				break;
			case 1:
				late.setSelected(true);
				break;
			case 2:
				absent.setSelected(true);
				break;
			}

			attend.addActionListener(new listener(i - 1, 1));
			absent.addActionListener(new listener(i - 1, 1));
			late.addActionListener(new listener(i - 1, 1));

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
				// ���� �⼮�� ���� ȭ�鿡 ǥ�õ� ������ ��ü
				for (int i = 0; i < 16; i++)
					attendance[i] = a[i];
				// stu.editStudent(student, index);
				// System.out.println(stu.getAttendanceScore());

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

	// ���� �ڽ��� ���� �׼� ������
	class listener implements ActionListener {

		// ������ ���� ���� ������ ���� ����
		private int w, c;

		// ������
		public listener(int w, int c) {
			this.w = w;
			this.c = c;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			// ������ ������ ������ �⼮ �� ����
			if (s.equals(attend.getText())) {
				a[w][c] = 0;
			} else if (s.equals(late.getText())) {
				a[w][c] = 1;
			} else if (s.equals(absent.getText())) {
				a[w][c] = 2;
			}
		}

	}

}
