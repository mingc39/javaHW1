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
	public final int MAX_SCORE = 100, MIN_SCORE = 0;
	private int nextStudent = 0;
	private String[] scoreName = {"�⼮", "�߰� ����", "�⸻ ����", "����", "����", "��ǥ", "����", "��Ÿ"};
	private double[] raito = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private String[] gradeName = {"A+", "A0", "B+", "B0", "C+", "C0", "D", "F"};
	private double[] grade = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scroll;
	private int absentLimit = 4, lateToAbsent = 3;
	private double lateSubtract = 0, absentSubtract = 1;
	
	// ������
	public StudentTable() {
		
		// ǥ ����
		String[] header = new String[scoreName.length + 6];
		header[0] = "index"; header[1] = "�й�"; header[2] = "�̸�";
		header[header.length - 3] = "����"; header[header.length - 2] = "����"; header[header.length - 1] = "";
		for(int i = 0; i < scoreName.length; i++) header[i + 3] = scoreName[i];
		tableModel = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = -2265577528898631753L;
			
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex == 2) return String.class;
				return Integer.class;
			}
		};
		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSorter(new TableRowSorter<TableModel>(tableModel));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		table.getColumnModel().getColumn(header.length - 1).setMinWidth(0);
		table.getColumnModel().getColumn(header.length - 1).setMaxWidth(0);
		table.getColumnModel().getColumn(header.length - 1).setWidth(0);
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
		int[] scores = new int[scoreName.length];
		for(int i = 0; i < scoreName.length; i++) scores[i] = (int) table.getValueAt(selectedRow, i + 3);
		int[][] attendance = (int[][]) table.getValueAt(selectedRow, table.getColumnCount() - 1);
		if(attendance != null) {
			attendance = attendance.clone();
			for(int i = 0; i < 16; i++)
				if(attendance[i] != null) attendance[i] = attendance[i].clone();
		}
		Student student = new Student((int) table.getValueAt(selectedRow, 1), (String) table.getValueAt(selectedRow, 2), scores, attendance);
		student.setTotal((double) table.getValueAt(selectedRow, scoreName.length + 3));
		student.setGrade((String) table.getValueAt(selectedRow, scoreName.length + 4));
		return student;
	}
	
	public Student getStudentAt(int selectedRow) {
		int[] scores = new int[scoreName.length];
		for(int i = 0; i < scoreName.length; i++) scores[i] = (int) table.getValueAt(selectedRow, i + 3);
		int[][] attendance = (int[][]) table.getValueAt(selectedRow, table.getColumnCount() - 1);
		if(attendance != null) {
			attendance = attendance.clone();
			for(int i = 0; i < 16; i++)
				if(attendance[i] != null) attendance[i] = attendance[i].clone();
		}
		Student student = new Student((int) table.getValueAt(selectedRow, 1), (String) table.getValueAt(selectedRow, 2), scores, attendance);
		student.setTotal((double) table.getValueAt(selectedRow, scoreName.length + 3));
		student.setGrade((String) table.getValueAt(selectedRow, scoreName.length + 4));
		return student;
	}
	
	public Student getSelectedStudent() {
		return getStudent(table.getSelectedRow());
	}
	
	public int getSelectedStudentIndex() {
		return (int) table.getValueAt(table.getSelectedRow(), 0);
	}
	
	public Student getStudentByIndex(int index) {
		
		// index�� ��ġ�ϴ� �л� ã�� ��ȯ
		for(int row = 0; row < tableModel.getRowCount(); row++) {
			if((int) tableModel.getValueAt(row, 0) == index) 
				return getStudent(row);
		}
		return null;
	}
	
	public double[] getRaito() {
		return raito.clone();
	}
	
	public boolean setRaito(double[] raito) {
		if(raito.length != this.raito.length) return false;
		double total = 0;
		for(double score : raito) {
			total += score;
			if(score > 1 || score < 0) return false;
		}
		if(total != 1) return false;
		this.raito = raito.clone();
		return true;
	}
	
	public double[] getGrade() {
		return grade.clone();
	}
	
	public boolean setGrade(double[] grade) {
		if(grade.length != this.grade.length) return false;
		double total = 0;
		for(double score : grade) {
			total += score;
			if(score > 1 || score < 0) return false;
		}
		if(total != 1) return false;
		this.grade = grade.clone();
		return true;
	}
	
	public String[] getGradeName() {
		return gradeName.clone();
	}
	
	public String[] getScoreName() {
		return scoreName.clone();
	}
	
	public int getAbsentLimit() {
		return absentLimit;
	}

	public int getLateToAbsent() {
		return lateToAbsent;
	}

	public double getLateSubtract() {
		return lateSubtract;
	}

	public double getAbsentSubtract() {
		return absentSubtract;
	}

	public void setAbsentLimit(int absentLimit) {
		this.absentLimit = absentLimit;
	}

	public void setLateToAbsent(int lateToAbsent) {
		this.lateToAbsent = lateToAbsent;
	}

	public void setLateSubtract(double lateSubtract) {
		this.lateSubtract = lateSubtract;
	}

	public void setAbsentSubtract(double absentSubtract) {
		this.absentSubtract = absentSubtract;
	}

	// ��� �л� ������ Student �迭�� ��ȯ
	public Student[] getStudents() {
		
		Student[] students = new Student[table.getRowCount()];
		
		for(int row = 0; row < table.getRowCount(); row++) {
			students[row] = getStudent(row);
		}
		
		return students;
	}
	
	public String toString() {
		String info = "";
		for(int row = 0; row < table.getRowCount(); row++) {
			if(row != 0) info += "\n";
			info += getStudent(row).toString();
		}
		return info;
	}
	
	// �л� �߰�
	public void addStudent(Student student) {
		
		// �л� ��ü���� ���� �迭 �޾ƿ���
		int[] scores = student.getScores();
	
		// �迭�� �ڷ� �߰�
		Object[] values = new Object[scores.length + 6];
		values[0] = nextStudent++; values[1] = student.getStudentID(); values[2] = student.getName();
		for(int i = 0; i < scores.length; i++) values[i + 3] = scores[i];
		values[values.length - 3] = calScore(student);
		values[values.length - 1] = student.getAttendance();
		
		// ǥ�� �ڷ� �߰�
		tableModel.addRow(values);
		
		// ���� ���
		calGradeAll();
		
		
	}
	
	// �л� ����
	public void editStudent(Student s, int index) {
		
		// �л� ��ü���� ���� ��������
		int[] scores = s.getScores();
		
		// index�� ��ġ�ϴ� �л� ã�� ����
		for(int row = 0; row < tableModel.getRowCount(); row++) {
			if((int) tableModel.getValueAt(row, 0) == index) {
				
				tableModel.setValueAt(s.getStudentID(), row, 1);
				tableModel.setValueAt(s.getName(), row, 2);
				for(int i = 0; i < scores.length; i++) {
					tableModel.setValueAt(scores[i], row, i + 3);
				}
				tableModel.setValueAt(calScore(s), row, scores.length + 3);
				tableModel.setValueAt(s.getAttendance(), row, scores.length + 5);
				calGradeAll();
				break;
			}
		}
		
	}
	
	// �л� ����
	public void removeStudent(int index) {
		for(int row = 0; row < tableModel.getRowCount(); row++) {
			if((int) tableModel.getValueAt(row, 0) == index) {
				tableModel.removeRow(row);
				break;
			}
		}
		calGradeAll();
	}
	
	// ���� ���
	public double calScore(Student student) {
		double score = 0;
		for(int i = 0; i < raito.length; i++)
			score += student.getScores()[i] * raito[i];
		return score;
	}
	public double calScore(int[] scores) {
		double score = 0;
		for(int i = 0; i < raito.length; i++)
			score += (double) scores[i] * raito[i];
		return score;
	}
	public void calScoreAll() {
		int[] scores = new int[scoreName.length];
		// ��� �л��� ����
		for(int row = 0; row < tableModel.getRowCount(); row++) {
			// �������� �迭�� ��Ƽ�
			for(int i = 0; i < scoreName.length; i++)
				scores[i] = (int) tableModel.getValueAt(row, i + 3);
			// calScore �Լ��� ����ؼ� ǥ�� ����
			tableModel.setValueAt(calScore(scores), row, scoreName.length + 3);
		}
	}
	
	// ���� ���
	public String calGrade(double score) {
		
		// �迭 ����
		double[] scores = new double[tableModel.getRowCount()];
		
		// ���� ��������
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			scores[i] = (double) tableModel.getValueAt(i, scoreName.length + 3);			
		}
		
		// ���� ����
		Arrays.sort(scores);
		
		// ���� ���
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
		return "";
	}
	public void calGradeAll() {
		
		// �迭 ����
		double[] scores = new double[tableModel.getRowCount()];
		
		// �Ѱ� ��������
		int[][] attendance;
		int absent, late;
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			scores[i] = (double) tableModel.getValueAt(i, scoreName.length + 3);
			attendance = (int[][]) tableModel.getValueAt(i, scoreName.length + 5);
			absent = 0; late = 0;
			if(attendance == null) continue;
			for(int[] j : attendance) {
				if(j == null) continue;
				else {
					for(int k : j) {
						switch(k) {
						case 1:
							late++;
							break;
						case 2:
							absent++;
							break;
						}
					}
				}
			}
			if(lateToAbsent != 0) absent += late / lateToAbsent;
			if(absent >= absentLimit && absentLimit != 0) scores[i] = -1;
			
		}
		
		// ���� ����
		for(double d : scores) System.out.print(d + " ");
		System.out.println();
		Arrays.sort(scores);
		for(double d : scores) System.out.print(d + " ");
		System.out.println();
		double rank, t;
		
		// ���� ��� �� ����
		for(int row = 0; row < table.getRowCount(); row++) {
			double score = (double) tableModel.getValueAt(row, scoreName.length + 3);
			attendance = (int[][]) tableModel.getValueAt(row, scoreName.length + 5);
			absent = 0; late = 0;
			if(attendance == null) continue;
			for(int[] j : attendance) {
				if(j == null) continue;
				else {
					for(int k : j) {
						switch(k) {
						case 1:
							late++;
							break;
						case 2:
							absent++;
							break;
						}
					}
				}
			}
			if(lateToAbsent != 0) absent += late / lateToAbsent;
			if(absent >= absentLimit && absentLimit != 0) score = -1;
			
			System.out.print(row + " ");
			//double score = (double) tableModel.getValueAt(row, scoreName.length + 3);
			System.out.print(score + " ");
			
			if(score < 0) {
				tableModel.setValueAt(gradeName[gradeName.length - 1], row, scoreName.length + 4);
				System.out.println(gradeName[gradeName.length - 1]);
				continue;
			}
			
			for(int i = 0; i < scores.length; i++) {
				
				if(scores[i] == score) {
					rank = 1 - (double) i / scores.length;
					t = 0;
					for(int j = 0; j < grade.length; j++) {
						t += grade[j];
						System.out.print(gradeName[gradeName.length - 1] + " ");
						if(rank <= t) {
							tableModel.setValueAt(gradeName[j], row, scoreName.length + 4);
							System.out.println();
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

}
