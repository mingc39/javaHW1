import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UISearch extends JFrame {

	private static final long serialVersionUID = 6454243338637096822L;

	private JButton button;
	private JTextField field;
	private JComboBox<String> cb;
	private StudentTable st;
	private String[] list = { "학번", "이름", "출석", "중간 시험", "기말 시험", "과제", "퀴즈", "발표", "보고서", "기타", "총점", "학점" };
	private JFrame f;

	public UISearch(Student[] stu) {
		setTitle("검색");
		f = this;

		showNorth();
		showSouth(stu);
		
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		setVisible(true);
	}

	void showNorth() {
		JPanel p = new JPanel();
		JLabel input = new JLabel("입력 ");

		field = new JTextField(15);
		cb = new JComboBox<>(list);
		button = new JButton("검색");

		p.add(input);
		p.add(field);
		p.add(cb);
		p.add(button);

		this.add(p,BorderLayout.NORTH);
	}

	void showSouth(Student[] stu) {
		JPanel p = new JPanel();
		st = new StudentTable();

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(st!=null) {
					p.removeAll();
					st=new StudentTable();
				}
				for (int i = 0; i <stu.length ; i++) {
					switch (cb.getSelectedIndex()) {
					case 0:
						if (field.getText().equals(String.valueOf(stu[i].getStudentID())))
							st.addStudent(stu[i]);
						break;
					case 1:
						if (field.getText().equals(stu[i].getName())) 
							st.addStudent(stu[i]);
						break;
					case 2:
						if (field.getText().equals(String.valueOf(stu[i].getAttendanceScore())))
							st.addStudent(stu[i]);
						break;
					case 3:
						if (field.getText().equals(String.valueOf(stu[i].getMidTest())))
							st.addStudent(stu[i]);
						break;
					case 4:
						if (field.getText().equals(String.valueOf(stu[i].getFinalTest())))
							st.addStudent(stu[i]);
						break;
					case 5:
						if (field.getText().equals(String.valueOf(stu[i].getHomework())))
							st.addStudent(stu[i]);
						break;
					case 6:
						if (field.getText().equals(String.valueOf(stu[i].getQuiz())))
							st.addStudent(stu[i]);
						break;
					case 7:
						if (field.getText().equals(String.valueOf(stu[i].getPt())))
							st.addStudent(stu[i]);
						break;
					case 8:
						if (field.getText().equals(String.valueOf(stu[i].getReport())))
							st.addStudent(stu[i]);
						break;
					case 9:
						if (field.getText().equals(String.valueOf(stu[i].getOthers())))
							st.addStudent(stu[i]);
						break;
					} 
					
				}
				
				st.refresh();
				p.add(st.getScroll());
				f.pack();
				
			}
		};

		button.addActionListener(al);
		this.add(p, BorderLayout.CENTER);
	}

}
