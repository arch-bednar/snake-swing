import javax.swing.JPanel;
//import java.awt.*;
import java.awt.*;

public class Frame extends JPanel{

    JCanvas board;    //Canvas - main game stage
    JPanel scorePane; //Pane with current score and food color
    //Text scoreText; //Text with score number

    Frame(){
        super();
        this.setLayout(new BorderLayout());

        makeLogo();
        makeBoard();
        makeScore();
    }

    public void makeLogo(){
        int width=800;
        int height=100;

        //set pane for logo with preferred size and background
        JPanel logo = new JPanel(){
            //set border - white line
            @Override
            public void paintComponent(Graphics gc){
                super.paintComponent(gc);
                Graphics2D g2d = (Graphics2D) gc;

                //Rectangle border1 = new Rectangle(0, height-10, width, 10);
                Rectangle border1 = new Rectangle(0, height-10, width, 10);
                g2d.setColor(Color.WHITE);
                g2d.fill(border1);
                g2d.draw(border1);

                //set part of logo - without CSS

                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                String snake = new String("Snake");
                g2d.setFont(new Font(null, Font.BOLD, 36));
                g2d.setColor(new Color(0,255,120));
                g2d.drawString(snake, 334, 55);

                String fx = new String("FX");
                g2d.setFont(new Font(null, Font.BOLD|Font.ITALIC, 36));
                g2d.setColor(new Color(255, 0, 0));
                g2d.drawString(fx, 435, 55);
            }
        };

        logo.setPreferredSize(new Dimension(width, height));
        logo.setBackground(Color.BLACK);

        this.add(logo, BorderLayout.NORTH);
    }

    public void makeBoard(){
        board = new JCanvas(600, 600);
        board.setTimer();
        board.setFrame(this);
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

                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2d.setColor(board.food.getColor());
                g2d.fillRect(75,290,50,50);

                setInformation(g2d);
            }

            public void setInformation(Graphics2D g2d){
                String score = new String("Score:");
                g2d.setFont(new Font(null, Font.PLAIN, 38));
                g2d.setColor(Color.WHITE);
                g2d.drawString(score, 50, 130);

                String food = new String("Food:");
                g2d.setFont(new Font(null, Font.PLAIN, 35));
                g2d.setColor(Color.WHITE);
                g2d.drawString(food, 55, 260);

                //set food color
                //setScore(board.getScore());
                //setColor(board.food.getColor());

                String scoreText = new String(String.valueOf(board.getScore()));
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font(null, Font.PLAIN, 25));
                g2d.drawString(scoreText, 70, 180);

                g2d.setColor(board.food.getColor());
                g2d.fillRect(75,290,50,50);
            }

//            public void setScore(int score){
//                String scoreText = new String(String.valueOf(score));
//                Graphics2D g2d = (Graphics2D) getGraphics();
//                g2d.setColor(Color.WHITE);
//                g2d.setFont(new Font(null, Font.PLAIN, 25));
//                g2d.drawString(scoreText, 70, 180);
//            }
//
//            public void setColor(Color color){
//                Graphics2D g2d = (Graphics2D) getGraphics();
//                g2d.setColor(color);
//                g2d.fillRect(75,290,50,50);
//            }
        };

        scorePane.setPreferredSize(new Dimension(width, height));
        scorePane.setBackground(Color.BLACK);

        //setInformation(); //<-it cannot be called here because jpanel is not rendered yet -> jframe.setVisible(true)

        this.add(scorePane, BorderLayout.EAST);
    }

//    public void setInformation(){
//
//        String text = new String("Score:");
//        Graphics2D g2d = (Graphics2D) scorePane.getGraphics();
//        g2d.setFont(new Font(null, Font.PLAIN, 38));
//        g2d.drawString(text, 130, 50);
//
//        //set food color
//        setColor(board.food.getColor());
//    }
//
//    public void setScore(int score){
//
//    }
//
//    public void setColor(Color color){
//        Graphics2D g2d = (Graphics2D) scorePane.getGraphics();
//        g2d.setColor(color);
//        g2d.fillRect(75,290,50,50);
//    }
}
