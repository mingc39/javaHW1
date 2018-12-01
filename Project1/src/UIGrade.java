// UIGrade.java
// 학점 비율 설정 창

public class UIGrade extends UISetting {

	private static final long serialVersionUID = -7295863902602826900L;

	public UIGrade(StudentTable studentTable) {
		super(studentTable);
	}

	@Override
	protected void applySetting() {
		studentTable.setGrade(raito);
	}

	@Override
	protected void setName() {
		name = studentTable.getGradeName();
	}

	@Override
	protected void setData() {
		raito = studentTable.getGrade();
	}

}
