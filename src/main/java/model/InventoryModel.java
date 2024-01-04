package model;

import db.DbConnection;
import dto.InventoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    public boolean saveInventory(InventoryDto inventoryDto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO inventory VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,inventoryDto.getInventoryId());
        preparedStatement.setString(2,inventoryDto.getItemID());
        preparedStatement.setString(3,inventoryDto.getGodownID());
        preparedStatement.setString(4,inventoryDto.getTrackID());
        preparedStatement.setString(5,inventoryDto.getInvoiceNumber());
        preparedStatement.setString(6, String.valueOf(inventoryDto.getQuantity()));
        preparedStatement.setString(7, String.valueOf(inventoryDto.getDate()));

        boolean isSaved = preparedStatement.executeUpdate() > 0;
        return isSaved;
    }

    public List<InventoryDto> getAllItem() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM inventory";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<InventoryDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new InventoryDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            resultSet.getDate(7).toLocalDate()
                    )
            );
        }
        return dtoList;
    }
}
