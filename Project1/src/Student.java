// Student.java
// 학생을 나타내는 클래스

public class Student {
	
	private static final int MAX_SCORE = 100, MIN_SCORE = 0;
	private int studentID, attendance, midTest, finalTest, homework, quiz, pt, report, others;
	private String name;
	
	public Student(int studentID, String name, int attendance, int midTest, int finalTest, int homework, int quiz, int pt,
			int report, int others) throws ScoreRangeException {
		this.studentID = studentID;
		this.name = name;
		this.attendance = checkScoreRange(attendance);
		this.midTest = checkScoreRange(midTest);
		this.finalTest = checkScoreRange(finalTest);
		this.homework = checkScoreRange(homework);
		this.quiz = checkScoreRange(quiz);
		this.pt = checkScoreRange(pt);
		this.report = checkScoreRange(report);
		this.others = checkScoreRange(others);
	}
	
	public Student(int studentID, String name, int[] scores) throws ScoreRangeException {
		this(studentID, name, scores[0], scores[1], scores[2], scores[3], scores[4], scores[5], scores[6], scores[7]);
	}
	
	private int checkScoreRange(int score) throws ScoreRangeException {
		if((score > MAX_SCORE) || (score < MIN_SCORE)) throw new ScoreRangeException();
		return score;
	}
	
	public String toString() {
		String info = "학번: " + studentID + ", 이름: " + name;
		for(int score : getScores()) info += ", " + score;
		return info;
	}
	
	public String[] getValues() {
		return new String[] { Integer.toString(studentID), name, Integer.toString(attendance), Integer.toString(midTest), Integer.toString(finalTest),
				Integer.toString(homework), Integer.toString(quiz), Integer.toString(pt), Integer.toString(report),
				Integer.toString(others)};
	}
	
	public int[] getScores() {
		return new int[] { attendance, midTest, finalTest, homework, quiz, pt, report, others };
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
