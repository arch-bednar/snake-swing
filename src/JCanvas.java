import javax.swing.JPanel;
import javax.swing.Timer;
//import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Random;

public class JCanvas extends JPanel implements Engine, Direction{

    private final double width, height;   //bounds
    private final int pixels; //pixels
    //private final Graphics graphics;    //graphicsContext
    private Timer timer; //animation timer
    private long lastTime=0;    //last time
    private int score=0;    //score
    private boolean dead; //if is dead flag
    private boolean flag = true; //pause flag
    private Frame frame; //frame

    JCanvas(double width ,double height){
        this.setPreferredSize(new Dimension((int)width, (int)height));
        this.setMinimumSize(new Dimension((int)width, (int)height));
        //this.graphics = this.getGraphics(); //get graphics from canvas
        setFocusable(true);
        this.pixels = 10;   //set pixels of entity = 10 like food or any part of snake
        this.width = width; //set width of board
        this.height = height;   //set height of board
        snake.setBounds((int) width - pixels, (int) height - pixels); //set bounds of board
        setKeys();
        //draw();
    }

    //initial drawning
//    public void draw(){
//        refresh();
//        drawSnake();
//        drawFood();
//        drawPause();
//    }

    //draw snake
    private void drawSnake(){
        snake.drawSnake(getGraphics());
    }

    private void drawDeadHead(){
        snake.drawDeadHead(getGraphics());
    }

    private void drawSnake(Graphics2D g2d){
        snake.drawSnake(g2d);
    }


    private void clearLastRect(){
        snake.clearLast(getGraphics());
    }

    //draw food
    private void drawFood(){
        food.drawFood(getGraphics());
    }

    private void drawFood(Graphics2D g2d){
        snake.drawDeadHead(g2d);
    }

    private void repaintFrame(){
        frame.scorePane.repaint();
    }

    //refresh whole board (after pause)
    //is useless due to paintComponent method and repaint()
//    private void refresh(){
//
//        Graphics2D g2d = (Graphics2D) getGraphics();
//        for(int row=0; row<width; row=row+pixels){
//            for(int col=0; col<height; col=col+pixels){
//                if(row <= 10 || row >= width-pixels*2 || col <= 10 || col >= height-pixels*2){
//                    g2d.setColor(Color.BLACK);
//                }else{
//                    g2d.setColor(Color.RED);
//                }
//                g2d.fillRect(row, col, pixels, pixels);
//            }
//        }
//    }

    //set animation timer for speed of animation
    public void setTimer(){
        int delay=100; //delay in miliseconds

        ActionListener taskTimer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //refresh();
                //food.drawFood(getGraphics()); //draw food
                drawFood(); //draw food

                dead = snake.move();    //move snake and check if is dead
                clearLastRect();   //clear last position of snakes tail instead refreshing whole board

                if(snake.isEaten(food)){    //do if when food is eaten
                    snake.grow();           //grow snake
                    setFood();              //set food location and color
                    score++;                //incrementation of score
                    //frame.setScore(score);  //set score number in frame
                    //frame.setColor(food.getColor());    //set food color in frame
                    //frame.setInformation();

                    repaintFrame();
                }
                //snake.drawSnake(getGraphics());   //draw snake
                drawSnake();

                if(dead){   //if is dead then stop animation
                    timer.stop(); //stop animation
                    //snake.drawDeadHead(getGraphics());    //draw collision
                    drawDeadHead(); //draw collision
                    drawGameOver();
                }
            }
        };

        timer = new Timer(delay, taskTimer);
    }

    //set key event when key is pressed
    public void setKeys(){

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event){

                if(event.getKeyCode() == KeyEvent.VK_LEFT){

                    if(snake.getDirection() != Line.RIGHT){ //cannot change direction to opposite direction
                        snake.setDirection(Line.LEFT);
                    }
                }else if(event.getKeyCode() == KeyEvent.VK_RIGHT){

                    if(snake.getDirection() != Line.LEFT){  //cannot change direction to opposite direction
                        snake.setDirection(Line.RIGHT);
                    }
                }else if(event.getKeyCode() == KeyEvent.VK_UP){

                    if(snake.getDirection() != Line.DOWN){  //cannot change direction to opposite direction
                        snake.setDirection(Line.UP);
                    }
                }else if(event.getKeyCode() == KeyEvent.VK_DOWN){

                    if(snake.getDirection() != Line.UP){    //cannot change direction to opposite direction
                        snake.setDirection(Line.DOWN);
                    }
                }else if(event.getKeyCode() == KeyEvent.VK_ESCAPE){

                    if(flag == false && dead == false){ //if not pause
                        timer.stop(); //stop snake animation
                        drawPause((Graphics2D)getGraphics());
                        flag=true;
                    }else if(flag == true && dead == false){ //if pause
                        repaint();
                        timer.start(); //start snake animation
                        flag=false;
                    }
                }
            }
        });
    }

    //insted refresh method, to use that call repaint();
    @Override
    public void paintComponent(Graphics gc){
        Graphics2D g2d = (Graphics2D) gc;
        super.paintComponent(gc);

//        for(int row=0; row<width; row=row+pixels){
//            for(int col=0; col<height; col=col+pixels){
//                if(row <= 10 || row >= width-pixels*2 || col <= 10 || col >= height-pixels*2){
//                    g2d.setColor(Color.BLACK);
//                }else{
//                    g2d.setColor(Color.GREEN);
//                }
//                g2d.fillRect(row, col, pixels, pixels);
//            }
//        }

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,600, 600);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(pixels*2, pixels*2, (int)width-pixels*4, (int)height-pixels*4);

//        snake.drawSnake(g2d);
//        food.drawFood(g2d);

        drawSnake(g2d);
        drawFood(g2d);

        if(flag) {
            drawPause(g2d);
        }

//        drawSnake();
//        drawFood();
//        if(flag) drawPause();
    }

    //draw pause text
    public void drawPause(){
        Graphics2D g2d = (Graphics2D) getGraphics();
        drawPause(g2d);
    }

    //draw pause text
    public void drawPause(Graphics2D g2d){
        g2d.setColor(Color.BLUE);
        //graphics.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        g2d.setFont(new Font(null, Font.BOLD, 36));
        g2d.drawString("Press ESC key to start", 100, 100);
    }

    //draw game over
    public void drawGameOver(){
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(Color.RED);
        //graphics.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        g2d.setFont(new Font(null, Font.BOLD, 36));
        g2d.drawString("Game Over!", 200, 100);
    }

    //set food location, color and clear last food position
    public void setFood(){
        //food.clearFood(graphics);
        food.setRandomColor();
        setFoodLocation();
    }

    //set food position
    private void setFoodLocation(){
        int x, y;
        Random generator = new Random();
        do{
            x = generator.nextInt((int)(width-pixels*2)/10)*10;
            y = generator.nextInt((int)(height-pixels*2)/10)*10;
        }while(!isCorrect(x,y));

        food.setPosition(x,y);
        food.setRandomColor();
    }

    //check if random food position is correct
    public boolean isCorrect(int x, int y){

        //check if new food position is not in snake body
        LinkedList<Vector> tempBody = snake.getBody();

        for(Vector c: tempBody){
            if(x==c.getX() && y == c.getY()){
                return false;
            }
        }

        //check if food position is not in board border
        if(x<=10){
            return false;
        }

        if(y<=10){
            return false;
        }

        return true;
    }

    //set Frame (BorderPane) for easy access to setScore and setFood
    public void setFrame(Frame frame){
        this.frame=frame;
    }

    public int getScore(){
        return score;
    }
}
