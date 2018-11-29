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
	JRadioButton attend, absent, late; // 출석 결석 지각 라디오 버튼
	String w = ""; // 1~16주 를 나타낼 문자열
	String str = "", result = "";
	Box ver, days, info, box;
	JButton save; // 저장버튼

	public UIUCheck() {
		setTitle("나는 수정-입력의 출석입니다.");

		box = Box.createVerticalBox();
		save = new JButton("저장");

		for (int i = 1; i < 17; i++) {
			p = new JPanel();
			tue = new JLabel("화요일");
			thr = new JLabel("목요일");

			attend = new JRadioButton("출석");
			absent = new JRadioButton("결석               ");
			late = new JRadioButton("지각");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);

			ActionListener aal = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String s = e.getActionCommand();

					if (s.equals(attend.getText())) {
						str = s;
					} else if (s.equals(absent.getText())) {
						str = s;
					} else if (s.equals(late.getText())) {
						str = s;
					}
					

				}
			};
			attend.addActionListener(aal);
			absent.addActionListener(aal);
			late.addActionListener(aal);

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

			ActionListener alT = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String s = e.getActionCommand();
					if (s.equals(attend.getText())) {
						str = s;
					} else if (s.equals(absent.getText())) {
						str = s;
					} else if (s.equals(late.getText())) {
						str = s;
					}
					
				}
			};
			attend.addActionListener(alT);
			absent.addActionListener(alT);
			late.addActionListener(alT);

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

		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				result += str;
				System.out.println(result);

			}
		};

		save.addActionListener(al);
		this.add(box, BorderLayout.CENTER);
		p = new JPanel();
		p.add(save, BorderLayout.CENTER);
		this.add(p, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		setVisible(true);
	}

	public UIUCheck(int index) {
		setTitle("나는 수정-수정의 출석입니다.");

		box = Box.createVerticalBox();
		save = new JButton("저장");

		for (int i = 1; i < 17; i++) {
			p = new JPanel();
			tue = new JLabel("화요일");
			thr = new JLabel("목요일");

			attend = new JRadioButton("출석");
			absent = new JRadioButton("결석               ");
			late = new JRadioButton("지각");

			check = new ButtonGroup();
			check.add(attend);
			check.add(absent);
			check.add(late);

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

		this.add(box, BorderLayout.CENTER);
		p = new JPanel();
		p.add(save, BorderLayout.CENTER);
		this.add(p, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		setVisible(true);
	}

}
