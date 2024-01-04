package model;

import db.DbConnection;
import dto.SupplierDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public boolean SaveSupplier(SupplierDto supplierDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO supplier VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,supplierDto.getSupplierID());
        preparedStatement.setString(2,supplierDto.getSupplierName());
        preparedStatement.setString(3,supplierDto.getInvoiceName());
        preparedStatement.setString(4, String.valueOf(supplierDto.getDate()));
        preparedStatement.setString(5, String.valueOf(supplierDto.getSupplierContact()));

        boolean isSaved=preparedStatement.executeUpdate()>0;
        return isSaved;
    }

    public List<SupplierDto> getAllSupplier() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SupplierDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SupplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4).toLocalDate(),
                            resultSet.getInt(5)
                    )
            );
        }
        return dtoList;
    }

    public SupplierDto searchItem(String ItemID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE ItemID = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, ItemID);

        ResultSet resultSet = pstm.executeQuery();

        SupplierDto dto = null;

        if(resultSet.next()) {
            dto = new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getInt(6)
            );
        }
        return dto;
    }


}
