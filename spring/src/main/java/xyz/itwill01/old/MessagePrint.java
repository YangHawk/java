package xyz.itwill01.old;

public class MessagePrint {
	public void helloMessagePrint() {
		HelloMessageObject Object = new HelloMessageObject();
		String message = Object.getHelloMessage();
		System.out.println("message = " + message);
	}

	public void hiMessagePrint() {
		HiMessageObject Object = new HiMessageObject();
		String message = Object.getHiMessage();
		System.out.println("message = " + message);
	}
}
