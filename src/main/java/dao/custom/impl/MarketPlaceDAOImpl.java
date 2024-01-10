package dao.custom.impl;

import dao.SQLUtill;
import dao.custom.MarketPlaceDAO;
import entity.MarketPlace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarketPlaceDAOImpl implements MarketPlaceDAO {
    @Override
    public ArrayList<MarketPlace> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM marketplace");
        ArrayList<MarketPlace> allMarketPlace = new ArrayList<>();

        while (rst.next()) {
            MarketPlace entity = new MarketPlace(
                    rst.getString("marketplaceId"),
                    rst.getString("marketName"),
                    rst.getString("address"),
                    rst.getInt("hotline"),
                    rst.getString("email")
            );
            allMarketPlace.add(entity);
        }
        return null;
    }

    @Override
    public boolean save(MarketPlace entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO marketplace (marketplaceId, marketName, address, hotline, email) VALUES (?,?,?,?,?)",
                entity.getMarketPlaceId(),entity.getMarketName(),entity.getAddress(),entity.getHotline(),entity.getEmail());
    }

    @Override
    public boolean update(MarketPlace entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE marketplace SET marketplaceId=?, marketName=? , address=?, hotline=?, email=?  WHERE id=?",
                entity.getMarketPlaceId(),entity.getMarketName(),entity.getAddress(),entity.getHotline(),entity.getEmail());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtill.execute("DELETE FROM marketplace WHERE id=?",id);
    }

    @Override
    public MarketPlace search(String marketplaceId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM marketplace WHERE id=?",marketplaceId);
        rst.next();
        return new MarketPlace(marketplaceId + "", rst.getString("marketName"), rst.getString("address"), rst.getInt("hotline"), rst.getString("email"));
    }
}
