package dao.custom.impl;

import dao.CrudDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements CrudDAO {
    @Override
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(T dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(T dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
