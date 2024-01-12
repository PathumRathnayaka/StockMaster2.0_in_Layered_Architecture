package bo.custom;



import bo.SuperBO;
import dto.InventoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {
    ArrayList<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException;
    boolean saveInventory(InventoryDto dto) throws SQLException, ClassNotFoundException;
    boolean updateInventory(InventoryDto dto) throws SQLException, ClassNotFoundException;
    boolean existInventory(String id) throws SQLException, ClassNotFoundException;
    void deleteInventory(String id) throws SQLException, ClassNotFoundException;
    String generateInventory() throws SQLException, ClassNotFoundException;
    InventoryDto searchInventory(String id) throws SQLException, ClassNotFoundException;
}
