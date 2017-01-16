package complex;

public class Test {

	private static int score = 0;
	private static int total = 0;

	public static void main(String[] args){

		Complex factory = new CarComplex(0, 0);
		test(factory);
		factory = new PolComplex(0, 0);
		test(factory);
		System.out.println("-----------------------------------------------------------");
		System.out.println("Final score : " + score + " / " + total);
	}

	private static void test(Complex factory){
		Complex a = factory.create(1,-1);
		testCoordinates(a, 1.0, -1.0, Math.sqrt(2.0), (7.0 * Math.PI / 4.0));

		Complex b = factory.create(2, new Angle(Math.PI/2.0));
		testCoordinates(b, 0.0, 2.0, 2.0, (Math.PI / 2.0));

		Complex c = a.add(b);
		testEquality(c, factory.create(1.0, 1.0));
		testCoordinates(c, 1.0, 1.0, Math.sqrt(2.0), Math.PI/ 4.0);

		Complex d = a.multiply(c);
		testEquality(d, factory.create(2.0, 0.0));
		testCoordinates(d, 2.0, 0.0, 2.0, 0.0);
	}

	private static void testCoordinates(Complex c, 
			double x, double y,
			double rho, double theta){
		total++;
		if(Comparison.isEqual(c.getReal(), x)){
			score++;
		}else{
			System.out.println("-----------------------------------------------------------");
			System.out.println("Error for complex " + c + ": " + c.getClass());
			System.out.println("x = " + c.getReal() + " instead of " + x);
		}
		total++;
		if(Comparison.isEqual(c.getImag(), y)){
			score++;
		}else{
			System.out.println("-----------------------------------------------------------");
			System.out.println("Error for complex " + c + ": " + c.getClass());
			System.out.println("y = " + c.getImag() + " instead of " + y);
		}
		total++;
		if(Comparison.isEqual(c.getRadius(), rho)){
			score++;
		}else{
			System.out.println("-----------------------------------------------------------");
			System.out.println("Error for complex " + c + ": " + c.getClass());
			System.out.println("rho = " + c.getRadius() + " instead of " + rho);
		}
		total++;
		if(Comparison.isEqual(c.getAzimuth().getAngle(), theta)){
			score++;
		}else{
			System.out.println("-----------------------------------------------------------");
			System.out.println("Error for complex " + c + ": " + c.getClass());
			System.out.println("theta = " + c.getAzimuth().getAngle() + " instead of " + theta);
		}

	}

	private static void testEquality(Complex c1, Complex c2){
		total++;
		if(c1.equals(c2)){
			score++;
		}else{
			System.out.println("-----------------------------------------------------------");
			System.out.println("Error in the test for equality: " 
					+ c1 + ": " + c1.getClass() 
					+ " egal a " + c2 + ": " + c2.getClass() );
		}
	}

}