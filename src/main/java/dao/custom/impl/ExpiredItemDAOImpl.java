package dao.custom.impl;

import dao.custom.ExpiredItemDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpiredItemDAOImpl implements ExpiredItemDAO {
    @Override
    public ArrayList<ExpiredItemDAO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ExpiredItemDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ExpiredItemDAO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public ExpiredItemDAO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
