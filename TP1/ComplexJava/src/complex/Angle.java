package complex;

public class Angle {
	private double theta; // angle in radians (in [0, 2.PI[) 
	public Angle( double radians){
	  this.theta = 
		radians - 2 * Math.PI * Math.floor(radians / (2 * Math.PI));
	}
	public double getAngle(){
	  return this.theta; 
	}
	public Angle add(Angle a){
	  return new Angle(a.getAngle() + this.getAngle());
	}
	public Angle multiply( double k){
		return new Angle(k * this.getAngle());
	}
}