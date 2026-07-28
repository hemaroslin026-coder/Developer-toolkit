import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PasswordGenerator extends Frame implements ActionListener {

    Label lblLength, lblResult;
    TextField txtLength, txtPassword;
    Button btnGenerate, btnClear;

    public PasswordGenerator() {

        setTitle("Password Generator");
        setSize(450,300);
        setLayout(null);

        lblLength = new Label("Password Length:");
        lblLength.setBounds(40,50,120,20);
        add(lblLength);

        txtLength = new TextField();
        txtLength.setBounds(180,50,150,25);
        add(txtLength);

        btnGenerate = new Button("Generate");
        btnGenerate.setBounds(80,100,100,30);
        btnGenerate.addActionListener(this);
        add(btnGenerate);

        btnClear = new Button("Clear");
        btnClear.setBounds(220,100,100,30);
        btnClear.addActionListener(this);
        add(btnClear);

        lblResult = new Label("Generated Password:");
        lblResult.setBounds(40,160,130,20);
        add(lblResult);

        txtPassword = new TextField();
        txtPassword.setBounds(180,160,200,25);
        txtPassword.setEditable(false);
        add(txtPassword);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnGenerate){

            try{

                int len=Integer.parseInt(txtLength.getText());

                String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";

                Random random=new Random();

                StringBuilder password=new StringBuilder();

                for(int i=0;i<len;i++){
                    password.append(chars.charAt(random.nextInt(chars.length())));
                }

                txtPassword.setText(password.toString());

                // Save to MySQL
                History history = new History(
                        "Password Generator",
                        "Length : " + len,
                        password.toString()
                );

                HistoryDAO dao = new HistoryDAO();
                dao.saveHistory(history);

            }catch(Exception ex){

                txtPassword.setText("Enter valid length");

            }

        }

        if(e.getSource()==btnClear){

            txtLength.setText("");
            txtPassword.setText("");

        }

    }

    public static void main(String args[]){

        new PasswordGenerator();

    }

}