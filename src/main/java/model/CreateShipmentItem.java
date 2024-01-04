package model;

import db.DbConnection;
import dto.CreateShipmentDto;
import dto.tm.CartTm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CreateShipmentItem {
    public static boolean placeShipment(CreateShipmentDto dto) throws SQLException {
        Connection con = null;
        try{

            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            // set auto commit = false
            //1. order table
            //2. item table
            //3. order_details table
            // commit
            // else
            // rollback
            // finally
            // set auto commit = true

            boolean isShipmentSaved = Shipment.saveShipment(dto.getShipmentID(), dto.getMarketplaceId(), dto.getDate());
            if (isShipmentSaved){
                boolean isItemUpdated = updateItem(dto.getCartTmList());
                if (isItemUpdated){
                    boolean isOrderDetailsSaved = Shipmentdetail.saveShipmentDetails(dto.getShipmentID(), dto.getCartTmList());
                    if (isOrderDetailsSaved){
                        // all 3 queries must be success
                        con.commit();
                        return true;
                    }else {
                        con.rollback();
                    }
                }else {
                    con.rollback();
                }
            }else {
                con.rollback();
            }

        } catch (SQLException | ClassNotFoundException e) {
            if (con != null) con.rollback();
            e.printStackTrace();
        }finally {
            if (con != null) con.setAutoCommit(true);
        }

        return false;
    }

    private static boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for (CartTm tm : cartTmList) {
            boolean isUpdateItem = Itemmodel.updateQty(tm.getItemID(), tm.getQty());
            if (!isUpdateItem){
                return false;
            }
        }
        return true;
    }
}
