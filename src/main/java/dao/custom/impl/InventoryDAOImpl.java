package dao.custom.impl;

import dao.SQLUtill;
import dao.custom.InventoryDAO;
import entity.Inventory;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl implements InventoryDAO {
    @Override
    public ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM inventory");
        ArrayList<Inventory> allInventory = new ArrayList<>();

        while (rst.next()) {
            Inventory entity = new Inventory(
                    rst.getString("invetoryId"),
                    rst.getString("itemID"),
                    rst.getString("godownID"),
                    rst.getString("trackID"),
                    rst.getString("invoiceNumber"),
                    rst.getInt("quantity"),
                    rst.getDate("date").toLocalDate()
            );
            allInventory.add(entity);
        }
        return allInventory;
    }

    @Override
    public boolean save(Inventory entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO inventory (invetoryId, itemID, godownID, trackID, invoiceNumber, quantity, date) VALUES (?,?,?,?,?,?,?)",
                entity.getInventoryId(),entity.getItemID(),entity.getGodownID(),entity.getTrackID(),entity.getInvoiceNumber(),entity.getQuantity(),entity.getDate());
    }

    @Override
    public boolean update(Inventory entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE inventory SET invetoryId=?, itemID=? , godownID=?, trackID=?, invoiceNumber=? , quantity=?, date=? WHERE id=?",
                entity.getInventoryId(),entity.getItemID(),entity.getGodownID(),entity.getTrackID(),entity.getInvoiceNumber(),entity.getQuantity(),entity.getDate());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtill.execute("DELETE FROM inventory WHERE id=?",id);
    }

    @Override
    public Inventory search(String inventoryId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM inventory WHERE id=?",inventoryId);
        rst.next();
        return new Inventory(inventoryId + "", rst.getString("itemID"), rst.getString("godownID"), rst.getString("trackID"), rst.getString("invoiceNumber"), rst.getInt("quantity"), rst.getDate("date").toLocalDate());
    }
}
