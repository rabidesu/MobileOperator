import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {
    public static void main (String[] args){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "SqlPass");){
            if (!con.isClosed()){
                System.out.println("Connected to MySQL");
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ticket JOIN passenger ON "
                        + "ticket.Passenger_id = passenger.id WHERE passenger.name = ?");
                pstmt.setString(1, "Ivanov");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()){
                    System.out.println(rs.getInt(1));
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
