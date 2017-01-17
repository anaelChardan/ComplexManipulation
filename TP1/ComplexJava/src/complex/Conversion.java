package complex;

public class Conversion {
	/*
	 * Conversions between polar and cartesian coordinates
	 */
	public static double real(double radius, Angle azimuth){
	    return radius * Math.cos(azimuth.getAngle());
	}
	public static double imag(double radius, Angle azimuth){
	    return radius * Math.sin(azimuth.getAngle());
	}
	public static double radius(double real, double imag){
	    return Math.sqrt(real*real + imag*imag);
	}
	public static Angle azimuth(double real, double imag){
	    return new Angle(Math.atan2(imag, real));  
	}
}
