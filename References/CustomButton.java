import javax.swing.*;
import java.awt.*;

public class Test {
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
		frame.setSize(100, 50);
        JButton b = new JButton("Yes");//http://www.chacha.com/question/what-are-the-rgb-values-for-the-background-color-of-comments-on-facebook
        b.setBackground(new Color(59, 89, 182));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Tahoma", Font.BOLD, 12));//http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP
        frame.add(b);
        frame.pack();
        frame.setVisible(true);
	}
}
