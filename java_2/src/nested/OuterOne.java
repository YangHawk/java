package nested;

public class OuterOne {
	private int outerNum;

	public OuterOne() {
		// TODO Auto-generated constructor stub
	}

	public OuterOne(int outerNum) {
		super();
		this.outerNum = outerNum;
	}

	public int getOuterNum() {
		return outerNum;
	}

	public void setOuterNum(int outerNum) {
		this.outerNum = outerNum;
	}

	public void outerDisplay() {
		System.out.println("outerNum = " + outerNum);
	}

	// innerOne.innerDisplay();
	// InnerOne innerOne = new InnerOne();

	public class InnerOne {
		private int innerNum;

		public InnerOne() {
			// TODO Auto-generated constructor stub
		}

		public InnerOne(int innerNum) {
			super();
			this.innerNum = innerNum;
		}

		public int getInnerNum() {
			return innerNum;
		}

		public void setInnerNum(int innerNum) {
			this.innerNum = innerNum;
		}

		public void innerDisplay() {
			System.out.println("innerNum = " + innerNum);

			System.out.println("outerNum = " + outerNum);
		}
	}
}
