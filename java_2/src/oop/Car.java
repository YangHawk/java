package oop;

public class Car {
	private String modelName;
	private boolean engineStatus;
	private int currentSpeed;

	public void startEngine() {
		engineStatus = true;
		System.out.println(modelName + "의 시동을 켰습니다.");
	}

	public void stopEngine() {
		/*
		 * if (currentSpeed != 0) { currentSpeed = 0; System.out.println(modelName +
		 * "의 자동차가 멈췄습니다."); }
		 */
		// 이렇게 사용할 수도 있지만, 메소드를 호출할 수 있다.↓

		if (currentSpeed != 0) {
			speedZero();
		}

		engineStatus = false;
		System.out.println(modelName + "의 시동을 껐습니다.");
	}

	public void speedUp(int speed) {
		if (!engineStatus) {
			System.out.println(modelName + "의 자동차 시동이 꺼져 있습니다.");
			return;
		}

		if (currentSpeed + speed > 150) {
			speed = 150 - currentSpeed;
		}

		currentSpeed += speed;
		System.out.println(modelName + "의 속도가 " + speed + "Km/h 증가 되었습니다. 현재 속도는 " + currentSpeed + "Km/h 입니다.");
	}

	public void speedDown(int speed) {
		if (!engineStatus) {
			System.out.println(modelName + "의 자동차 시동이 꺼져 있습니다.");
			return;
		}

		if (currentSpeed < speed) {
			speed = currentSpeed;
		}

		currentSpeed -= speed;
		System.out.println(modelName + "의 속도가 " + speed + "Km/h 감소 되었습니다. 현재 속도는 " + currentSpeed + "Km/h 입니다.");
	}

	public  void speedZero() {

		currentSpeed = 0;
		System.out.println(modelName + "의 자동차가 멈췄습니다");
	}

	public boolean isEngineStatus() {
		return engineStatus;
	}

	public void setEngineStatus(boolean engineStatus) {
		this.engineStatus = engineStatus;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String name) {
		modelName = name;
	}

}
