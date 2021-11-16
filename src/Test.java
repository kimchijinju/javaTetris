import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws InterruptedException, IOException {
        int height = 15;
        int width = 10;
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter to Start");
        if (sc.nextLine().equals("")) {
            Controller controller = new Controller(height, width);
            JFrame frame = new JFrame();
            frame.setSize(300, 200);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.addKeyListener(controller);
            while (true) {
                controller.update();
                Thread.sleep(1000);
            }
        }
    }

}
