package complex;

public interface Complex extends CartesianPolar, ComplexField {
    public Complex create(double real, double imag);
    public Complex create(double radius, Angle azimuth);
}