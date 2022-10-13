import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calc {

    JFrame frame;
    JTextField textfield;
    JButton[] numButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton;
    JButton subButton;
    JButton mulButton;
    JButton divButton;
    JButton decButton;
    JButton equButton;
    JButton delButton;
    JButton clrButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);
    double num1=0;
    double num2=0;
    double result=0;
    char operator;

    Calc(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);

        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calc Calulator = new Calc();
    }

    //@Override
    public void actionPerformed(ActionEvent e) {

    }
}
