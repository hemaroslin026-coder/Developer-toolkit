import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HistoryFrame extends Frame implements ActionListener {

    List historyList;
    Button btnRefresh, btnClear, btnClose;

    HistoryDAO dao = new HistoryDAO();

    public HistoryFrame() {

        setTitle("Developer Toolkit - History");
        setSize(900,500);
        setLayout(null);

        Label lblTitle = new Label("History");
        lblTitle.setBounds(20,40,100,20);
        add(lblTitle);

        historyList = new List();
        historyList.setBounds(20,70,850,300);
        add(historyList);

        btnRefresh = new Button("Refresh");
        btnRefresh.setBounds(180,400,100,35);
        add(btnRefresh);

        btnClear = new Button("Clear All");
        btnClear.setBounds(350,400,100,35);
        add(btnClear);

        btnClose = new Button("Close");
        btnClose.setBounds(520,400,100,35);
        add(btnClose);

        btnRefresh.addActionListener(this);
        btnClear.addActionListener(this);
        btnClose.addActionListener(this);

        loadHistory();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void loadHistory() {

        historyList.removeAll();

        ArrayList<History> list = dao.getAllHistory();

        historyList.add("ID | TOOL | INPUT | OUTPUT | DATE");
        historyList.add("--------------------------------------------------------------");

        for(History h : list){

            historyList.add(
                    h.getId() + " | " +
                    h.getToolName() + " | " +
                    h.getInputData() + " | " +
                    h.getOutputData() + " | " +
                    h.getCreatedAt()
            );

        }

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnRefresh){

            loadHistory();

        }

        if(e.getSource()==btnClear){

            dao.clearHistory();
            loadHistory();

        }

        if(e.getSource()==btnClose){

            dispose();

        }

    }

    public static void main(String args[]){

        new HistoryFrame();

    }

}