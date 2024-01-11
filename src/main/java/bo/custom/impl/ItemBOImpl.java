package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.SQLUtill;
import dao.custom.ItemDAO;
import dto.ItemDto;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO=(ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items=itemDAO.getAll();
        ArrayList<ItemDto> itemDtos=new ArrayList<>();
        for (Item item:items) {
            itemDtos.add(new ItemDto(item.getItemID(),item.getSupplierID(),item.getName(),item.getPrice(),item.getCategory(),item.getDate(),item.getDescription(),item.getQty()));
        }
        return null;
    }

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemID(),dto.getSupplierID(),dto.getName(),dto.getPrice(),dto.getCategory(),dto.getDate(),dto.getDescription(),dto.getQty()));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItemID(),dto.getSupplierID(),dto.getName(),dto.getPrice(),dto.getCategory(),dto.getDate(),dto.getDescription(),dto.getQty()));
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public void deleteItem(String id) throws SQLException, ClassNotFoundException {
        itemDAO.delete(id);
    }

    @Override
    public String generateItem() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ItemDto searchItem(String id) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = SQLUtill.execute("SELECT * FROM supplier WHERE id=?",id);
        rst.next();
        // Convert java.sql.Date to java.time.LocalDate
        java.sql.Date sqlDate = rst.getDate("date");
        LocalDate localDate = sqlDate.toLocalDate();
        return new Item(id + "", rst.getString("supplierID"), rst.getString("itemName"), rst.getDouble("price"), rst.getString("category") , localDate, rst.getString("description"), rst.getInt("qty"));*/
    return null;
    }
}
