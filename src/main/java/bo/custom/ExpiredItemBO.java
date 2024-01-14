package bo.custom;



import bo.SuperBO;
import dto.ExpiredItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExpiredItemBO extends SuperBO {
    ArrayList<ExpiredItemDto> getAllExpiredItem() throws SQLException, ClassNotFoundException;
    boolean saveExpiredItem(ExpiredItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateExpiredItem(ExpiredItemDto dto) throws SQLException, ClassNotFoundException;
    boolean existExpiredItem(String id) throws SQLException, ClassNotFoundException;
    void deleteExpiredItem(String id) throws SQLException, ClassNotFoundException;
    String generateExpiredItem() throws SQLException, ClassNotFoundException;
    ExpiredItemDto searchExpiredItem(String id) throws SQLException, ClassNotFoundException;
}
