package inheritance;

public class EmployeeApp {
	public static void main(String[] args) {
		
		//Employee employee = new Employee();
		
		/*
		Employee employee1 = new EmployeeRegular();
		Employee employee2 = new EmployeeTime();
		Employee employee3 = new EmployeeContract();
		*/
		
		Employee[] empArray = new Employee[5];

		empArray[0] = new EmployeeRegular(1000, "홍길동", 96000000);
		empArray[1] = new EmployeeTime(2000, "임꺽정", 50000, 150);
		empArray[2] = new EmployeeContract(3000, "전우치", 7000000);
		empArray[3] = new EmployeeTime(4000, "일지매", 20000, 100);
		empArray[4] = new EmployeeRegular(5000, "장길산", 60000000);

		for (Employee employee : empArray) {
			System.out.println("사원번호 = " + employee.getEmpNo());
			System.out.println("사원이름 = " + employee.getEmpName());
			System.out.println("사원급여 = " + employee.computePay());
			System.out.println("인센티브 = " + employee.computeIncentive());			

			/*
			if (employee instanceof EmployeeRegular) {
				System.out.println("급여 = " + ((EmployeeRegular) employee).computeSalary());

			} else if (employee instanceof EmployeeTime) {
				System.out.println("급여 = " + ((EmployeeTime) employee).computeTime());

			} else if (employee instanceof EmployeeContract) {
				System.out.println("급여 = " + ((EmployeeContract) employee).computeContract());
			}
			*/
			
			System.out.println("================================================");
		}
	}
}
