package model;

import db.DbConnection;

import java.sql.*;
import java.time.LocalDate;

public class Shipment {

    public static String generateNextShipmentId() throws SQLException, SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT shipmentID FROM shipment ORDER BY shipmentID DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitShipmentId(resultSet.getString(1));
        }
        return splitShipmentId(null);
    }

    private static String splitShipmentId(String currentShipmentId) {
        if(currentShipmentId != null) {
            String[] split = currentShipmentId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }
    public static boolean saveShipment(String shipmentID, String marketId, LocalDate date) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO shipment VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, shipmentID);
        pstm.setString(2, marketId);
        pstm.setDate(3, Date.valueOf(date));

        return pstm.executeUpdate() > 0;
    }
}
