package dao.custom.impl;

import dao.CrudDAO;
import dao.custom.SupplierDAO;
import entity.Supplier;
import dao.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM supplier");
        ArrayList<Supplier> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Supplier entity = new Supplier(
                    rst.getString("supplierID"),
                    rst.getString("SupplierName"),
                    rst.getString("invoiceNUmber"),
                    rst.getDate("date").toLocalDate(),
                    rst.getInt("supplierContact")
                    );
            allCustomer.add(entity);
        }
        return allCustomer;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

}
