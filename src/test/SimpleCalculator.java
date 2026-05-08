package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * An enhanced simple calculator application with colors, an image, and dialog results.
 */
public class SimpleCalculator extends JFrame {
    private JTextField num1Field;
    private JTextField num2Field;

    public SimpleCalculator() {
        // Window settings
        setTitle("Colorful Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Header with Image and Title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel Blue
        headerPanel.setLayout(new FlowLayout());
        
        // Generate a simple image programmatically
        ImageIcon icon = createSimpleIcon();
        JLabel imageLabel = new JLabel(icon);
        headerPanel.add(imageLabel);
        
        JLabel titleLabel = new JLabel("My Cool Calculator");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // 2. Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputPanel.setBackground(new Color(240, 248, 255)); // Alice Blue

        JLabel l1 = new JLabel("Number 1:");
        l1.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(l1);
        num1Field = new JTextField();
        inputPanel.add(num1Field);

        JLabel l2 = new JLabel("Number 2:");
        l2.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(l2);
        num2Field = new JTextField();
        inputPanel.add(num2Field);
        add(inputPanel, BorderLayout.CENTER);

        // 3. Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton addButton = createColoredButton("+", new Color(144, 238, 144)); // Light Green
        JButton subButton = createColoredButton("-", new Color(255, 182, 193)); // Light Pink
        JButton mulButton = createColoredButton("*", new Color(173, 216, 230)); // Light Blue
        JButton divButton = createColoredButton("/", new Color(255, 255, 224)); // Light Yellow

        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listener
        ActionListener listener = e -> calculate(e.getActionCommand());

        addButton.addActionListener(listener);
        subButton.addActionListener(listener);
        mulButton.addActionListener(listener);
        divButton.addActionListener(listener);
    }

    private JButton createColoredButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setFocusPainted(false);
        return btn;
    }

    private ImageIcon createSimpleIcon() {
        // Create a 40x40 image with a simple pattern
        BufferedImage img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.ORANGE);
        g2.fillRect(0, 0, 40, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(5, 5, 30, 30);
        g2.drawLine(10, 20, 30, 20);
        g2.drawLine(20, 10, 20, 30);
        g2.dispose();
        return new ImageIcon(img);
    }

    private void calculate(String operator) {
        try {
            double n1 = Double.parseDouble(num1Field.getText());
            double n2 = Double.parseDouble(num2Field.getText());
            double result = 0;

            switch (operator) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "*": result = n1 * n2; break;
                case "/":
                    if (n2 == 0) {
                        JOptionPane.showMessageDialog(this, "Error: Division by 0", "Calculator Result", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = n1 / n2;
                    break;
            }
            
            String resultStr;
            if (result == (long) result) {
                resultStr = String.format("%d", (long) result);
            } else {
                resultStr = String.format("%.2f", result);
            }

            // Display result in a small window (dialog)
            JOptionPane.showMessageDialog(this, "The result is: " + resultStr, "Calculation Result", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleCalculator().setVisible(true);
        });
    }
}
