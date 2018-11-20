import java.util.EventObject;

// StudentEvent.java
// 학생 관련 이벤트
public class StudentEvent extends EventObject {
	
	private static final long serialVersionUID = -7124631594752237995L;
	private int row;
	private Student student;
	private UIInputMode mode;
	
	public StudentEvent(Object source, Student student, UIInputMode mode, int row) {
		super(source);
		this.student = student;
		this.mode = mode;
		this.row = row;
	}
	
	public StudentEvent(Object source, Student student, UIInputMode mode) {
		super(source);
		this.student = student;
		this.mode = mode;
	}

	public StudentEvent(Object source, Student student) {
		super(source);
		this.student = student;
		mode = UIInputMode.NONE;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public UIInputMode getUIInputMode() {
		return mode;
	}
	
	public int getRow() {
		return row;
	}

}
