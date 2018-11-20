import java.util.EventObject;

public class StudentEvent extends EventObject {
	
	private static final long serialVersionUID = -7124631594752237995L;
	private Student student;

	public StudentEvent(Object source, Student student) {
		super(source);
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}

}
