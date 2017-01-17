package complex;

public class CarComplex extends AbsComplex {
	private double real;
	private double imag;

	public CarComplex(double real, double imag){
		this.real = real;
		this.imag = imag;
	}
	public CarComplex(double radius, Angle azimuth){
		this.real = Conversion.real(radius, azimuth);
		this.imag = Conversion.imag(radius, azimuth);
	}

	public double getReal(){
		return real;
	}
	public double getImag(){
		return imag;
	}
	public double getRadius(){
		return Conversion.radius(this.getReal(), this.getImag());
	}
	public Angle getAzimuth(){
		return Conversion.azimuth(this.getReal(), this.getImag());
	}

	public Complex create(double real, double imag){
		return new CarComplex(real, imag);
	}
	public Complex create(double radius, Angle azimuth){
		return new CarComplex(radius, azimuth);
	}
}