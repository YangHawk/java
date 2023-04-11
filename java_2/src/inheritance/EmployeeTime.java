package inheritance;

public class EmployeeTime extends Employee {
	private int moneyPerHour;
	private int workedHour;

	public EmployeeTime() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeTime(int empNo, String empName, int moneyPerHour, int workedHour) {
		super(empNo, empName);
		this.moneyPerHour = moneyPerHour;
		this.workedHour = workedHour;
	}

	public int getMoneyPerHour() {
		return moneyPerHour;
	}

	public void setMoneyPerHour(int moneyPerHour) {
		this.moneyPerHour = moneyPerHour;
	}

	public int getWorkedHour() {
		return workedHour;
	}

	public void setWorkedHour(int workedHour) {
		this.workedHour = workedHour;
	}

	public int computeTime() {
		return moneyPerHour * workedHour;
	}
	
	@Override
	public int computePay() {
		// TODO Auto-generated method stub
		return moneyPerHour * workedHour;
	}
}
