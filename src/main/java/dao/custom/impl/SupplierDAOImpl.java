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
        ArrayList<Supplier> allSupplier = new ArrayList<>();

        while (rst.next()) {
            Supplier entity = new Supplier(
                    rst.getString("supplierID"),
                    rst.getString("SupplierName"),
                    rst.getString("invoiceNumber"),
                    rst.getDate("date").toLocalDate(),
                    rst.getInt("supplierContact")
                    );
            allSupplier.add(entity);
        }
        return allSupplier;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO supplier (supplierID,supplierName, invoiceNumber,date,supplierContact) VALUES (?,?,?,?,?)",
                entity.getSupplierID(),entity.getSupplierName(),entity.getInvoiceName(),entity.getDate(),entity.getSupplierContact());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE supplier SET supplierName=?, invoiceNumber=?, date=?, supplierContact=? , WHERE id=?",
                entity.getSupplierID(),entity.getSupplierName(),entity.getInvoiceName(),entity.getDate(),entity.getSupplierContact());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtill.execute("SELECT id FROM supplier WHERE id=?",id);
        return rst.next();
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
