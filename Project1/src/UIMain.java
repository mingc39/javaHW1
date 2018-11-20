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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//UIMain.java
//메인창
public class UIMain extends JFrame {
	
	private static final long serialVersionUID = -9063420066930412578L;
	private DefaultTableModel tableModel;
	private JTable table;
	
	public UIMain() {
		
		// 창 제목 설정
		setTitle("성적 관리");
		
		// 표 생성
		tableModel = new DefaultTableModel(new String[] {"학번", "이름", "출석", "중간 시험", "기말 시험", "과제", "퀴즈", "발표", "보고서", "기타"}, 0) {
			private static final long serialVersionUID = -2265577528898631753L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.setRowSorter(new TableRowSorter<TableModel>(tableModel));
		
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
		
		// 파일 메뉴 생성
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "DB 열기":
					break;
				case "DB 저장":
					break;
				case "CSV 열기":
					break;
				case "CSV 저장":
					break;
				case "종료":
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
		
		// 편집 메뉴 생성
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "입력":
					new UIInput().addStudentEventListener(new Listener());
					break;
				case "수정":
					if(table.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "수정할 학생을 표에서 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					else {
						int row = table.getSelectedRow();
						int[] scores = new int[8];
						for(int i = 0; i < 8; i++) scores[i] = Integer.parseInt((String)table.getValueAt(row, i + 2));
						new UIInput(new Student(Integer.parseInt((String)table.getValueAt(row, 0)), (String)table.getValueAt(row, 1), scores), row).addStudentEventListener(new Listener());;
					}
					break;
				case "검색":
					break;
				case "평균":
					break;
				case "출석 체크":
					break;
				case "반영 비율":
					break;
				}
			}
		};
		// 메뉴 생성 및 추가
		item = new JMenuItem("입력");
		item.addActionListener(listener);
		menuEdit.add(item);
		item = new JMenuItem("수정");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("검색");
		item.addActionListener(listener);
		menuEdit.add(item);
		item = new JMenuItem("평균");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("출석 체크");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("반영 비율");
		item.addActionListener(listener);
		menuEdit.add(item);
		
		// 그래프 메뉴 생성
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new UIGraph();
				/*switch(((JMenuItem) (e.getSource())).getText()) {
				case "입력":
					break;
				case "수정":
					break;
				case "검색":
					break;
				case "평균":
					break;
				case "출석 체크":
					break;
				case "반영 비율":
					break;
				}*/
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
	
	// UIInput 리스너
	class Listener implements StudentEventListener {

		@Override
		public void studentEvent(StudentEvent e) {
			if(e.getUIInputMode() == UIInputMode.ADD) {
				tableModel.addRow(new String[] {Integer.toString(e.getStudent().getStudentID()), e.getStudent().getName(), Integer.toString(e.getStudent().getAttendance()), Integer.toString(e.getStudent().getMidTest()), Integer.toString(e.getStudent().getFinalTest()), Integer.toString(e.getStudent().getHomework()), Integer.toString(e.getStudent().getQuiz()), Integer.toString(e.getStudent().getPt()), Integer.toString(e.getStudent().getReport()), Integer.toString(e.getStudent().getOthers())});
			} else if(e.getUIInputMode() == UIInputMode.EDIT) {
				String[] values = e.getStudent().getValues();
				int row = e.getRow();
				for(int i = 0; i < values.length; i++)
					tableModel.setValueAt(values[i], row, i);
			} else if(e.getUIInputMode() == UIInputMode.DELETE) {
				tableModel.removeRow(e.getRow());
			}
		}
		
	}

}
