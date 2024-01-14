package dao.custom.impl;

import dao.SQLUtill;
import dao.custom.ShipmentDetailDAO;
import entity.ShipmentDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShipmentDetailDAOImpl implements ShipmentDetailDAO {

    @Override
    public ArrayList<ShipmentDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ShipmentDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", entity.getShipmentID(), entity.getItemID(), entity.getQty(), entity.getUnitPtice());
    }

    @Override
    public boolean update(ShipmentDetail dto) throws SQLException, ClassNotFoundException {
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
    public ShipmentDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
