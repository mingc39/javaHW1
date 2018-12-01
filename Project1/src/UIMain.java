import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

//UIMain.java
//메인창
public class UIMain extends JFrame {
	
	private static final long serialVersionUID = -9063420066930412578L;
	private StudentTable studentTable;
	
	public UIMain(StudentTable studentTable) {
		
		// 창 제목 설정
		setTitle("성적 관리");
		
		this.studentTable = studentTable;
		
		// 메뉴 추가
		menu();
		
		// 창에 표 추가
		add(studentTable.getScroll());
		
		// 창 기본 설정
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "정말로 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		setSize(1000,500);
		setVisible(true);
		
	}
	
	// 메뉴 추가
	private void menu() {
		
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
					new UIInput(studentTable);
					break;
				case "수정":
					if(studentTable.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "수정할 학생을 표에서 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					else {
						new UIInput(studentTable, studentTable.getSelectedStudentIndex());
					}
					break;
				case "검색":
					new UISearch(studentTable.getStudents());
					break;
				case "평균":
					new UIStatistics(studentTable);
					break;
				case "출석 체크":
					new UIAttendance(studentTable.getStudents());
					break;
				case "반영 비율":
					//new UIRaito(studentTable);
					new UISetting(studentTable) {

						private static final long serialVersionUID = 3312792879135047715L;

						@Override
						protected void applySetting() {
							studentTable.setRaito(raito);
						}

						@Override
						protected void setName() {
							name = studentTable.getScoreName();
						}

						@Override
						protected void setData() {
							raito = studentTable.getRaito();
						}
						
					};
					break;
				case "학점 비율":
					//new UIGrade(studentTable);
					new UISetting(studentTable) {

						private static final long serialVersionUID = -8526134229675530544L;

						@Override
						protected void applySetting() {
							studentTable.setGrade(raito);
						}

						@Override
						protected void setName() {
							name = studentTable.getGradeName();
						}

						@Override
						protected void setData() {
							raito = studentTable.getGrade();
						}
						
					};
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
		item = new JMenuItem("학점 비율");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('G', Event.CTRL_MASK));
		menuEdit.add(item);
		
		// =======================<그래프 메뉴 생성>=======================
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "기능이 없습니다.", "그래프", JOptionPane.INFORMATION_MESSAGE);
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
	
}
