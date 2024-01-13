package bo.custom;

import bo.SuperBO;
import dto.MarketPlaceDto;


import java.sql.SQLException;
import java.util.ArrayList;

public interface MarketPlaceBO extends SuperBO {
    ArrayList<MarketPlaceDto> getAllMarketPlace() throws SQLException, ClassNotFoundException;
    boolean saveMarketPlace(MarketPlaceDto dto) throws SQLException, ClassNotFoundException;
    boolean updateMarketPlace(MarketPlaceDto dto) throws SQLException, ClassNotFoundException;
    boolean existMarketPlace(String id) throws SQLException, ClassNotFoundException;
    void deleteMarketPlace(String id) throws SQLException, ClassNotFoundException;
    String generateMarketPlace() throws SQLException, ClassNotFoundException;
    MarketPlaceDto searchMarketPlace(String id) throws SQLException, ClassNotFoundException;
}
