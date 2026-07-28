import java.awt.*;
import java.awt.event.*;

public class WordCounter extends Frame implements ActionListener {

    Label lblInput, lblResult;
    TextArea txtInput;
    Button btnCount, btnClear;

    public WordCounter() {

        setTitle("Word Counter");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        lblInput = new Label("Enter Text:");
        lblInput.setBounds(30, 50, 100, 20);
        add(lblInput);

        txtInput = new TextArea();
        txtInput.setBounds(30, 80, 430, 150);
        add(txtInput);

        btnCount = new Button("Count");
        btnCount.setBounds(100, 250, 100, 35);
        btnCount.addActionListener(this);
        add(btnCount);

        btnClear = new Button("Clear");
        btnClear.setBounds(250, 250, 100, 35);
        btnClear.addActionListener(this);
        add(btnClear);

        lblResult = new Label("Words: 0   Characters: 0");
        lblResult.setBounds(30, 310, 300, 20);
        add(lblResult);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCount) {

            String text = txtInput.getText().trim();

            int words = 0;

            if (!text.isEmpty()) {
                words = text.split("\\s+").length;
            }

            int characters = text.length();

            String result = "Words: " + words +
                    "   Characters: " + characters;

            lblResult.setText(result);

            // Save into MySQL
            History history = new History(
                    "Word Counter",
                    text,
                    result
            );

            HistoryDAO dao = new HistoryDAO();
            dao.saveHistory(history);
        }

        if (e.getSource() == btnClear) {
            txtInput.setText("");
            lblResult.setText("Words: 0   Characters: 0");
        }
    }
}