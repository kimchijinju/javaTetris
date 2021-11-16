package Model;

public class TetrominoJ extends Tetromino {
    public TetrominoJ() {
        x = 3;
        y = 0;
        rotate = 0;
        shape = new int[][][]{
                {
                        {1, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {1, 1, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0}
                }

        };
    }
}
