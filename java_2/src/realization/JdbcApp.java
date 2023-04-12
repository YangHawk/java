package realization;

public class JdbcApp {
	public static void main(String[] args) {
		Jdbc mysql = new JdbcMysql();

		mysql.insert();
		mysql.update();
		mysql.delete();
		mysql.select();
		
		System.out.println("====================================================");
	
		/*
		JdbcOracle oralce = new JdbcOracle();

		oralce.add();
		oralce.modify();
		oralce.remove();
		oralce.search();
		*/

		//Jdbc jdbc = new JdbcMysql();
		Jdbc jdbc = new JdbcOracle();
		
		jdbc.insert();
		jdbc.update();
		jdbc.delete();
		jdbc.select();
		System.out.println("====================================================");

	}
}
