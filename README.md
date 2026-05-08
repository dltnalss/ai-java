# README.md 파일 작성
## ai를 활용한 백엔드 개발


 
---

```markdown
# 🎨 Colorful Simple Calculator

Java Swing으로 만든 **색상과 이미지를 활용한 예쁜 계산기**입니다.  
더하기, 빼기, 곱하기, 나누기 기능을 지원하며, 결과를 다이얼로그로 표시합니다.

## ✨ 주요 기능

- **예쁜 UI 디자인** (색상 테마 적용)
- **헤더에 프로그램 아이콘** (BufferedImage로 생성)
- **4가지 연산** (+, -, *, /)
- **0으로 나누기 오류 처리**
- **정수/실수 결과 자동 처리**
- **입력 오류 처리** (NumberFormatException)

## 📁 프로젝트 구조

```bash
SimpleCalculator/
├── src/
│   └── test/
│       └── SimpleCalculator.java     # 메인 클래스 (단일 파일)
├── README.md
└── (선택) SimpleCalculator.jar      # 컴파일 후 생성 가능
```

> **단일 파일 프로젝트**이므로 `src/test/SimpleCalculator.java`에 코드를 저장하면 됩니다.

## 🚀 실행 방법

### 1. 컴파일 및 실행 (터미널)

```bash
# 1. 컴파일
javac -d . src/test/SimpleCalculator.java

# 2. 실행
java test.SimpleCalculator
```

### 2. IDE 사용 시
- IntelliJ IDEA, Eclipse, VS Code 등에서 `SimpleCalculator.java`를 열고 Run

## 📋 전체 소스 코드

```java
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

        JButton addButton = createColoredButton("+", new Color(144, 238, 144));
        JButton subButton = createColoredButton("-", new Color(255, 182, 193));
        JButton mulButton = createColoredButton("*", new Color(173, 216, 230));
        JButton divButton = createColoredButton("/", new Color(255, 255, 224));

        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listener (람다식)
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
                        JOptionPane.showMessageDialog(this, 
                            "Error: Division by 0", 
                            "Calculator Result", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = n1 / n2;
                    break;
            }
            
            String resultStr = (result == (long) result) 
                    ? String.format("%d", (long) result) 
                    : String.format("%.2f", result);

            JOptionPane.showMessageDialog(this, 
                "The result is: " + resultStr, 
                "Calculation Result", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: Please enter valid numbers", 
                "Input Error", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleCalculator().setVisible(true);
        });
    }
}
```

## 📚 학습 개념 상세 설명

### 1. **Swing GUI 컴포넌트**
- `JFrame`: 메인 윈도우
- `JPanel`: 컴포넌트를 그룹화
- `JTextField`: 사용자 입력 필드
- `JButton`: 버튼
- `JLabel`: 텍스트와 이미지 표시
- `JOptionPane`: 모달 다이얼로그 (결과/오류 표시)

### 2. **레이아웃 매니저**
- `BorderLayout`: NORTH / CENTER / SOUTH 배치
- `GridLayout`: 격자 형태 배치 (입력창, 버튼)
- `FlowLayout`: 좌→우 흐름 배치 (헤더)

### 3. **이벤트 처리**
- `ActionListener` + 람다식 (`e -> calculate(...)`)
- `e.getActionCommand()`로 버튼 구분

### 4. **이미지 생성**
- `BufferedImage` + `Graphics2D`를 이용한 **프로그래밍 방식 아이콘 생성**

### 5. **예외 처리**
- `NumberFormatException`, 0으로 나누기

## 🔗 참고 자료

- [Java Swing Official Tutorial (Oracle)](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Java Swing Layout Managers](https://docs.oracle.com/javase/tutorial/uiswing/layout/index.html)
- [BufferedImage로 그래픽 그리기](https://docs.oracle.com/javase/tutorial/2d/images/index.html)
- [JOptionPane 사용법](https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html)
- [Java GUI Best Practices](https://www.oracle.com/java/technologies/gui-best-practices.html)

---

**필요하시면** `build.gradle`, `pom.xml`, 또는 **실행 가능한 JAR** 만드는 방법도 추가해 드릴 수 있습니다!  
원하는 내용 더 있으면 말씀해주세요.
