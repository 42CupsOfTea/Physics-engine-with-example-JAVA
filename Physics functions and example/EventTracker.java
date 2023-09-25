import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventTracker implements KeyListener {
    // up = 0 index
    //down = 1 index
    //left = 2 index
    //right = 3 index 
    private boolean [] keyPressedArray = new boolean[4];
    private int movmentSpeed = 5;
    EventTracker() {
        Main.frame.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int whichKey = e.getKeyCode();
        switch(whichKey) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                keyPressedArray[0] = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                keyPressedArray[1] = true;


                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                keyPressedArray[2] = true;

                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                keyPressedArray[3] = true;
                break;
        }   
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int whichKey = e.getKeyCode();
        switch(whichKey) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                keyPressedArray[0] = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                keyPressedArray[1] = false;


                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                keyPressedArray[2] = false;

                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                keyPressedArray[3] = false;
                break;
        }   
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean [] getMovmentKeyPresses() {
        return keyPressedArray;
    }
    public int getSpeedForPlayer() {
        return movmentSpeed;
    }
}
