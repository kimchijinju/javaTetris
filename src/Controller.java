import Model.TetrisBoard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller extends Thread implements KeyListener {
    private TetrisBoard tetrisBoard;
    public Controller(int height, int width) throws InterruptedException {
        tetrisBoard = new TetrisBoard(height,width);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        tetrisBoard.moveBlock(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    public void update() {
        tetrisBoard.moveBlock(40);
    }

}
