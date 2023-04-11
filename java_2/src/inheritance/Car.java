package inheritance;

public class Car extends Object {
	private String modelName;
	private String userName;

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(String modelName, String userName) {
		super();
		this.modelName = modelName;
		this.userName = userName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "모델명 = " + modelName + ", 소유자 = " + userName;
	}
}
