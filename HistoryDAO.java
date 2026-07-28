import java.sql.*;
import java.util.ArrayList;

public class HistoryDAO {

    // Save history
    public void saveHistory(History history) {

        String sql = "INSERT INTO history(tool_name,input_data,output_data) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, history.getToolName());
            pst.setString(2, history.getInputData());
            pst.setString(3, history.getOutputData());

            pst.executeUpdate();

            System.out.println("History Saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all history
    public ArrayList<History> getAllHistory() {

        ArrayList<History> list = new ArrayList<>();

        String sql = "SELECT * FROM history ORDER BY id DESC";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                History h = new History();

                h.setId(rs.getInt("id"));
                h.setToolName(rs.getString("tool_name"));
                h.setInputData(rs.getString("input_data"));
                h.setOutputData(rs.getString("output_data"));
                h.setCreatedAt(rs.getString("created_at"));

                list.add(h);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Delete by ID
    public void deleteHistory(int id) {

        String sql = "DELETE FROM history WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clear all history
    public void clearHistory() {

        String sql = "DELETE FROM history";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}