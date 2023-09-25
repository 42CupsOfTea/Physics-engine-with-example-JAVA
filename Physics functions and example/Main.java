import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
    static JFrame frame = new JFrame("Donkey Kong");

    static int WINDOWWIDTH = 1500;
    static int WINDOWHEIGHT = 750;
     
    public static void main(String[] args) {
        GameLoop game = new GameLoop();
        Screen screen = new Screen(game);
        game.passInScreen(screen);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WINDOWWIDTH,WINDOWHEIGHT);
                frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.setLayout(null);
        
        frame.setContentPane(screen);

        game.initialize();
    }
    
   
}
