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
                    rst.getString("invoiceNumber"),
                    rst.getDate("date").toLocalDate(),
                    rst.getInt("supplierContact")
                    );
            allCustomer.add(entity);
        }
        return allCustomer;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO supplier (supplierID,suppierName, invoiceNumber,date,supplierContact) VALUES (?,?,?,?,?)",
                entity.getSupplierID(),entity.getSupplierName(),entity.getInvoiceName(),entity.getDate(),entity.getSupplierContact());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE supplier SET name=?, address=? WHERE id=?",
                entity.getSupplierID(),entity.getSupplierName(),entity.getInvoiceName(),entity.getDate(),entity.getSupplierContact());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtill.execute("DELETE FROM supplier WHERE id=?",id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM supplier WHERE id=?",id);
        rst.next();
        return new Supplier(id + "", rst.getString("supplierName"), rst.getString("invoiceNumber"), rst.getDate("date").toLocalDate(),rst.getInt("supplierContact"));
    }

}
