package dataaccess;

import java.sql.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    
    private static final EntityManagerFactory EMF = 
               Persistence.createEntityManagerFactory("UsersPU");
    
    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static EntityManagerFactory getEmFactory() {
        return EMF;
    }
}
