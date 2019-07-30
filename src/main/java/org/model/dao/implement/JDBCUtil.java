package org.model.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {

    public static boolean checkNotMatch(String query, Connection connection) {
        boolean result = true;
        try (Statement st = connection.createStatement()) {
            if (st.executeQuery(query).next()) {
                result = false;
            }
        } catch(SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkMatch(String query, Connection connection) {
        boolean result = false;
        try (Statement st = connection.createStatement()) {
            if (st.executeQuery(query).next()) {
                result = true;
            }
        } catch(SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
        return result;
    }

    public static Long getLong(String query, String fieldName,
                               Connection connection) {
        Long res = 0L;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                res = rs.getLong(fieldName);
            }
        } catch (SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
        return res;
    }

    public static void makeUpdate(String query, Connection connection) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(query);
        } catch (SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
    }

//    public static void insertRow(String query, Connection connection) {
//        try (Statement st = connection.createStatement()) {
//            st.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



    public static void makeTransactionUpdate(String[] queries,
            Connection connection) {
        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(false);
            for (String query : queries) {
                st.executeUpdate(query);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
    }



}
