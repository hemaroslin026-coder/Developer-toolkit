import java.awt.*;
import java.awt.event.*;
import java.util.Base64;

public class TextEncoder extends Frame implements ActionListener {

    Label lblInput, lblOutput;
    TextArea txtInput, txtOutput;
    Button btnEncode, btnDecode, btnClear;

    public TextEncoder() {

        setTitle("Text Encoder / Decoder");
        setSize(600,500);
        setLayout(null);

        lblInput = new Label("Input Text");
        lblInput.setBounds(30,40,100,20);
        add(lblInput);

        txtInput = new TextArea();
        txtInput.setBounds(30,70,520,120);
        add(txtInput);

        btnEncode = new Button("Encode");
        btnEncode.setBounds(70,220,100,35);
        add(btnEncode);

        btnDecode = new Button("Decode");
        btnDecode.setBounds(220,220,100,35);
        add(btnDecode);

        btnClear = new Button("Clear");
        btnClear.setBounds(370,220,100,35);
        add(btnClear);

        lblOutput = new Label("Output");
        lblOutput.setBounds(30,280,100,20);
        add(lblOutput);

        txtOutput = new TextArea();
        txtOutput.setBounds(30,310,520,120);
        txtOutput.setEditable(false);
        add(txtOutput);

        btnEncode.addActionListener(this);
        btnDecode.addActionListener(this);
        btnClear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnEncode){

            String input = txtInput.getText();

            String output = Base64.getEncoder()
                    .encodeToString(input.getBytes());

            txtOutput.setText(output);

            History history = new History(
                    "Text Encoder",
                    input,
                    output
            );

            new HistoryDAO().saveHistory(history);
        }

        if(e.getSource()==btnDecode){

            try{

                String input = txtInput.getText();

                String output = new String(
                        Base64.getDecoder().decode(input));

                txtOutput.setText(output);

                History history = new History(
                        "Text Decoder",
                        input,
                        output
                );

                new HistoryDAO().saveHistory(history);

            }catch(Exception ex){

                txtOutput.setText("Invalid Encoded Text");

            }

        }

        if(e.getSource()==btnClear){

            txtInput.setText("");
            txtOutput.setText("");

        }

    }

    public static void main(String args[]){

        new TextEncoder();

    }

}