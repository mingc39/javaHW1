// Student.java
// 학생 클래스

public class Student {
	
	private int studentID, attendance, midTest, finalTest, homework, quiz, pt, report, others;
	private String name;
	
	public Student(int studentID, String name, int attendance, int midTest, int finalTest, int homework, int quiz, int pt,
			int report, int others) {
		this.studentID = studentID;
		this.attendance = attendance;
		this.midTest = midTest;
		this.finalTest = finalTest;
		this.homework = homework;
		this.quiz = quiz;
		this.pt = pt;
		this.report = report;
		this.others = others;
		this.name = name;
	}

	public int getStudentID() {
		return studentID;
	}

	public int getAttendance() {
		return attendance;
	}

	public int getMidTest() {
		return midTest;
	}

	public int getFinalTest() {
		return finalTest;
	}

	public int getHomework() {
		return homework;
	}

	public int getQuiz() {
		return quiz;
	}

	public int getPt() {
		return pt;
	}

	public int getReport() {
		return report;
	}

	public int getOthers() {
		return others;
	}

	public String getName() {
		return name;
	}
	
	

}
