//import javax.swing.*;
//import java.awt.*;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.SwingUtilities;

public class Game {

    Game(){
//        Board board = new Board(600, 600);
//        JFrame jframe = new JFrame();
//        jframe.setFocusable(false);
//        //jframe.setMinimumSize(new Dimension(620, 620));
//        //jframe.setPreferredSize(new Dimension(620, 620));
//        jframe.add(board);
//        jframe.setVisible(true);
//        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jframe.pack();
//        board.setTimer();


//        Frame board = new Frame();
//
//        JFrame jframe = new JFrame();
//        jframe.add(board);
//        jframe.setFocusable(false);
//        jframe.setMinimumSize(new Dimension(800, 800));
//        jframe.setVisible(true);
//        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Frame f = new Frame();
        JFrame f1 = new JFrame();
        f1.add(f);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.pack();
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
