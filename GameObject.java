public class GameObject {

}

import java.awt.Rectangle;

public class Test {
   public static void main(String[] args) {
      Rectangle a = new Rectangle(2, 4, 3, 5);
      Rectangle b = new Rectangle(3, 4, 3, 5);
      System.out.println(a.intersects(b));
   }
}
