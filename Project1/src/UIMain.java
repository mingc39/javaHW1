import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
				if(JOptionPane.showConfirmDialog(null, "정말로 종료하시겠습니까?\n저장하지 않은 내용이 사라질 수 있습니다.", "종료", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		setSize(1000,500);
		setVisible(true);
		
	}
	
	// 메뉴 추가
	private void menu(){
		
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
					if(JOptionPane.showConfirmDialog(null, "저장하지 않은 내용은 사라질 수 있습니다. 계속하시겠습니까?", "DB",
			                  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) break;
					try {
						StudentTable newStudentTable = new StudentTable();
			            Student students[] = SQLmethod.open();
			            if(students == null) {
			            	JOptionPane.showMessageDialog(null, "DB를 열지 못하였습니다.", "DB", JOptionPane.ERROR_MESSAGE);
			            	break;
			            }
			            for(Student s : students) newStudentTable.addStudent(s);
				        new UIMain(newStudentTable);
				        dispose();
					}
					catch(SQLException exp) {
						JOptionPane.showMessageDialog(null, "DB 오류가 발생하였습니다.", "DB", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "DB 저장":
					try {
						SQLmethod.Insert(studentTable.getStudents());
					}
					catch(SQLException exp) {
						JOptionPane.showMessageDialog(null, "DB 오류가 발생하였습니다.", "DB", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "CSV 열기":
		            if(JOptionPane.showConfirmDialog(null, "저장하지 않은 내용은 사라질 수 있습니다. 계속하시겠습니까?", "CSV",
		                  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) break;
		            StudentTable newStudentTable = new StudentTable();
		            Student students[] = CSV.Read();
		            if(students == null) {
		            	JOptionPane.showMessageDialog(null, "파일을 열지 못하였습니다.\n오류가 발생했거나 사용자가 취소했을 수 있습니다.", "CSV", JOptionPane.ERROR_MESSAGE);
		            	break;
		            }
		            for(Student s : students) newStudentTable.addStudent(s);
			        new UIMain(newStudentTable);
			        dispose();
		            break;
				case "CSV 저장":
					CSV.Write(studentTable.getStudents());
					break;
				case "DB 설정":
					JPanel panel = new JPanel(new GridLayout(4, 1));
					JTextField url = new JTextField(SQLmethod.url, 20);
					JTextField username = new JTextField(SQLmethod.user, 20);
					JPasswordField password = new JPasswordField(SQLmethod.password, 20);
					JPasswordField confirm = new JPasswordField(SQLmethod.password, 20);
					JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("서버 주소"));
					panel2.add(url);
					panel.add(panel2);
					panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("사용자 이름"));
					panel2.add(username);
					panel.add(panel2);
					panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("암호"));
					panel2.add(password);
					panel.add(panel2);
					panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("암호 확인"));
					panel2.add(confirm);
					panel.add(panel2);
					if(JOptionPane.showConfirmDialog(null, panel, "DB", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
						if(new String(password.getPassword()).equals(new String(confirm.getPassword())) == false) {
							JOptionPane.showMessageDialog(null, "암호와 암호 확인이 서로 다릅니다.", "DB", JOptionPane.ERROR_MESSAGE);
							break;
						}
						SQLmethod.url = url.getText();
						SQLmethod.user = username.getText();
						SQLmethod.password = new String(password.getPassword());
					}
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
		item = new JMenuItem("DB 설정");
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
				case "삭제":
					if(JOptionPane.showConfirmDialog(null, "정말로 " + studentTable.getSelectedStudent().getName() + " 학생을 삭제하시겠습니까?",
							"학생 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
						studentTable.removeStudent(studentTable.getSelectedStudentIndex());
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
					new UISetting(studentTable, "반영 비율 설정", true) {

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
					new UISetting(studentTable, "학점 비율 설정", true) {

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
				case "출석 설정":
					new UISetting(studentTable, "출결 점수 설정", false) {

						private static final long serialVersionUID = 1429846739837239674L;

						@Override
						protected void applySetting() throws ScoreRangeException {
							for(double d : raito)
								if(d < 0) throw new ScoreRangeException();
							
							int absentLimit = (int) raito[0];
							int lateToAbsent = (int) raito[1];
							double absentSubtract = raito[2];
							double lateSubtract = raito[3];
							
							studentTable.setAbsentLimit(absentLimit);
							studentTable.setLateToAbsent(lateToAbsent);
							studentTable.setAbsentSubtract(absentSubtract);
							studentTable.setLateSubtract(lateSubtract);
						}

						@Override
						protected void setName() {
							name = new String[] { "F처리 결석수", "지각 결석 변환", "결석 감점", "지각 감점" };
						}

						@Override
						protected void setData() {
							raito = new double[] { studentTable.getAbsentLimit(), studentTable.getLateToAbsent(),
									studentTable.getAbsentSubtract(), studentTable.getLateSubtract() };
						}
						
						@Override
						protected void setInfo() {
							info = "<html>"
									+ "값이 양의 정수 또는 0(기능 사용 안함)인 설정 항목<br>"
									+ "- F처리 결석수: 학생의 결석수가 F처리 결석수 이상이면 F학점을 부여합니다.<br>"
									+ "- 지각 결석 변환: 설정 값 만큼의 지각을 1회 결석으로 취급합니다.<br>"
									+ "값이 양의 실수인 설정 항목(출석 점수 계산시에는 총 감점 점수 계산후 정수로 변환되어 계산됩니다.)<br>"
									+ "- 결석 감점: 결석 1회마다 감점시킬 출결 점수입니다.<br>"
									+ "- 지각 감점: 지각 1회마다 감점시킬 출결 점수입니다.<br>"
									+ "</html>";
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
		item = new JMenuItem("삭제");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('D', Event.CTRL_MASK));
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
		item = new JMenuItem("출석 설정");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));
		menuEdit.add(item);
		
		// =======================<그래프 메뉴 생성>=======================
		// 리스너 생성
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "기능이 없습니다.", "그래프", JOptionPane.INFORMATION_MESSAGE);
				new StudentDraw(studentTable.getStudents(), ((JMenuItem)e.getSource()).getText());
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