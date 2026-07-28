import java.awt.*;
import java.awt.event.*;
import org.json.JSONObject;

public class JsonValidator extends Frame implements ActionListener {

    Label lblInput, lblResult;
    TextArea txtJson;
    Button btnValidate, btnClear;

    public JsonValidator() {

        setTitle("JSON Validator");
        setSize(500,400);
        setLayout(null);

        lblInput = new Label("Enter JSON:");
        lblInput.setBounds(30,40,100,20);
        add(lblInput);

        txtJson = new TextArea();
        txtJson.setBounds(30,70,430,180);
        add(txtJson);

        btnValidate = new Button("Validate");
        btnValidate.setBounds(100,280,100,30);
        add(btnValidate);

        btnClear = new Button("Clear");
        btnClear.setBounds(250,280,100,30);
        add(btnClear);

        lblResult = new Label("");
        lblResult.setBounds(30,330,300,20);
        add(lblResult);

        btnValidate.addActionListener(this);
        btnClear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnValidate){

            String input = txtJson.getText();

            try{

                new JSONObject(input);

                lblResult.setText("Valid JSON");

                History history = new History(
                        "JSON Validator",
                        input,
                        "Valid JSON"
                );

                new HistoryDAO().saveHistory(history);

            }
            catch(Exception ex){

                lblResult.setText("Invalid JSON");

                History history = new History(
                        "JSON Validator",
                        input,
                        "Invalid JSON"
                );

                new HistoryDAO().saveHistory(history);

            }

        }

        if(e.getSource()==btnClear){

            txtJson.setText("");
            lblResult.setText("");

        }

    }

    public static void main(String args[]){

        new JsonValidator();

    }

}