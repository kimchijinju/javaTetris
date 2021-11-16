package Model;

public class TetrominoZ extends Tetromino {
    public TetrominoZ() {
        x = 3;
        y = 0;
        rotate = 0;
        shape = new int[][][]{
                {
                        {1, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {1, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                {
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}
                }

        };
    }
}
