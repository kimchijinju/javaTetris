package Model;

public abstract class Tetromino {
    protected int x;
    protected int y;
    protected int rotate;
    protected int[][][] shape;

    protected void changeRotation() {
        if(this.rotate >= 3){
            rotate = 0;
            return;
        }
        rotate++;
    }

}
