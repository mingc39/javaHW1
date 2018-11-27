// Student.java
// 학생을 나타내는 클래스

public class Student {
	
	private static final int MAX_SCORE = 100, MIN_SCORE = 0;
	private int[] scores;
	private int studentID;
	private double total;
	private String grade;
	private String name;
	
	//------------------------------------------------------
	public Student(int studentID, String name, int attendance, int midTest, int finalTest, int homework, int quiz, int pt,
			int report, int others) throws ScoreRangeException {
		this(studentID, name, new int[]{ attendance, midTest, finalTest, homework, quiz, pt, report, others });
	}
	//------------------------------------------------------
	
	public Student(int studentID, String name, int[] scores) throws ScoreRangeException {
		this.studentID = studentID;
		this.name = name;
		for(int i : scores) checkScoreRange(i);
		this.scores = scores.clone();
	}
	
	private int checkScoreRange(int score) throws ScoreRangeException {
		if((score > MAX_SCORE) || (score < MIN_SCORE)) throw new ScoreRangeException();
		return score;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String toString() {
		String info = "학번: " + studentID + ", 이름: " + name;
		for(int score : getScores()) info += ", " + score;
		return info;
	}
	
	public String[] getValues() {
		String[] values = new String[scores.length + 2];
		values[0] = Integer.toString(studentID); values[1] = name;
		for(int i = 0; i < scores.length; i++) values[i + 2] = Integer.toString(scores[i]);
		return values;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public double getTotal() {
		return total;
	}
	
	public String getGrade() {
		return grade;
	}

	public int getStudentID() {
		return studentID;
	}

	//---------------------------------
	public int getAttendance() {
		return scores[0];
	}

	public int getMidTest() {
		return scores[1];
	}

	public int getFinalTest() {
		return scores[2];
	}

	public int getHomework() {
		return scores[3];
	}

	public int getQuiz() {
		return scores[4];
	}

	public int getPt() {
		return scores[5];
	}

	public int getReport() {
		return scores[6];
	}

	public int getOthers() {
		return scores[7];
	}
	//-----------------------------------------------

	public String getName() {
		return name;
	}
	
}
