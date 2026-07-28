import java.awt.*;
import java.awt.event.*;

public class MainFrame extends Frame implements ActionListener {

    Button btnFormatter, btnJson, btnPassword, btnEncoder,
           btnWordCounter, btnHistory, btnExit;

    public MainFrame() {

        setTitle("Developer Toolkit");
        setSize(500, 500);
        setLayout(new GridLayout(7, 1, 10, 10));
        setLocationRelativeTo(null);

        btnFormatter = new Button("Code Formatter");
        btnJson = new Button("JSON Validator");
        btnPassword = new Button("Password Generator");
        btnEncoder = new Button("Text Encoder");
        btnWordCounter = new Button("Word Counter");
        btnHistory = new Button("History");
        btnExit = new Button("Exit");

        add(btnFormatter);
        add(btnJson);
        add(btnPassword);
        add(btnEncoder);
        add(btnWordCounter);
        add(btnHistory);
        add(btnExit);

        btnFormatter.addActionListener(this);
        btnJson.addActionListener(this);
        btnPassword.addActionListener(this);
        btnEncoder.addActionListener(this);
        btnWordCounter.addActionListener(this);
        btnHistory.addActionListener(this);
        btnExit.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnFormatter) {
            new CodeFormatter();
        }
        else if (e.getSource() == btnJson) {
            new JsonValidator();
        }
        else if (e.getSource() == btnPassword) {
            new PasswordGenerator();
        }
        else if (e.getSource() == btnEncoder) {
            new TextEncoder();
        }
        else if (e.getSource() == btnWordCounter) {
            new WordCounter();
        }
        else if (e.getSource() == btnHistory) {
            new HistoryFrame();
        }
        else if (e.getSource() == btnExit) {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}