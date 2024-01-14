package dao.custom.impl;

import dao.SQLUtill;
import dao.custom.ExpiredItemDAO;
import entity.ExpiredItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpiredItemDAOImpl implements ExpiredItemDAO {
    @Override
    public ArrayList<ExpiredItem> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM inventory");
        ArrayList<ExpiredItem> allExpiredItem = new ArrayList<>();

        while (rst.next()) {
            ExpiredItem entity = new ExpiredItem(
                    rst.getString("expiredID"),
                    rst.getString("inventoryId"),
                    rst.getString("itemName"),
                    rst.getInt("quantity"),
                    rst.getString("supplierID"),
                    rst.getString("supplierName"),
                    rst.getInt("supplierContact")
            );
            allExpiredItem.add(entity);
        }
        return allExpiredItem;
    }

    @Override
    public boolean save(ExpiredItem dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ExpiredItem dto) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public ExpiredItem search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
