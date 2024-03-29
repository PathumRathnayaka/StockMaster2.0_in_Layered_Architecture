package bo.custom;

import bo.SuperBO;
import dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    boolean existSupplier(String id) throws SQLException, ClassNotFoundException;
    void deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    String generateSupplierID() throws SQLException, ClassNotFoundException;
    SupplierDto searchSupplier(String id) throws SQLException, ClassNotFoundException;
}
