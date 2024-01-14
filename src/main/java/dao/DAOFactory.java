package dao;

import dao.custom.impl.ExpiredItemDAOImpl;
import dao.custom.impl.InventoryDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.SupplierDAOImpl;

public class    DAOFactory {
    private static DAOFactory daoFactory;
    public DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return(daoFactory==null)? daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        SUPPLIER,ITEM,MARKETPLACE,INVENTORY,EXPIREDITEM
    }
    public SupperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ITEM:
                return new ItemDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case MARKETPLACE:
                return new InventoryDAOImpl();
            case EXPIREDITEM:
                return new ExpiredItemDAOImpl();
            default:
                return null;
            }
        }

}
