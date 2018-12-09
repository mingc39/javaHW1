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
//����â
public class UIMain extends JFrame {
	
	private static final long serialVersionUID = -9063420066930412578L;
	private StudentTable studentTable;
	
	public UIMain(StudentTable studentTable) {
		
		// â ���� ����
		setTitle("���� ����");
		
		this.studentTable = studentTable;
		
		// �޴� �߰�
		menu();
		
		// â�� ǥ �߰�
		add(studentTable.getScroll());
		
		// â �⺻ ����
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?\n�������� ���� ������ ����� �� �ֽ��ϴ�.", "����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		setSize(1000,500);
		setVisible(true);
		
	}
	
	// �޴� �߰�
	private void menu(){
		
		// ����
		JMenuItem item;
		ActionListener listener;
		
		// �޴� ��, �޴� ��ü ����
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("����");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenu menuEdit = new JMenu("����");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		JMenu menuGraph = new JMenu("�׷���");
		menuGraph.setMnemonic(KeyEvent.VK_G);
		
		// =======================<���� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "DB ����":
					if(JOptionPane.showConfirmDialog(null, "�������� ���� ������ ����� �� �ֽ��ϴ�. ����Ͻðڽ��ϱ�?", "DB",
			                  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) break;
					try {
						StudentTable newStudentTable = new StudentTable();
			            Student students[] = SQLmethod.open();
			            if(students == null) {
			            	JOptionPane.showMessageDialog(null, "DB�� ���� ���Ͽ����ϴ�.", "DB", JOptionPane.ERROR_MESSAGE);
			            	break;
			            }
			            for(Student s : students) newStudentTable.addStudent(s);
				        new UIMain(newStudentTable);
				        dispose();
					}
					catch(SQLException exp) {
						JOptionPane.showMessageDialog(null, "DB ������ �߻��Ͽ����ϴ�.", "DB", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "DB ����":
					try {
						SQLmethod.Insert(studentTable.getStudents());
					}
					catch(SQLException exp) {
						JOptionPane.showMessageDialog(null, "DB ������ �߻��Ͽ����ϴ�.", "DB", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "CSV ����":
		            if(JOptionPane.showConfirmDialog(null, "�������� ���� ������ ����� �� �ֽ��ϴ�. ����Ͻðڽ��ϱ�?", "CSV",
		                  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) break;
		            StudentTable newStudentTable = new StudentTable();
		            Student students[] = CSV.Read();
		            if(students == null) {
		            	JOptionPane.showMessageDialog(null, "������ ���� ���Ͽ����ϴ�.\n������ �߻��߰ų� ����ڰ� ������� �� �ֽ��ϴ�.", "CSV", JOptionPane.ERROR_MESSAGE);
		            	break;
		            }
		            for(Student s : students) newStudentTable.addStudent(s);
			        new UIMain(newStudentTable);
			        dispose();
		            break;
				case "CSV ����":
					CSV.Write(studentTable.getStudents());
					break;
				case "DB ����":
					JPanel panel = new JPanel(new GridLayout(4, 1));
					JTextField url = new JTextField(SQLmethod.url, 20);
					JTextField username = new JTextField(SQLmethod.user, 20);
					JPasswordField password = new JPasswordField(SQLmethod.password, 20);
					JPasswordField confirm = new JPasswordField(SQLmethod.password, 20);
					JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("���� �ּ�"));
					panel2.add(url);
					panel.add(panel2);
					panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("����� �̸�"));
					panel2.add(username);
					panel.add(panel2);
					panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("��ȣ"));
					panel2.add(password);
					panel.add(panel2);
					panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					panel2.add(new JLabel("��ȣ Ȯ��"));
					panel2.add(confirm);
					panel.add(panel2);
					if(JOptionPane.showConfirmDialog(null, panel, "DB", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
						if(new String(password.getPassword()).equals(new String(confirm.getPassword())) == false) {
							JOptionPane.showMessageDialog(null, "��ȣ�� ��ȣ Ȯ���� ���� �ٸ��ϴ�.", "DB", JOptionPane.ERROR_MESSAGE);
							break;
						}
						SQLmethod.url = url.getText();
						SQLmethod.user = username.getText();
						SQLmethod.password = new String(password.getPassword());
					}
					break;
				case "����":
					if(JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
						System.exit(0);
					break;
				}
			}
		};
		// �޴� ���� �� �߰�
		item = new JMenuItem("DB ����");
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("DB ����");
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("CSV ����", KeyEvent.VK_O);
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("CSV ����", KeyEvent.VK_S);
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("DB ����");
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("����");
		item.addActionListener(listener);
		menuFile.add(item);
		
		// =======================<���� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "�Է�":
					new UIInput(studentTable);
					break;
				case "����":
					if(studentTable.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "������ �л��� ǥ���� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
					else {
						new UIInput(studentTable, studentTable.getSelectedStudentIndex());
					}
					break;
				case "����":
					if(JOptionPane.showConfirmDialog(null, "������ " + studentTable.getSelectedStudent().getName() + " �л��� �����Ͻðڽ��ϱ�?",
							"�л� ����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
						studentTable.removeStudent(studentTable.getSelectedStudentIndex());
					break;
				case "�˻�":
					new UISearch(studentTable.getStudents());
					break;
				case "���":
					new UIStatistics(studentTable);
					break;
				case "�⼮ üũ":
					new UIAttendance(studentTable.getStudents());
					break;
				case "�ݿ� ����":
					new UISetting(studentTable, "�ݿ� ���� ����", true) {

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
				case "���� ����":
					new UISetting(studentTable, "���� ���� ����", true) {

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
				case "�⼮ ����":
					new UISetting(studentTable, "��� ���� ����", false) {

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
							name = new String[] { "Fó�� �Ἦ��", "���� �Ἦ ��ȯ", "�Ἦ ����", "���� ����" };
						}

						@Override
						protected void setData() {
							raito = new double[] { studentTable.getAbsentLimit(), studentTable.getLateToAbsent(),
									studentTable.getAbsentSubtract(), studentTable.getLateSubtract() };
						}
						
						@Override
						protected void setInfo() {
							info = "<html>"
									+ "���� ���� ���� �Ǵ� 0(��� ��� ����)�� ���� �׸�<br>"
									+ "- Fó�� �Ἦ��: �л��� �Ἦ���� Fó�� �Ἦ�� �̻��̸� F������ �ο��մϴ�.<br>"
									+ "- ���� �Ἦ ��ȯ: ���� �� ��ŭ�� ������ 1ȸ �Ἦ���� ����մϴ�.<br>"
									+ "���� ���� �Ǽ��� ���� �׸�(�⼮ ���� ���ÿ��� �� ���� ���� ����� ������ ��ȯ�Ǿ� ���˴ϴ�.)<br>"
									+ "- �Ἦ ����: �Ἦ 1ȸ���� ������ų ��� �����Դϴ�.<br>"
									+ "- ���� ����: ���� 1ȸ���� ������ų ��� �����Դϴ�.<br>"
									+ "</html>";
						}
						
					};
					break;
				}
			}
		};
		// �޴� ���� �� �߰�
		item = new JMenuItem("�Է�");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('E', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('D', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("�˻�");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("���");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('T', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("�⼮ üũ");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('U', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("�ݿ� ����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("���� ����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('G', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("�⼮ ����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));
		menuEdit.add(item);
		
		// =======================<�׷��� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "�׷���", JOptionPane.INFORMATION_MESSAGE);
				new StudentDraw(studentTable.getStudents(), ((JMenuItem)e.getSource()).getText());
			}
		};
		// �޴� ���� �� �߰�
		item = new JMenuItem("�⼮");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("�߰� ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("�⸻ ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("���� ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("���� ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("��ǥ ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("��Ÿ ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		
		// �޴� �ٿ� �޴� �߰�
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuGraph);
		setJMenuBar(menuBar);
		
	}
	
}