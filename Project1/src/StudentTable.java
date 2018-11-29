import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

// StudentTable.java
// 성적표 클래스
public class StudentTable {
	
	// 변수 선언
	private int nextStudent = 0;
	private String[] scoreName = {"출석", "중간 시험", "기말 시험", "과제", "퀴즈", "발표", "보고서", "기타"};
	private double[] raito = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private String[] gradeName = {"A+", "A0", "B+", "B0", "C+", "C0", "D", "F"};
	private double[] grade = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scroll;
	
	// 생성자
	public StudentTable() {
		
		// 표 생성
		String[] header = new String[scoreName.length + 6];
		header[0] = "index"; header[1] = "학번"; header[2] = "이름";
		header[header.length - 3] = "총점"; header[header.length - 2] = "학점"; header[header.length - 1] = "";
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
		Student student = new Student((int) table.getValueAt(selectedRow, 1), (String) table.getValueAt(selectedRow, 2), scores);
		student.setTotal((double) table.getValueAt(selectedRow, scoreName.length + 3));
		student.setGrade((String) table.getValueAt(selectedRow, scoreName.length + 4));
		return student;
	}
	
	public Student getStudentAt(int selectedRow) {
		int[] scores = new int[scoreName.length];
		for(int i = 0; i < scoreName.length; i++) scores[i] = (int) table.getValueAt(selectedRow, i + 3);
		Student student = new Student((int) table.getValueAt(selectedRow, 1), (String) table.getValueAt(selectedRow, 2), scores);
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
		this.raito = raito;
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
		this.grade = grade;
		return true;
	}
	
	public String[] getGradeName() {
		return gradeName.clone();
	}
	
	public String[] getScoreName() {
		return scoreName.clone();
	}
	
	// 모든 학생 정보를 Student 배열로 반환
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
	
	// 학생 추가
	public void addStudent(Student student) {
		
		// 학생 객체에서 점수 배열 받아오기
		int[] scores = student.getScores();
		
		// 배열에 자료 추가
		Object[] values = new Object[scores.length + 6];
		values[0] = nextStudent++; values[1] = student.getStudentID(); values[2] = student.getName();
		for(int i = 0; i < scores.length; i++) values[i + 3] = scores[i];
		values[values.length - 3] = calScore(student);
		values[values.length - 1] = new int[16][];
		
		// 표에 자료 추가
		tableModel.addRow(values);
		
		// 학점 계산
		calGradeAll();
		
	}
	
	// 학생 수정
	public void editStudent(Student s, int index) {
		
		// 학생 객체에서 점수 가져오기
		int[] scores = s.getScores();
		
		// index가 일치하는 학생 찾아 수정
		for(int row = 0; row < tableModel.getRowCount(); row++) {
			if((int) tableModel.getValueAt(row, 0) == index) {
				
				tableModel.setValueAt(s.getStudentID(), row, 1);
				tableModel.setValueAt(s.getName(), row, 2);
				for(int i = 0; i < scores.length; i++) {
					tableModel.setValueAt(scores[i], row, i + 3);
				}
				tableModel.setValueAt(calScore(s), row, scores.length + 3);
				calGradeAll();
				break;
			}
		}
		
	}
	
	// 학생 삭제
	public void removeStudent(int index) {
		for(int row = 0; row < tableModel.getRowCount(); row++) {
			if((int) tableModel.getValueAt(row, 0) == index) {
				tableModel.removeRow(row);
				break;
			}
		}
		calGradeAll();
	}
	
	// 총점 계산
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
		// 모든 학생에 대해
		for(int row = 0; row < table.getRowCount(); row++) {
			// 점수들을 배열로 담아서
			for(int i = 0; i < scoreName.length; i++)
				scores[i] = (int) tableModel.getValueAt(row, i + 3);
			// calScore 함수로 계산해서 표에 저장
			tableModel.setValueAt(calScore(scores), row, scoreName.length + 3);
		}
	}
	
	// 학점 계산
	public String calGrade(double score) {
		
		// 배열 생성
		double[] scores = new double[table.getRowCount()];
		
		// 총점 가져오기
		for(int i = 0; i < table.getRowCount(); i++) {
			scores[i] = (double) table.getValueAt(i, scoreName.length + 3);
		}
		
		// 총점 정렬
		Arrays.sort(scores);
		
		// 학점 계산
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
		
		// 배열 선언
		double[] scores = new double[table.getRowCount()];
		
		// 총검 가져오기
		for(int i = 0; i < table.getRowCount(); i++) {
			scores[i] = (double) table.getValueAt(i, scoreName.length + 3);
		}
		
		// 총점 정렬
		Arrays.sort(scores);
		double rank, t;
		
		// 총점 계산 및 저장
		for(int row = 0; row < table.getRowCount(); row++) {
			double score = (double) tableModel.getValueAt(row, 11);
			
			for(int i = 0; i < scores.length; i++) {
				
				if(scores[i] == score) {
					rank = 1 - (double) i / scores.length;
					t = 0;
					for(int j = 0; j < grade.length; j++) {
						t += grade[j];
						if(rank <= t) {
							tableModel.setValueAt(gradeName[j], row, scoreName.length + 4);
							break;
						}
					}
				}
				
			}
			
		}
	}
	
	// 총점, 학점 새로 고침
	public void refresh() {
		calScoreAll();
		calGradeAll();
	}

}
