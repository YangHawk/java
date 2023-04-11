package inheritance;

public class EmployeeContract extends Employee {
	private int contractPay;

	public EmployeeContract() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeContract(int empNo, String empName, int contractPay) {
		super(empNo, empName);
		this.contractPay = contractPay;
	}

	public int getContractPay() {
		return contractPay;
	}

	public void setContractPay(int contractPay) {
		this.contractPay = contractPay;
	}

	public int computeContract() {
		return contractPay;
	}
	
	@Override
	public int computePay() {
		// TODO Auto-generated method stub
		return contractPay;
	}
}
