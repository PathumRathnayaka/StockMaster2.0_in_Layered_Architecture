package bo.custom.impl;

import bo.custom.InventoryBO;
import dao.DAOFactory;
import dao.custom.InventoryDAO;
import dto.InventoryDto;
import entity.Inventory;
//import entity.Item;


import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {
    InventoryDAO inventoryDAO=(InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);
    @Override
    public ArrayList<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> inventories=inventoryDAO.getAll();
        ArrayList<InventoryDto> itemDtos=new ArrayList<>();
        for (Inventory inventory:inventories) {
            itemDtos.add(new InventoryDto(inventory.getInventoryId(),inventory.getItemID(),inventory.getGodownID(),inventory.getTrackID(),inventory.getInvoiceNumber(),inventory.getQuantity(),inventory.getDate()));
        }
        return null;
    }

    @Override
    public boolean saveInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(dto.getInventoryId(),dto.getItemID(),dto.getGodownID(),dto.getTrackID(),dto.getInvoiceNumber(),dto.getQuantity(),dto.getDate()));
    }

    @Override
    public boolean updateInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(dto.getInventoryId(),dto.getItemID(),dto.getGodownID(),dto.getTrackID(),dto.getInvoiceNumber(),dto.getQuantity(),dto.getDate()));
    }

    @Override
    public boolean existInventory(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.exist(id);
    }

    @Override
    public void deleteInventory(String id) throws SQLException, ClassNotFoundException {
        inventoryDAO.delete(id);
    }

    @Override
    public String generateInventory() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public InventoryDto searchInventory(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
