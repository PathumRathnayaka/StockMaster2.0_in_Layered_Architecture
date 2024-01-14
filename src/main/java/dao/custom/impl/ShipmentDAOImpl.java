package dao.custom.impl;

import dao.SQLUtill;
import dao.custom.ShipmentDAO;
import entity.Shipment;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShipmentDAOImpl implements ShipmentDAO {
    @Override
    public ArrayList<Shipment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Shipment entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO `shipment` (shipmentID, marketplaceId, date) VALUES (?,?,?)",entity.getShipmentID(),entity.getMarketplaceId(), Date.valueOf(entity.getDate()));
    }

    @Override
    public boolean update(Shipment dto) throws SQLException, ClassNotFoundException {
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
    public Shipment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
