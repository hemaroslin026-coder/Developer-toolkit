import java.awt.*;
import java.awt.event.*;

public class CodeFormatter extends Frame implements ActionListener {

    Label lblInput, lblOutput;
    TextArea txtInput, txtOutput;
    Button btnFormat, btnClear;

    public CodeFormatter() {

        setTitle("Code Formatter");
        setSize(700,550);
        setLayout(null);

        lblInput = new Label("Original Code");
        lblInput.setBounds(30,40,120,20);
        add(lblInput);

        txtInput = new TextArea();
        txtInput.setBounds(30,70,620,150);
        add(txtInput);

        btnFormat = new Button("Format");
        btnFormat.setBounds(180,240,100,35);
        add(btnFormat);

        btnClear = new Button("Clear");
        btnClear.setBounds(350,240,100,35);
        add(btnClear);

        lblOutput = new Label("Formatted Code");
        lblOutput.setBounds(30,300,120,20);
        add(lblOutput);

        txtOutput = new TextArea();
        txtOutput.setBounds(30,330,620,160);
        txtOutput.setEditable(false);
        add(txtOutput);

        btnFormat.addActionListener(this);
        btnClear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnFormat){

            String input = txtInput.getText();

            String[] lines = input.split("\n");

            StringBuilder formatted = new StringBuilder();

            int indent = 0;

            for(String line : lines){

                line = line.trim();

                if(line.isEmpty())
                    continue;

                if(line.startsWith("}"))
                    indent--;

                for(int i=0;i<indent;i++)
                    formatted.append("    ");

                formatted.append(line).append("\n");

                if(line.endsWith("{"))
                    indent++;
            }

            txtOutput.setText(formatted.toString());

            History history = new History(
                    "Code Formatter",
                    input,
                    formatted.toString()
            );

            new HistoryDAO().saveHistory(history);

        }

        if(e.getSource()==btnClear){

            txtInput.setText("");
            txtOutput.setText("");

        }

    }

    public static void main(String args[]) {

        new CodeFormatter();

    }

}