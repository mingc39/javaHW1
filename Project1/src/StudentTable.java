import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

// StudentTable.java
// ����ǥ Ŭ����
public class StudentTable {
	
	// ���� ����
	private boolean viewIndex = false;
	private int nextStudent = 0;
	private double[] raito = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private double[] grade = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private String[] gradeName = {"A+", "A0", "B+", "B0", "C+", "C0", "D", "F"};
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scroll;
	
	// ������
	public StudentTable() {
		
		// ǥ ����
		tableModel = new DefaultTableModel(new String[] {"index", "�й�", "�̸�", "�⼮", "�߰� ����", "�⸻ ����", "����", "����", "��ǥ", "����", "��Ÿ", "����", "����"}, 0) {
			private static final long serialVersionUID = -2265577528898631753L;
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSorter(new TableRowSorter<TableModel>(tableModel));
		if(viewIndex == false) {
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0);
			table.getColumnModel().getColumn(0).setWidth(0);
		}
		scroll = new JScrollPane(table);
		
	}
	
	// getter
	public JScrollPane getScroll() {
		return scroll;
	}
	
	public int getSelectedRow() {
		return table.getSelectedRow();
	}
	
	public Student getStudent(int selectedRow) {
		return new Student((int) table.getValueAt(selectedRow, 1), (String) table.getValueAt(selectedRow, 2), (int) table.getValueAt(selectedRow, 3), (int) table.getValueAt(selectedRow, 4),
				(int) table.getValueAt(selectedRow, 5), (int) table.getValueAt(selectedRow, 6), (int) table.getValueAt(selectedRow, 7), (int) table.getValueAt(selectedRow, 8),
				(int) table.getValueAt(selectedRow, 9), (int) table.getValueAt(selectedRow, 10));
	}
	
	public Student getStudentAt(int selectedRow) {
		return new Student((int) table.getValueAt(selectedRow, 1), (String) table.getValueAt(selectedRow, 2), (int) table.getValueAt(selectedRow, 3), (int) table.getValueAt(selectedRow, 4),
				(int) table.getValueAt(selectedRow, 5), (int) table.getValueAt(selectedRow, 6), (int) table.getValueAt(selectedRow, 7), (int) table.getValueAt(selectedRow, 8),
				(int) table.getValueAt(selectedRow, 9), (int) table.getValueAt(selectedRow, 10));
	}
	
	public Student getSelectedStudent() {
		return getStudent(table.getSelectedRow());
	}
	
	public int getSelectedStudentIndex() {
		return (int) table.getValueAt(table.getSelectedRow(), 0);
	}
	
	public double[] getRaito() {
		return raito;
	}
	
	public double[] getGrade() {
		return grade;
	}
	
	public String[] getGradeName() {
		return gradeName;
	}
	
	// �̺�Ʈ ������ ����
	public StudentEventListener getNewStudentEventListener() {
		return new Listener();
	}
	
	// ��� �л� ������ Student �迭�� ��ȯ
	public Student[] getStudents() {
		
		Student[] students = new Student[table.getRowCount()];
		
		int[] scores = new int[8];
		for(int row = 0; row < table.getRowCount(); row++) {
			for(int i = 0; i < 8; i++) scores[i] = (int) table.getValueAt(row, i + 3);
			students[row] = new Student((int) table.getValueAt(row, 1), (String)table.getValueAt(row, 2), scores);
			students[row].setTotal((double) table.getValueAt(row, 11));
			students[row].setGrade((String) table.getValueAt(row, 12));
		}
		
		return students;
	}
	
	public String toString() {
		String info = "";
		for(int col = 0; col < table.getColumnCount(); col++)
			info += table.getColumnName(col) + "\t";
		for(int row = 0; row < table.getRowCount(); row++) {
			info += "\n";
			for(int col = 0; col < table.getColumnCount(); col++)
				info += table.getValueAt(row, col) + "\t";
		}
		return info;
	}
	
	// �л� �߰�
	public void addStudent(Student s) {
		tableModel.addRow(new Object[] { nextStudent++, s.getStudentID(), s.getName(), s.getAttendance(), s.getMidTest(),
				s.getFinalTest(), s.getHomework(), s.getQuiz(), s.getPt(), s.getReport(), s.getOthers(), calScore(s) });
		
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
	private void calScoreAll() {
		int[] scores = new int[8];
		// ��� �л��� ����
		for(int row = 0; row < table.getRowCount(); row++) {
			// �������� �迭�� ��Ƽ�
			for(int i = 0; i < 8; i++)
				scores[i] = (int) tableModel.getValueAt(row, i + 3);
			// calScore �Լ��� ����ؼ� ǥ�� ����
			tableModel.setValueAt(calScore(scores), row, 11);
		}
	}
	
	// ���� ���
	/*@SuppressWarnings("unused")
	private String calGrade(double score) {
		double[] scores = new double[table.getRowCount()];
		for(int i = 0; i < table.getRowCount(); i++) {
			scores[i] = (double) table.getValueAt(i, 11);
		}
		Arrays.sort(scores);
		for(int i = 0; i < scores.length; i++) {
			if(scores[i] == score) {
				double rank = 1 - (double) i / scores.length;
				double t = 0;
				for(int j = 0; j < grade.length; j++) {
					t += grade[j];
					if(rank <= t) return gradeName[j];
				}
			}
		}
		return null;
	}*/
	private void calGradeAll() {
		
		double[] scores = new double[table.getRowCount()];
		for(int i = 0; i < table.getRowCount(); i++) {
			scores[i] = (double) table.getValueAt(i, 11);
		}
		Arrays.sort(scores);
		double rank, t;
		
		for(int row = 0; row < table.getRowCount(); row++) {
			double score = (double) tableModel.getValueAt(row, 11);
			
			for(int i = 0; i < scores.length; i++) {
				
				if(scores[i] == score) {
					rank = 1 - (double) i / scores.length;
					t = 0;
					for(int j = 0; j < grade.length; j++) {
						t += grade[j];
						if(rank <= t) {
							tableModel.setValueAt(gradeName[j], row, 12);
							break;
						}
					}
				}
				
			}
			
		}
	}
	
	// ����, ���� ���� ��ħ
	public void refresh() {
		calScoreAll();
		calGradeAll();
	}
	
	private class Listener implements StudentEventListener {

		@Override
		public void studentEvent(StudentEvent e) {
			
			// �л� �߰�
			if(e.getUIInputMode() == UIInputMode.ADD) {
				Student s = e.getStudent();
				tableModel.addRow(new Object[] { nextStudent++, s.getStudentID(), s.getName(), s.getAttendance(), s.getMidTest(),
						s.getFinalTest(), s.getHomework(), s.getQuiz(), s.getPt(), s.getReport(), s.getOthers(), calScore(s) });
				calGradeAll();
				
			// �л� ����
			} else if(e.getUIInputMode() == UIInputMode.EDIT) {
				int[] scores = e.getStudent().getScores();
				for(int row = 0; row < table.getRowCount(); row++) {
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
				calGradeAll();
			
			// �л� ����
			} else if(e.getUIInputMode() == UIInputMode.DELETE) {
				for(int row = 0; row < table.getRowCount(); row++) {
					if((int) table.getValueAt(row, 0) == e.getRow()) {
						tableModel.removeRow(row);
						break;
					}
				}
				calGradeAll();
			
			// ���� �ٽ� ���
			} else {
				refresh();
			}
		}
		
	}

}
