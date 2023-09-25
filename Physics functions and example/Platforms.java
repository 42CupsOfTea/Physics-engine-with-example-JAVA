import java.awt.Color;

public class Platforms extends Square{

    Platforms (int x, int y, int width, int height, Color colour, double rotation) {
        super(x, y, width, height, colour, rotation);
        
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    
}
