import java.sql.*;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false";

    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "insert into EMPLOYEE(name,age,sex) values('john',13,'f')";
            PreparedStatement preStmt=(PreparedStatement)conn.prepareStatement(sql);
            preStmt.executeUpdate();

            sql = "SELECT * FROM EMPLOYEE";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int age = rs.getInt("age");
                String name = rs.getString("name");
                String sex = rs.getString("sex");

                System.out.println(name + " " + age + " " + sex);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}