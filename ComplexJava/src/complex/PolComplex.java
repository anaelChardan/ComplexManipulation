package complex;

public class PolComplex extends AbsComplex {
	private double radius;
	private Angle azimuth;

	public PolComplex(double real, double imag){
		this.radius = Conversion.radius(real, imag);
		this.azimuth = Conversion.azimuth(real, imag);
	}
	public PolComplex(double radius, Angle azimuth){
		this.radius = radius;
		this.azimuth = azimuth;
	}

	public double getReal(){
		return Conversion.real(this.getRadius(), this.getAzimuth());
	}
	public double getImag(){
		return Conversion.imag(this.getRadius(), this.getAzimuth());
	}
	public double getRadius(){
		return radius;
	}
	public Angle getAzimuth(){
		return azimuth;
	}

	public Complex create(double real, double imag){
		return new PolComplex(real, imag);
	}
	public Complex create(double radius, Angle azimuth){
		return new PolComplex(radius, azimuth);
	}
}