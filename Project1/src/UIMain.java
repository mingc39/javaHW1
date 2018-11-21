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
//����â
public class UIMain extends JFrame {
	
	private static final long serialVersionUID = -9063420066930412578L;
	private boolean viewIndex = false;
	private int nextStudent = 0;
	private double[] raito = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private DefaultTableModel tableModel;
	private JTable table;
	
	public UIMain() {
		
		// â ���� ����
		setTitle("���� ����");
		
		// ǥ ����
		tableModel = new DefaultTableModel(new String[] {"index", "�й�", "�̸�", "�⼮", "�߰� ����", "�⸻ ����", "����", "����", "��ǥ", "����", "��Ÿ", "����", "����"}, 0) {
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
		
		// �޴� �߰�
		menu(tableModel);
		
		// â�� ǥ �߰�
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
		
		// â �⺻ ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		
	}
	
	// �޴� �߰�
	void menu(DefaultTableModel tableModel) {
		
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
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "DB", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "DB ����":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "DB", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "CSV ����":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "CSV", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "CSV ����":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "CSV", JOptionPane.INFORMATION_MESSAGE);
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
		item = new JMenuItem("����");
		item.addActionListener(listener);
		menuFile.add(item);
		
		// =======================<���� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "�Է�":
					new UIInput().addStudentEventListener(new listener());
					break;
				case "����":
					if(table.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "������ �л��� ǥ���� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
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
				case "�˻�":
					// new UISearch(getStudents());
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "�˻�", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "���":
					new UIStatistics(getStudents());
					break;
				case "�⼮ üũ":
					// new UIAttendance(getStudents());
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "�⼮ üũ", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "�ݿ� ����":
					new UIRaito(raito).addStudentEventListener(new listener());
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
		
		// =======================<�׷��� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new UIGraph(getStudents(), ((JMenuItem) (e.getSource())).getText());
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
	
	// ��� �л� ������ Student �迭�� ��ȯ
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
	
	// ���� ���
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
	
	// UIInput ������
	class listener implements StudentEventListener {

		@Override
		public void studentEvent(StudentEvent e) {
			
			// �л� �߰�
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
				
			// �л� ����
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
			
			// �л� ����
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
			
			// ���� �ٽ� ���
			} else {
				int[] scores = new int[8];
				// ��� �л��� ����
				for(int row = 0; row < table.getRowCount(); row++) {
					// �������� �迭�� ��Ƽ�
					for(int i = 0; i < 8; i++)
						scores[i] = (int) tableModel.getValueAt(row, i + 3);
						// scores[i] = Double.parseDouble((String) tableModel.getValueAt(row, i + 3));
					// calScore �Լ��� ����ؼ� ǥ�� ����
					//tableModel.setValueAt(Double.toString(calScore(scores)), row, 11);
					tableModel.setValueAt(calScore(scores), row, 11);
				}
			}
		}
		
	}

}
