//import javax.swing.*;
//import java.awt.*;
import javax.swing.JFrame;
//import java.awt.*;
import javax.swing.SwingUtilities;

public class Game {

    Game(){
        Frame scene = new Frame();
        JFrame stage = new JFrame();
        stage.add(scene);
        stage.setVisible(true);
        stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage.pack();
        stage.setResizable(false);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
            }
        });
    }
}
