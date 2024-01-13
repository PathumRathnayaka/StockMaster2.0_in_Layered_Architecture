package bo.custom.impl;

import bo.custom.MarketPlaceBO;
import dao.DAOFactory;
import dao.custom.MarketPlaceDAO;
import dto.MarketPlaceDto;
import entity.Item;
import entity.MarketPlace;

import java.sql.SQLException;
import java.util.ArrayList;

public class MarketPlaceBOImpl implements MarketPlaceBO {
    MarketPlaceDAO marketPlaceDAO=(MarketPlaceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MARKETPLACE);
    @Override
    public ArrayList<MarketPlaceDto> getAllMarketPlace() throws SQLException, ClassNotFoundException {
        ArrayList<MarketPlace> marketPlaces=marketPlaceDAO.getAll();
        ArrayList<MarketPlaceDto> marketPlaceDtos=new ArrayList<>();
        for (MarketPlace marketPlace:marketPlaces) {
            marketPlaceDtos.add(new MarketPlaceDto(marketPlace.getMarketPlaceId(),marketPlace.getMarketName(),marketPlace.getAddress(),marketPlace.getHotline(),marketPlace.getEmail()));
        }
        return null;
    }

    @Override
    public boolean saveMarketPlace(MarketPlaceDto dto) throws SQLException, ClassNotFoundException {
        return marketPlaceDAO.save(new MarketPlace(dto.getMarketPlaceId(),dto.getMarketName(),dto.getAddress(),dto.getHotline(),dto.getEmail()));
    }

    @Override
    public boolean updateMarketPlace(MarketPlaceDto dto) throws SQLException, ClassNotFoundException {
        return marketPlaceDAO.update(new MarketPlace(dto.getMarketPlaceId(),dto.getMarketName(),dto.getAddress(),dto.getHotline(),dto.getEmail()));
    }

    @Override
    public boolean existMarketPlace(String id) throws SQLException, ClassNotFoundException {
        return marketPlaceDAO.exist(id);
    }

    @Override
    public void deleteMarketPlace(String id) throws SQLException, ClassNotFoundException {
        marketPlaceDAO.delete(id);
    }

    @Override
    public String generateMarketPlace() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public MarketPlaceDto searchMarketPlace(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
