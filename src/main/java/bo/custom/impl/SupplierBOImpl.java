package bo.custom.impl;

import bo.custom.SupplierBO;
import dao.DAOFactory;
import dao.SQLUtill;
import dao.custom.SupplierDAO;
import dto.SupplierDto;
import entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO=(SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers=supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos=new ArrayList<>();
        for (Supplier supplier:suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSupplierID(),supplier.getSupplierName(),supplier.getInvoiceName(),supplier.getDate(),supplier.getSupplierContact()));
        }
        return supplierDtos;
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getSupplierID(),dto.getSupplierName(),dto.getInvoiceName(),dto.getDate(),dto.getSupplierContact()));
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupplierID(),dto.getSupplierName(),dto.getInvoiceName(),dto.getDate(),dto.getSupplierContact()));
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }

    @Override
    public void deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        SQLUtill.execute("DELETE FROM supplier WHERE id=?",id);
    }

    @Override
    public String generateSupplierID() throws SQLException, ClassNotFoundException {
        return null;
    }

   /* @Override
    public SupplierDto searchSupplier(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM supplier WHERE id=?",id);
        rst.next();
        return new Supplier(id + "", rst.getString("supplierName"), rst.getString("invoiceNumber"), rst.getDate("date"), rst.getInt("supplierContact"));
    }*/
   @Override
   public SupplierDto searchSupplier(String id) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtill.execute("SELECT * FROM supplier WHERE id=?",id);
       rst.next();
       // Convert java.sql.Date to java.time.LocalDate
       java.sql.Date sqlDate = rst.getDate("date");
       LocalDate localDate = sqlDate.toLocalDate();

       return new SupplierDto(
               id + "",
               rst.getString("supplierName"),
               rst.getString("invoiceNumber"),
               localDate,  // Use the converted LocalDate
               rst.getInt("supplierContact")
       );
   }
}
