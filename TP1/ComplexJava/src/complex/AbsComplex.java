package complex;

public abstract class AbsComplex implements Complex {
	public Complex add(Complex c){
		return this.create(this.getReal() + c.getReal(),
				this.getImag() + c.getImag());
	}
	public Complex multiply(Complex c){
		return this.create(this.getRadius() * c.getRadius(),
				this.getAzimuth().add(c.getAzimuth()));
	}

	public boolean equals(Object o){
		if(!(o instanceof Complex)) return false;
		Complex c = (Complex)o;
		return Comparison.isEqual(this.getReal(), c.getReal()) 
				&& Comparison.isEqual(this.getImag(), c.getImag());
	} 
	public String toString(){
		return "(" + this.getReal() + ", " + this.getImag() + ")";
	}
}