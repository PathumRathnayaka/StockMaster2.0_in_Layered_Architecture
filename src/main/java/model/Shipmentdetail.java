package model;

import db.DbConnection;
import dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Shipmentdetail {

    public static boolean saveOrderDetails(String shipmentID, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(CartTm tm : cartTmList) {
            if(!saveShipmentDetails(shipmentID, tm)) {
                return false;
            }
        }
        return true;
    }
    private static boolean saveShipmentDetails(String shipmentID, CartTm tm) throws SQLException, SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO shipmentdetail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, shipmentID);
        pstm.setString(2, tm.getItemID());
        pstm.setInt(3, tm.getQty());
        pstm.setDouble(4, tm.getUnitPrice());

        return pstm.executeUpdate() > 0;
    }
    public static boolean saveShipmentDetails(String shipmentId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(CartTm tm : cartTmList) {
            if(!saveOrderDetails(shipmentId,  cartTmList)) {
                return false;
            }
        }
        return true;
    }
}
