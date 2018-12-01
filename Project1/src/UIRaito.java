// UIRaito.java
// 반영 비율 설정 창

public class UIRaito extends UISetting {
	
	private static final long serialVersionUID = 7205320606118195094L;

	public UIRaito(StudentTable studentTable) {
		super(studentTable);
	}

	@Override
	protected void applySetting() {
		studentTable.setRaito(raito);
	}

	@Override
	protected void setName() {
		name = studentTable.getScoreName();
	}

	@Override
	protected void setData() {
		raito = studentTable.getRaito();
	}

}
