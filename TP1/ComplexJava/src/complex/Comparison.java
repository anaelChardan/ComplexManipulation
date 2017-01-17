package complex;

public class Comparison {
	public static double precision = 1E-15; 
	public static boolean isEqual(double x, double y){
		return Math.abs(x -y) < precision;
	}
}