package report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author juan.serranoUSAM
 */
public class Conexion {

    static String bd = "restaurantes";
    static String user = "root";
    static String pass = "root";
    static String url = "jdbc:mysql://localhost/" + bd;
    static String driv = "com.mysql.jdbc.Driver";

    Connection conn = null;

    public Conexion() {
        try {
            Class.forName(driv);
            conn = DriverManager.getConnection(url, user, pass);

            if (conn != null) {
                System.out.println("exito de conexion");
            }
        } catch (Exception e) {
            System.err.println("error de conexion");
        }
    }

    public Connection conectar() {
        return conn;
    }

    public void desconectar() throws Exception {
        conn.close();
    }
}
