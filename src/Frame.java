import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;

public class Frame extends JPanel{

    JCanvas board;    //Canvas - main game stage
    JPanel scorePane; //Pane with current score and food color
    //Text scoreText; //Text with score number

    Frame(){
        this.setLayout(new BorderLayout());

        makeLogo();
        makeBoard();
        makeScore();
    }

    public void makeLogo(){
        int width=800;
        int height=100;

        JPanel logo = new JPanel(){
            @Override
            public void paintComponent(Graphics gc){
                super.paintComponent(gc);
                //setBackground(Color.black);
                Graphics2D g2d = (Graphics2D) gc;

                //Rectangle border1 = new Rectangle(0, height-10, width, 10);
                Rectangle border1 = new Rectangle(0, height-10, width, 10);
                g2d.setColor(Color.WHITE);
                g2d.fill(border1);
                g2d.draw(border1);
            }
        };

        logo.setPreferredSize(new Dimension(width, height));
        logo.setBackground(Color.BLACK);
        this.add(logo, BorderLayout.NORTH);
    }

    public void makeBoard(){
        board = new JCanvas(600, 600);
        board.setTimer();
        //board.setFrame(this);
        this.add(board, BorderLayout.CENTER);
    }

    public void makeScore(){
        int width=200;
        int height=600;

        //make panel
        scorePane = new JPanel(){
            @Override
            public void paintComponent(Graphics gc){
                super.paintComponent(gc);
                Graphics2D g2d = (Graphics2D) gc;

                //make border
                Rectangle border1 = new Rectangle(0, 0, 10, height);
                g2d.setColor(Color.WHITE);
                g2d.fill(border1);
                g2d.draw(border1);
            }
        };

        scorePane.setPreferredSize(new Dimension(width, height));
        scorePane.setBackground(Color.BLACK);

        setInformation();

        this.add(scorePane, BorderLayout.EAST);
    }

    public void setInformation(){

    }

    public void setScore(int score){

    }

    public void setColor(Color color){
        Rectangle rec = new Rectangle(75, 290, 50, 50);
        Graphics2D g2d = (Graphics2D) scorePane.getGraphics();
        g2d.setColor(color);
        g2d.fill(rec);
        g2d.draw(rec);
    }
}
