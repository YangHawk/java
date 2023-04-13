package xyz.itwill.app;

import xyz.itwill.subject.JavaSubject;
import xyz.itwill.subject.OracleSubject;
//import xyz.uniwill.subject.JavaSubject;

public class SubjectApp {
	public static void main(String[] args) {
		OracleSubject subject1 = new OracleSubject();
		subject1.display();

		JavaSubject subject2 = new JavaSubject();
		subject2.display();

		xyz.uniwill.subject.JavaSubject subject3 = new xyz.uniwill.subject.JavaSubject();
		subject3.display();
	}
}
