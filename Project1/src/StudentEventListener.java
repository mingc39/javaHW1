import java.util.EventListener;

// StudentEventListener.java
// StudentEvent 리스너 인터페이슨
public interface StudentEventListener extends EventListener {
	void studentEvent(StudentEvent e);
}
