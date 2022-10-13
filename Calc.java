import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numButtons = new JButton[10];
    JButton[] functionButtons = new JButton[20];
    JButton addButton;
    JButton subButton;
    JButton mulButton;
    JButton divButton;
    JButton decButton;
    JButton equButton;
    JButton delButton;
    JButton clrButton;
    JButton negButton;
    JButton expButton;
    JButton sinButton;
    JButton cosButton;
    JButton tanButton;
    JButton cotButton;
    JButton lnButton;
    JButton log10Button;
    JButton lPrenButton;
    JButton rPrenButton;
    JButton lBracButton;
    JButton rBracButton;
    JPanel panel;

    Font myFont = new Font("Times New Roman", Font.BOLD, 30);
    double num1 = 0;
    double num2 = 0;
    double result = 0;
    String operator;

    Calc(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 750);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 600, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("delete");
        clrButton = new JButton("clear");
        negButton = new JButton("(-)");
        expButton = new JButton("^");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        cotButton = new JButton("cot");
        lnButton = new JButton("ln");
        log10Button = new JButton("log10");
        lPrenButton = new JButton("(");
        rPrenButton = new JButton(")");
        lBracButton = new JButton("{");
        rBracButton = new JButton("}");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = expButton;
        functionButtons[10] = sinButton;
        functionButtons[11] = cosButton;
        functionButtons[12] = tanButton;
        functionButtons[13] = cotButton;
        functionButtons[14] = lnButton;
        functionButtons[15] = log10Button;
        functionButtons[16] = lPrenButton;
        functionButtons[17] = rPrenButton;
        functionButtons[18] = lBracButton;
        functionButtons[19] = rBracButton;

        for(int i = 0; i < 20; i++)
        {
            functionButtons[i].addActionListener( this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for(int i = 0; i < 10; i++)
        {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener( this);
            numButtons[i].setFont(myFont);
            numButtons[i].setFocusable(false);
        }
        negButton.setBounds(50,650,200,50);
        delButton.setBounds(250,650,200,50);
        clrButton.setBounds(450,650,200,50);
        lPrenButton.setBounds(50,550,150,50);
        rPrenButton.setBounds(200,550,150,50);
        lBracButton.setBounds(350,550,150,50);
        rBracButton.setBounds(500,550,150,50);
        expButton.setBounds(50,600,200,50);
        equButton.setBounds(450,600,200,50);
        decButton.setBounds(250,600,200,50);


        panel = new JPanel();
        panel.setBounds(50,100,600,400);
        panel.setLayout(new GridLayout(5,4, 15,15));

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(mulButton);
        panel.add(numButtons[0]);
        panel.add(divButton);
        panel.add(sinButton);
        panel.add(cosButton);
        panel.add(tanButton);
        panel.add(cotButton);
        panel.add(lnButton);
        panel.add(log10Button);

        frame.add(expButton);
        frame.add(equButton);
        frame.add(decButton);
        frame.add(lPrenButton);
        frame.add(rPrenButton);
        frame.add(lBracButton);
        frame.add(rBracButton);
        frame.add(negButton);
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calc Calulator = new Calc();
    }
    //Eval Function I found and modified to work with all the required functions
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++)
        {
            if(e.getSource() == numButtons[i]){
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton){
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == lPrenButton){
            textfield.setText(textfield.getText().concat("("));
        }
        if (e.getSource() == rPrenButton){
            textfield.setText(textfield.getText().concat(")"));
        }
        if (e.getSource() == lBracButton){
            textfield.setText(textfield.getText().concat("{"));
        }
        if (e.getSource() == rBracButton){
            textfield.setText(textfield.getText().concat("}"));
        }

        if (e.getSource() == addButton){
            textfield.setText(textfield.getText().concat("+"));
            //num1 = Double.parseDouble(textfield.getText());
            //operator = "+";
            //textfield.setText("");
        }

        if (e.getSource() == subButton){
            textfield.setText(textfield.getText().concat("-"));
            //num1 = Double.parseDouble(textfield.getText());
            //operator = "-";
            //textfield.setText("");
        }

        if (e.getSource() == sinButton){
            textfield.setText(textfield.getText().concat("sin"));
            //num1 = Double.parseDouble(textfield.getText());
            //operator = "sin";

        }

        if (e.getSource() == cosButton){
            textfield.setText(textfield.getText().concat("cos"));
            //num1 = Double.parseDouble(textfield.getText());
            //operator = "cos";

        }

        if (e.getSource() == tanButton){
            textfield.setText(textfield.getText().concat("tan"));
            //num1 = Double.parseDouble(textfield.getText());
            //operator = "tan";

        }

        if (e.getSource() == cotButton){
            textfield.setText(textfield.getText().concat("cot"));
            //num1 = Double.parseDouble(textfield.getText());
            //operator = "cot";

        }

        if (e.getSource() == lnButton){
            num1 = Double.parseDouble(textfield.getText());
            operator = "ln";

        }

        if (e.getSource() == log10Button){
            num1 = Double.parseDouble(textfield.getText());
            operator = "log10";

        }

        if (e.getSource() == mulButton){
            textfield.setText(textfield.getText().concat("*"));
            //num1 = Double.parseDouble(textfield.getText());
            //operator = "*";
            //textfield.setText("");
        }

        if (e.getSource() == divButton){
            textfield.setText(textfield.getText().concat("/"));

        }

        if (e.getSource() == equButton){
            //num2 = Double.parseDouble(textfield.getText());

            /*switch(operator) {
                case"+":
                    result = num1 + num2;
                    break;
                case"-":
                    result = num1 - num2;
                    break;
                case"*":
                    result = num1 * num2;
                    break;
                case"/":
                    result = num1 / num2;
                    break;
                case"sin":
                    num1 = Math.toRadians(num1);
                    result = Math.sin(num1);
                    break;
                case"cos":
                    num1 = Math.toRadians(num1);
                    result = Math.cos(num1);
                    break;
                case"tan":
                    num1 = Math.toRadians(num1);
                    result = Math.tan(num1);
                    break;
                case"cot":
                    num1 = Math.toRadians(num1);
                    result = 1.0 / Math.tan(num1);
                    break;
                case"ln":
                    result = Math.log(num1);
                    break;
                case"log10":
                    result = Math.log10(num1);
                    break;
            }*/
            result = eval(textfield.getText());
            textfield.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == clrButton){
            textfield.setText("");
        }

        if(e.getSource() == delButton){
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length()-1; i++){
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }

        if(e.getSource() == negButton){
            double temp = Double.parseDouble(textfield.getText());
            temp*=-1;
            textfield.setText(String.valueOf(temp));
        }
    }
}
