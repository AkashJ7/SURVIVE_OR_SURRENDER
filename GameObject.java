import java.awt.Rectangle;

public class GameObject extends Rectangle {

	public GameObject(double x, double y, double w, double h) { super(x, y, w, h); }
}

// EXAMPLE CODE FOR INTERSECTION:
/*
public class Test {
   public static void main(String[] args) {
      Rectangle a = new Rectangle(2, 4, 3, 5);
      Rectangle b = new Rectangle(3, 4, 3, 5);
      System.out.println(a.intersects(b));
   }
}
*/
