package xyz.itwill02.factory;

public class MessagePrint {
	public void messagePrint() {
		// 객체를 직접 생성하여 메소드 호출 - 객체 간 결합도가 높아 유지 보수의 효율성 감소
		// MessageObject object =new HelloMessageObject();

		// 프로그램 실행 시 필요한 객체를 Factory 클래스로부터 제공받아 메소드 호출
		// ▶ IoC(Inversion of Control): 제어의 역핵 - 객체 간 결합도를 낮춰 유지 보수의 효율성 증대
		// MessageObject object = MessageObjectFactory.getMessageObject(1); // Hello!!!
		MessageObject object = MessageObjectFactory.getMessageObject(2); // Hi???

		// 인터페이스로 생성된 참조 변수로 추상 메소드를 호출할 경우 참조 변수에 저장된 자식 객체의 오버라이드 메소드 호출
		String message = object.getMessage();
		System.out.println("message = " + message);
	}
}
