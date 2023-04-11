package inheritance;

public abstract class Employee {
	private int empNo;
	private String empName;
	
	public static final double INCENTIVE_RATE = 1.5;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empNo, String empName) {
		super();
		this.empNo = empNo;
		this.empName = empName;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/*
	public int computePay() {
		return 0;
	}
	*/
	
	public abstract int computePay();
	
	public final int computeIncentive() {
		return (int)(computePay()*INCENTIVE_RATE);
	}

}
