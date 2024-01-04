package model;

import db.DbConnection;
import dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Itemmodel {

    public boolean SaveItems(ItemDto itemDto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO item VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,itemDto.getItemID());
        preparedStatement.setString(2,itemDto.getSupplierID());
        preparedStatement.setString(3,itemDto.getName());
        preparedStatement.setDouble(4,itemDto.getPrice());
        preparedStatement.setString(5,itemDto.getCategory());
        preparedStatement.setString(6, String.valueOf(itemDto.getDate()));
        preparedStatement.setString(7,itemDto.getDescription());
        preparedStatement.setInt(8,itemDto.getQty());


        boolean isSaved = preparedStatement.executeUpdate() > 0;
        return isSaved;
    }

    public static List<ItemDto> getAllItem() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ItemDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new ItemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getString(5),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7),
                            resultSet.getInt(8)


                    )
            );
        }
        return dtoList;
    }
    public ItemDto searchItem(String ItemID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE ItemID = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, ItemID);

        ResultSet resultSet = pstm.executeQuery();

        ItemDto dto = null;

        if(resultSet.next()) {
            dto = new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5),
                    resultSet.getDate(6).toLocalDate(),
                    resultSet.getString(7),
                    resultSet.getInt(8)
            );
        }
        return dto;
    }
    public static boolean updateQty(String itemID, int qty) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET qty = qty - ? WHERE itemID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, itemID);

        return pstm.executeUpdate() > 0;
    }
    void UpdateItems(){
        Connection connection= DbConnection.getInstance().getConnection();
    }


}
