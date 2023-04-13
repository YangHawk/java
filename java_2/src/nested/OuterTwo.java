package nested;

public class OuterTwo {
	private int outerNum;

	public OuterTwo() {
		// TODO Auto-generated constructor stub
	}

	public OuterTwo(int outerNum) {
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

		// InnerTwo innerTwo = new InnerTwo(200);
		// System.out.println("innerTwo = " + innerTwo);

		//System.out.println("staticNum = " + InnerTwo.staticNum);
	}

	public static class InnerTwo {
		private int innerNum;
		private static int staticNum = 300;

		public InnerTwo() {
			// TODO Auto-generated constructor stub
		}

		public InnerTwo(int innerNum) {
			super();
			this.innerNum = innerNum;
		}

		public int getInnerNum() {
			return innerNum;
		}

		public void setInnerNum(int innerNum) {
			this.innerNum = innerNum;
		}

		public static int getStaticNum() {
			return staticNum;
		}

		public static void setStaticNum(int staticNum) {
			InnerTwo.staticNum = staticNum;
		}

		public void innerDisplay() {
			System.err.println("innerNum = " + innerNum);
			System.err.println("staticNum = " + staticNum);

			// System.out.println("outerNum = " + outerNum);
			// outerDisplay();
		}
	}
}
