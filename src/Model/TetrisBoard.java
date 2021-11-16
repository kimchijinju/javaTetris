package Model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class TetrisBoard {

    private final int MAX_HEIGHT;
    private final int MAX_WIDTH;
    private static Stack<Tetromino> tetrominoCycle;
    private int[][] viewBoard;
    private int[][] dataBoard;
    private Tetromino block;

    public TetrisBoard(int height, int width) {
        MAX_HEIGHT = height;
        MAX_WIDTH = width;
        dataBoard = new int[MAX_HEIGHT][MAX_WIDTH];
        viewBoard = new int[MAX_HEIGHT][MAX_WIDTH];
        tetrominoCycle = new Stack<>();
        createNewBlock();
    }

    public void moveBlock(int keycode) {
        switch (keycode) {
            case 37:
                moveLeft();
                break;
            case 39:
                moveRight();
                break;
            case 40:
                downBlock();
                break;
            case 38:
                rotationBlock();
                break;
            case 32:
                downBottom();
                break;
            default:
        }
    }

    private void printScreen() {
        System.out.println(System.lineSeparator().repeat(100));
        for (int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                if (viewBoard[i][j] == 0) {
                    System.out.print("□");
                    continue;
                }
                System.out.print("■");
            }
            System.out.println();
        }
    }

    private void createNewBlock() {

        if (tetrominoCycle.size() == 0) { // 테트리스 7-bag 시스템
            HashSet<Tetromino> set = new HashSet<>();
            set.add(new TetrominoI());
            set.add(new TetrominoJ());
            set.add(new TetrominoL());
            set.add(new TetrominoO());
            set.add(new TetrominoS());
            set.add(new TetrominoT());
            set.add(new TetrominoZ());
            Iterator<Tetromino> it = set.iterator();
            while (it.hasNext())
                tetrominoCycle.push(it.next());
        }

        this.block = tetrominoCycle.pop();
        drawBlock();
    }

    private boolean downBlock() {
        int removeY = block.y;
        int removeX = block.x;
        block.y++;
        if (isReached()) {
            block.y--;
            addData();
            return false;
        }
        drawNextLocation(block.rotate, removeY, removeX);
        return true;
    }

    private void downBottom() {
        while (downBlock()) ;
    }

    // 움직이기 전의 좌표에 표시된 블럭을 지우고, 움직인 부분에 다시 그림
    private void drawNextLocation(int r, int y, int x) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (block.shape[r][i][j] == 1)
                    viewBoard[y + i][x + j] = 0;
            }
        }
        drawBlock();
    }

    private void drawBlock() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (block.shape[block.rotate][i][j] == 1)
                    viewBoard[block.y + i][block.x + j] = 1;

            }
        }
        printScreen();
    }

    //끝에 도달했다면, 도달한 블럭을 데이터 배열에 표기 후 한 줄이 꽉찼는지 검사하고 새 블럭을 생성함
    private void addData() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (block.shape[block.rotate][i][j] == 1)
                    dataBoard[i + block.y][j + block.x] = 1;
            }
        }
        lineClear();
        createNewBlock();
    }

    // 블럭이 맨 밑이거나 양 끝에 도달했는지 검사
    private boolean isReached() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (block.shape[block.rotate][i][j] == 1) {
                    if (block.y + i >= MAX_HEIGHT || block.y < 0 || block.x + j >= MAX_WIDTH || block.x < 0)
                        return true;
                    if (dataBoard[block.y + i][block.x + j] == 1)
                        return true;
                }
            }
        }
        return false;
    }


    private void moveRight() {
        int removeY = block.y;
        int removeX = block.x;
        block.x++;
        if (isReached()) {
            block.x--;
            printScreen();
            return;
        }
        drawNextLocation(block.rotate, removeY, removeX);
    }

    private void moveLeft() {
        int removeY = block.y;
        int removeX = block.x;
        block.x--;
        if (isReached()) {
            block.x++;
            printScreen();
            return;
        }
        drawNextLocation(block.rotate, removeY, removeX);
    }

    private void rotationBlock() {
        int previousShape = block.rotate;
        int removeY = block.y;
        int removeX = block.x;

        block.changeRotation();

        while (isReached()) relocation();

        drawNextLocation(previousShape, removeY, removeX);
    }

    private void relocation() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (block.shape[block.rotate][i][j] == 1) {
                    if (block.y + i >= MAX_HEIGHT)
                        block.y--;
                    if (block.x + j >= MAX_WIDTH)
                        block.x--;
                    if (block.y < 0)
                        block.y++;
                    if (block.x < 0)
                        block.x++;
                    if (dataBoard[block.y + i][block.x + j] == 1)
                        block.y--;
                }
            }
        }

    }

    private int isFull() {
        for (int i = MAX_HEIGHT - 1; i >= 0; i--) {
            int count = 0;
            for (int j = 0; j < MAX_WIDTH; j++) {
                if (dataBoard[i][j] == 1) count++;
                if (count == MAX_WIDTH) return i;
            }

        }
        return -1;
    }

    private void lineClear() {
        int clearLine = isFull();
        if (clearLine == -1) return;

        for (int i = clearLine; i > 0; i--) {
            for (int j = 0; j < MAX_WIDTH; j++) {
                dataBoard[i][j] = dataBoard[i - 1][j];
                viewBoard[i][j] = dataBoard[i][j];
            }
        }

        lineClear();
    }

}
