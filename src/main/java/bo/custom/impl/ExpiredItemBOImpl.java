package bo.custom.impl;

import bo.custom.ExpiredItemBO;
import dao.DAOFactory;
import dao.custom.ExpiredItemDAO;

import dto.ExpiredItemDto;
import entity.ExpiredItem;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpiredItemBOImpl implements ExpiredItemBO {
    ExpiredItemDAO expiredItemDAO=(ExpiredItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXPIREDITEM);

    @Override
    public ArrayList<ExpiredItemDto> getAllExpiredItem() throws SQLException, ClassNotFoundException {
        ArrayList<ExpiredItem> expiredItems=expiredItemDAO.getAll();
        ArrayList<ExpiredItemDto> expiredItemDtos=new ArrayList<>();
        for (ExpiredItem expiredItem:expiredItems) {
            expiredItemDtos.add(new ExpiredItemDto(expiredItem.getExpiredID(),expiredItem.getInventoryId(),expiredItem.getItemName(),expiredItem.getQuantity(),expiredItem.getSupplierID(),expiredItem.getSupplierName(),expiredItem.getSupplierContact()));
        }
        return null;
    }

    @Override
    public boolean saveExpiredItem(ExpiredItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateExpiredItem(ExpiredItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existExpiredItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void deleteExpiredItem(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateExpiredItem() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ExpiredItemDto searchExpiredItem(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
