package config;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    final String PORT = "5432";
    final String USER = "postgres";
    final String PASSWORD = "a";
    final String DATA_BASE_NAME = "camioneros";
    public Connection obtenerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:"+PORT+"/"+DATA_BASE_NAME+"",USER, PASSWORD);
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void test() throws SQLException {

        Connection conn = obtenerConexion();
        String sql = "select top 5 Vn_Comentario from Venta;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String aidi;
        while (rs.next()) {
            aidi = rs.getString(1);
            System.out.println(aidi);
        }

    }

}
