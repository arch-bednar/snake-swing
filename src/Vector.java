public class Vector {

    /*
        based class for food and snake's body
        it's like Point from awt
     */

    private double x, y;

    Vector(double x, double y){
        this.x=x;
        this.y=y;
    }

    Vector(){
        this(0,0);
    }

    public void setPosition(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public Vector getVector(){
        return new Vector(x,y);
    }
}
