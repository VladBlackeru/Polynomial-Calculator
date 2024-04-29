// UI.java
import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private JPanel panel1;
    private JButton ADDButton;
    private JButton substractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton derivateButton;
    private JButton integrateButton;
    private JTextField textField1;
    private JTextField textField2;
    private PolynomyalStringCalculator calculator;

    public static void main(String[] args) {
        double x = 1.0;
        new UI();
    }

    public UI() {
        calculator = new PolynomyalStringCalculator();

        initUI();

        ADDButton.addActionListener(e -> handleAdd());
        substractButton.addActionListener(e -> handleSubtract());
        multiplyButton.addActionListener(e -> handleMultiply());
        derivateButton.addActionListener(e -> handleDerivative());
        integrateButton.addActionListener(e -> handleIntegrate());
        divideButton.addActionListener(e-> handleDivision());
    }

    private void initUI() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2 - 200, size.height / 2 - getHeight() / 2 - 200);
        setContentPane(panel1);
        setTitle("Polynomial Calculator");
        setSize(500, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void handleAdd() {
        String text1 = textField1.getText();
        String text2 = textField2.getText();
        String result = calculator.addPolynomials(text1, text2);
        JOptionPane.showMessageDialog(null, result);
    }

    private void handleSubtract() {
        String text1 = textField1.getText();
        String text2 = textField2.getText();
        String result = calculator.subtractPolynomials(text1, text2);
        JOptionPane.showMessageDialog(null, result);
    }

    private void handleMultiply() {
        String text1 = textField1.getText();
        String text2 = textField2.getText();
        String result = calculator.multiplyPolynomials(text1, text2);
        JOptionPane.showMessageDialog(null, result);
    }

    private void handleDerivative() {
        String text = textField1.getText();
        String result = calculator.computeDerivative(text);
        JOptionPane.showMessageDialog(null, result);
    }

    private void handleIntegrate() {
        String text = textField1.getText();
        String result = calculator.computeIntegral(text);
        JOptionPane.showMessageDialog(null, result);
    }

    private void handleDivision(){
        String text1 = textField1.getText();
        String text2 = textField2.getText();
        String result = calculator.computeDivision(text1, text2);
        JOptionPane.showMessageDialog(null, result);
    }
}
