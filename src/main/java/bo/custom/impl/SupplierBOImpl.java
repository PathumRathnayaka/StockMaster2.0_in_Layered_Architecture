package bo.custom.impl;

import bo.custom.SupplierBO;
import dao.DAOFactory;
import dao.SupperDAO;
import dao.custom.SupplierDAO;
import dto.SupplierDto;
import entity.Supplier;

import java.sql.SQLException;
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
        return null;
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void deleteSupplier(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateSupplierID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupplierDto searchSupplier(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
