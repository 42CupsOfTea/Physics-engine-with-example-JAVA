public class Vector {
    double x,y;
    Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector normalize() {
        double magintude = Math.sqrt(x*x + y*y);
        x /= magintude;
        y /= magintude;

        return new Vector(x, y);
    }

    public Vector makePerpVector () {
        return new Vector(-y,x);
    }

    public double projectPoint(Points point) {
        double scalarValue = (x * point.x) + (y * point.y);
        return scalarValue;
    } 
}
