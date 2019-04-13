import javax.swing.JFrame;

public class Driver03 {

   public static void main(String[] args) {
      JFrame frame = new JFrame("Lab 03");
      frame.setSize(300, 250);
      frame.setLocation(1368/2 - 150, 768/2 - 125);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new Panel03());
      frame.setVisible(true);
   }
}
