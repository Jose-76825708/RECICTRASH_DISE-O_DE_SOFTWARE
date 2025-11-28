package app_movil_recictrash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cConnection_1 {
    // Configurables
    private String host = "LAPTOP-PFSJPAMS";
    private int port = 1433;
    private String database = "RecicTrashDB";

    // Credenciales
    private String usr = "RECIC_ADMIN";
    private String pswd = "76825708";

    private Connection con = null;

    public cConnection_1() {
        loadDriver();
    }

    private void loadDriver() {
        try {
            // Opcional en Java moderno si el JAR está en classpath, pero no hace daño:
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver JDBC de SQL Server cargado con éxito.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de SQL Server. Asegúrate de agregar el JAR al proyecto.");
            e.printStackTrace();
        }
    }

    private String buildUrl() {
        // Construye la URL de conexión. trustServerCertificate=true para evitar problemas con certificado autogenerado en pruebas.
        return String.format("jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=true;trustServerCertificate=true",
                host, port, database);
    }

    public Connection obtenerConexion() {
        String url = buildUrl();
        System.out.println("Intentando establecer conexión con SQL Server en: " + url);
        try {
            con = DriverManager.getConnection(url, usr, pswd);
            System.out.println("¡Conexión establecida con SQL Server exitosa!");
        } catch (SQLException sqle) {
            System.err.println("ERROR: No se pudo establecer la conexión con SQL Server.");
            System.err.println("Mensaje: " + sqle.getMessage());
            System.err.println("SQLState: " + sqle.getSQLState());
            System.err.println("Error Code: " + sqle.getErrorCode());
            sqle.printStackTrace();
            con = null;
        }
        return con;
    }

    public boolean closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
                System.out.println("Conexión con SQL Server cerrada con éxito.");
                return true;
            } catch (SQLException sqle) {
                System.err.println("ERROR: No se pudo cerrar la conexión con SQL Server.");
                System.err.println("Mensaje: " + sqle.getMessage());
                sqle.printStackTrace();
                return false;
            }
        } else {
            System.out.println("No hay conexión activa para cerrar.");
            return true;
        }
    }

    // MAIN de prueba
    public static void main(String[] args) {
        cConnection_1 db = new cConnection_1();
        try {
            Connection conn = db.obtenerConexion();
            if (conn != null) {
                System.out.println("La conexión de prueba fue exitosa.");
                // Ejemplo: consulta simple con try-with-resources
                /*
                try (Statement st = conn.createStatement();
                     ResultSet rs = st.executeQuery("SELECT TOP 1 GETDATE() AS Fecha")) {
                    if (rs.next()) {
                        System.out.println("Fecha servidor: " + rs.getString("Fecha"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                */
            } else {
                System.out.println("La conexión de prueba falló. Revisa logs y configuraciones.");
            }
        } finally {
            db.closeConnection();
        }
    }
}
