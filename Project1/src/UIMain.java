import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//UIMain.java
//메인창
public class UIMain extends JFrame {
	
	private static final long serialVersionUID = -9063420066930412578L;
	private boolean viewIndex = false;
	private int nextStudent = 0;
	private double[] raito = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private DefaultTableModel tableModel;
	private JTable table;
	
	public UIMain() {
		
		// 창 제목 설정
		setTitle("성적 관리");
		
		// 표 생성
		tableModel = new DefaultTableModel(new String[] {"index", "학번", "이름", "출석", "중간 시험", "기말 시험", "과제", "퀴즈", "발표", "보고서", "기타", "총점", "학점"}, 0) {
			private static final long serialVersionUID = -2265577528898631753L;
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setRowSorter(new TableRowSorter<TableModel>(tableModel));
		if(viewIndex == false) {
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0);
			table.getColumnModel().getColumn(0).setWidth(0);
		}
		
		// 메뉴 추가
		menu(tableModel);
		
		// 창에 표 추가
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
		
		// 창 기본 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		
	}
	
	// 메뉴 추가
	void menu(DefaultTableModel tableModel) {
		
		// 변수
		JMenuItem item;
		ActionListener listener;
		
		// 메뉴 바, 메뉴 객체 생성
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("파일");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenu menuEdit = new JMenu("수정");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		JMenu menuGraph = new JMenu("그래프");
		menuGraph.setMnemonic(KeyEvent.VK_G);
		
		// =======================<파일 메뉴 생성>=======================
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "DB 열기":
					JOptionPane.showMessageDialog(null, "기능이 없습니다.", "DB", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "DB 저장":
					JOptionPane.showMessageDialog(null, "기능이 없습니다.", "DB", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "CSV 열기":
					JOptionPane.showMessageDialog(null, "기능이 없습니다.", "CSV", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "CSV 저장":
					JOptionPane.showMessageDialog(null, "기능이 없습니다.", "CSV", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "종료":
					if(JOptionPane.showConfirmDialog(null, "정말로 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
						System.exit(0);
					break;
				}
			}
		};
		// 메뉴 생성 및 추가
		item = new JMenuItem("DB 열기");
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("DB 저장");
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("CSV 열기", KeyEvent.VK_O);
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("CSV 저장", KeyEvent.VK_S);
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("종료");
		item.addActionListener(listener);
		menuFile.add(item);
		
		// =======================<편집 메뉴 생성>=======================
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "입력":
					new UIInput().addStudentEventListener(new listener());
					break;
				case "수정":
					if(table.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "수정할 학생을 표에서 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					else {
						int row = table.getSelectedRow();
						int[] scores = new int[8];
						//for(int i = 0; i < 8; i++) scores[i] = Integer.parseInt((String)table.getValueAt(row, i + 3));
						for(int i = 0; i < 8; i++) scores[i] = (int) table.getValueAt(row, i + 3);
						/*new UIInput(new Student(Integer.parseInt((String)table.getValueAt(row, 1)), (String)table.getValueAt(row, 2),
								scores),Integer.parseInt((String)table.getValueAt(row, 0))).addStudentEventListener(new listener());*/
						new UIInput(new Student((int) table.getValueAt(row, 1), (String) table.getValueAt(row, 2), scores),
								(int) table.getValueAt(row, 0)).addStudentEventListener(new listener());
					}
					break;
				case "검색":
					// new UISearch(getStudents());
					JOptionPane.showMessageDialog(null, "기능이 없습니다.", "검색", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "평균":
					new UIStatistics(getStudents());
					break;
				case "출석 체크":
					// new UIAttendance(getStudents());
					JOptionPane.showMessageDialog(null, "기능이 없습니다.", "출석 체크", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "반영 비율":
					new UIRaito(raito).addStudentEventListener(new listener());
					break;
				}
			}
		};
		// 메뉴 생성 및 추가
		item = new JMenuItem("입력");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("수정");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('E', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("검색");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("평균");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('T', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("출석 체크");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('U', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("반영 비율");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		menuEdit.add(item);
		
		// =======================<그래프 메뉴 생성>=======================
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new UIGraph(getStudents(), ((JMenuItem) (e.getSource())).getText());
			}
		};
		// 메뉴 생성 및 추가
		item = new JMenuItem("출석");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("중간 시험");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("기말 시험");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("과제 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("퀴즈 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("발표 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("보고서");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("기타 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		
		// 메뉴 바에 메뉴 추가
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuGraph);
		setJMenuBar(menuBar);
		
	}
	
	// 모든 학생 정보를 Student 배열로 반환
	public Student[] getStudents() {
		
		Student[] students = new Student[table.getRowCount()];
		
		int[] scores = new int[8];
		for(int row = 0; row < table.getRowCount(); row++) {
			//for(int i = 0; i < 8; i++) scores[i] = Integer.parseInt((String)table.getValueAt(row, i + 3));
			for(int i = 0; i < 8; i++) scores[i] = (int) table.getValueAt(row, i + 3);
			//students[row] = new Student(Integer.parseInt((String)table.getValueAt(row, 1)), (String)table.getValueAt(row, 2), scores);
			students[row] = new Student((int) table.getValueAt(row, 1), (String)table.getValueAt(row, 2), scores);
		}
		
		return students;
	}
	
	// 총점 계산
	private double calScore(Student student) {
		double score = 0;
		for(int i = 0; i < raito.length; i++)
			score += student.getScores()[i] * raito[i];
		return score;
	}
	private double calScore(int[] scores) {
		double score = 0;
		for(int i = 0; i < raito.length; i++)
			score += (double) scores[i] * raito[i];
		return score;
	}
	
	// UIInput 리스너
	class listener implements StudentEventListener {

		@Override
		public void studentEvent(StudentEvent e) {
			
			// 학생 추가
			if(e.getUIInputMode() == UIInputMode.ADD) {
				/*tableModel.addRow(new String[] {Integer.toString(nextStudent++), Integer.toString(e.getStudent().getStudentID()),
				e.getStudent().getName(), Integer.toString(e.getStudent().getAttendance()), Integer.toString(e.getStudent().getMidTest()),
				Integer.toString(e.getStudent().getFinalTest()), Integer.toString(e.getStudent().getHomework()),
				Integer.toString(e.getStudent().getQuiz()), Integer.toString(e.getStudent().getPt()),
				Integer.toString(e.getStudent().getReport()), Integer.toString(e.getStudent().getOthers()),
				Double.toString(calScore(e.getStudent())) });
				*/
				Student s = e.getStudent();
				tableModel.addRow(new Object[] { nextStudent++, s.getStudentID(), s.getName(), s.getAttendance(), s.getMidTest(),
						s.getFinalTest(), s.getHomework(), s.getQuiz(), s.getPt(), s.getReport(), s.getOthers(), calScore(s) });
				
			// 학생 수정
			} else if(e.getUIInputMode() == UIInputMode.EDIT) {
				//String[] values = e.getStudent().getValues();
				int[] scores = e.getStudent().getScores();
				for(int row = 0; row < table.getRowCount(); row++) {
					/*if(table.getValueAt(row, 0).equals(Integer.toString(e.getRow()))) {
						for(int i = 0; i < values.length; i++)
							tableModel.setValueAt(values[i], row, i + 1);
						tableModel.setValueAt(Double.toString(calScore(e.getStudent())), row, values.length + 1);
						break;
					}*/
					if((int) table.getValueAt(row, 0) == e.getRow()) {
						tableModel.setValueAt(e.getStudent().getStudentID(), row, 1);
						tableModel.setValueAt(e.getStudent().getName(), row, 2);
						for(int i = 0; i < scores.length; i++) {
							tableModel.setValueAt(scores[i], row, i + 3);
						}
						tableModel.setValueAt(calScore(e.getStudent()), row, 11);
						break;
					}
				}
			
			// 학생 삭제
			} else if(e.getUIInputMode() == UIInputMode.DELETE) {
				for(int row = 0; row < table.getRowCount(); row++) {
					/*if(table.getValueAt(row, 0).equals(Integer.toString(e.getRow()))) {
						tableModel.removeRow(row);
						break;
					}*/
					if((int) table.getValueAt(row, 0) == e.getRow()) {
						tableModel.removeRow(row);
						break;
					}
				}
			
			// 총점 다시 계산
			} else {
				int[] scores = new int[8];
				// 모든 학생에 대해
				for(int row = 0; row < table.getRowCount(); row++) {
					// 점수들을 배열로 담아서
					for(int i = 0; i < 8; i++)
						scores[i] = (int) tableModel.getValueAt(row, i + 3);
						// scores[i] = Double.parseDouble((String) tableModel.getValueAt(row, i + 3));
					// calScore 함수로 계산해서 표에 저장
					//tableModel.setValueAt(Double.toString(calScore(scores)), row, 11);
					tableModel.setValueAt(calScore(scores), row, 11);
				}
			}
		}
		
	}

}
