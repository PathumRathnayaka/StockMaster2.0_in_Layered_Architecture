package dao.custom.impl;

import dao.SQLUtill;
import dao.custom.ItemDAO;
import entity.Item;
import entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM supplier");
        ArrayList<Item> allItem = new ArrayList<>();

        while (rst.next()) {
            Item entity = new Item(
                    rst.getString("itemID"),
                    rst.getString("supplierID"),
                    rst.getString("itemName"),
                    rst.getDouble("price"),
                    rst.getString("category"),
                    rst.getDate("expiryDate").toLocalDate(),
                    rst.getString("description"),
                    rst.getInt("qty")
            );
            allItem.add(entity);
        }
        return allItem;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO item (itemID,supplierID, ItemName,price,category,expiryDate,qty) VALUES (?,?,?,?,?,?,?,?)",
                entity.getItemID(),entity.getSupplierID(),entity.getName(),entity.getPrice(),entity.getCategory(),entity.getDate(),entity.getDescription(),entity.getQty());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE item SET supplierID=?, itemName=? , price=?, category=?, expiryDate=?, description=?, qty=?  WHERE id=?",
                entity.getItemID(),entity.getSupplierID(),entity.getName(),entity.getPrice(),entity.getCategory(),entity.getDate(),entity.getDescription(),entity.getQty());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtill.execute("DELETE FROM item WHERE id=?",id);
    }

    @Override
    public Item search(String itemID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM item WHERE id=?",itemID);
        rst.next();
        return new Item(itemID + "", rst.getString("supplierID"), rst.getString("itemName"), rst.getDouble("price"), rst.getString("category"), rst.getDate("date").toLocalDate(),rst.getString("description"),rst.getInt("qty"));
    }
}
