package model;

import db.DbConnection;

import dto.MarketPlaceDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarketPlacemodel {
    public boolean SaveMarket(MarketPlaceDto marketPlaceDto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO marketplace VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,marketPlaceDto.getMarketPlaceId());
        preparedStatement.setString(2, marketPlaceDto.getMarketName());
        preparedStatement.setString(3, marketPlaceDto.getAddress());
        preparedStatement.setString(4, String.valueOf(marketPlaceDto.getHotline()));
        preparedStatement.setString(5, marketPlaceDto.getEmai());




        boolean isSaved = preparedStatement.executeUpdate() > 0;
        return isSaved;
    }


public MarketPlaceDto searchMarket(String id) throws SQLException {
    Connection connection = DbConnection.getInstance().getConnection();
    String sql = "SELECT * FROM marketplace WHERE marketplaceId = ?";

    PreparedStatement pstm = connection.prepareStatement(sql);
    pstm.setString(1, id);

    ResultSet resultSet = pstm.executeQuery();

    MarketPlaceDto dto = null;

    if (resultSet.next()) {
        dto = new MarketPlaceDto(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getString(5)
        );
    }
    return dto;
}


    public List<MarketPlaceDto> getAllMarket() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM marketplace";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<MarketPlaceDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new MarketPlaceDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }
}
