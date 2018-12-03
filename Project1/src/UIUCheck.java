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
	JRadioButton attend, absent, late; // 출석 결석 지각 라디오 버튼
	String w = ""; // 1~16주 를 나타낼 문자열
	String str = "", result = "";
	Box ver, days, info, box;
	JButton save; // 저장버튼
	Student student;
	int[][] attendance, a = new int[16][2];
	int tf;
	int nextint = 0;
	int nextint2 = 0;

	// 쓸모가 있는 생성자
	public UIUCheck(int[][] attendance) {
		setTitle("출석체크");

		box = Box.createVerticalBox();
		save = new JButton("저장");

		// 학생 출결 정보 받아오기
		/*
		 * 변수 정보 attendance: 매개변수로 받아온 실제 출석 값 a: 화면에 표시하고 사용자로부터 입력받을 출석 값
		 */
		this.attendance = attendance;
		a = attendance.clone();
		for (int i = 0; i < 16; i++)
			if (a[i] != null)
				a[i] = a[i].clone();

		for (int i = 1; i < 17; i++) {
			p = new JPanel();
			tue = new JLabel("화요일");
			thr = new JLabel("목요일");

			// 해당 주차의 출석 정보가 없으면 생성후 결석으로 초기화
			if (a[i - 1] == null) {
				a[i - 1] = new int[] { 2, 2 };
			}

			attend = new JRadioButton("출석");
			absent = new JRadioButton("결석               ");
			late = new JRadioButton("지각");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);
			// 기존의 출석값대로 라디오 박스에 체크
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

			w = i + "주               ";
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

			attend = new JRadioButton("출석");
			late = new JRadioButton("지각");
			absent = new JRadioButton("결석               ");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);
			// 기존의 출석값대로 라디오 박스에 체크
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
				// 실제 출석부 값을 화면에 표시된 값으로 교체
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

	// 라디오 박스를 위한 액션 리스너
	class listener implements ActionListener {

		// 주차와 수업 요일 저장을 위한 변수
		private int w, c;

		// 생성자
		public listener(int w, int c) {
			this.w = w;
			this.c = c;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			// 지정된 주차와 요일의 출석 값 설정
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
