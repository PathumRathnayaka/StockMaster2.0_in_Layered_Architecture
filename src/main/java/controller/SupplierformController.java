package controller;


import bo.custom.SupplierBO;
import bo.custom.impl.SupplierBOImpl;
import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import dto.SupplierDto;
import dto.tm.SupplierTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.SupplierModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Regex;
import util.TextFields;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.parse;

public class SupplierformController {


    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> clmnDate;

    @FXML
    private TableColumn<?, ?> clmnID;

    @FXML
    private TableColumn<?, ?> clmnInvoiceNumber;

    @FXML
    private TableColumn<?, ?> clmnSupplierContact;

    @FXML
    private TableColumn<?, ?> clmnSupplierName;

    @FXML
    private TableView<SupplierTable> tblSupplier;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtInvoiceName;

    @FXML
    private TextField txtSupplierContact;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtSupplierName;

    private SupplierModel supplierModel=new SupplierModel();
    private SupplierDto supplierDto= new SupplierDto();

    SupplierBO supplierBO = new SupplierBOImpl();

    public void initialize(){
        setCellValueFactory();
        loadAllSupplier();
        tableListener();
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        /*String supplierID=txtSupplierID.getText();
        String supplierName=txtSupplierName.getText();
        String invoiceName=txtInvoiceName.getText();
        LocalDate date=txtDate.getValue();
        int supplierContact= Integer.parseInt(txtSupplierContact.getText());

        SupplierDto supplierDto=new SupplierDto(supplierID,supplierName,invoiceName,date,supplierContact);

        try {
            boolean isSaved=supplierModel.SaveSupplier(supplierDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Supplier is Saved !").show();
                ClearField();
                loadAllSupplier();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }*/
        String supplierID=txtSupplierID.getText();
        String supplierName=txtSupplierName.getText();
        String invoiceName=txtInvoiceName.getText();
        LocalDate date=txtDate.getValue();
        int supplierContact= Integer.parseInt(txtSupplierContact.getText());

        if (!supplierName.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtSupplierName.requestFocus();
            return;
        } else if (!invoiceName.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtInvoiceName.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            /*Save Customer*/
            try {
                if (existSupplier(supplierID)) {
                    new Alert(Alert.AlertType.ERROR, supplierID + " already exists").show();
                }
                boolean isSaved = supplierBO.saveSupplier(new SupplierDto(supplierID,supplierName, invoiceName, date, supplierContact));

                if (isSaved) {
                    tblSupplier.getItems().add(new SupplierTable(supplierID,supplierName, invoiceName, date, supplierContact));
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the supplier " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
    }
        } else {
            /*Update customer*/
            try {
                if (!existSupplier(supplierID)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such supplier associated with the id " + supplierID).show();
                }
                SupplierDto dto = new SupplierDto(supplierID,supplierName, invoiceName, date, supplierContact);
                supplierBO.updateSupplier(dto);

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + supplierID + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            SupplierTable selectedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
            selectedSupplier.setSupplierID(supplierID);
            selectedSupplier.setSupplierName(supplierName);
            selectedSupplier.setInvoiceNum(invoiceName);
            selectedSupplier.setDate(date);
            selectedSupplier.setSupplierContact(supplierContact);
            //selectedSupplier.refresh();
        }

        //btnAddNewCustomer.fire();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        /*Delete Customer*/
        String id = tblSupplier.getSelectionModel().getSelectedItem().getSupplierID();
        try {
            if (!existSupplier(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such supplier associated with the id " + id).show();
            }
            supplierBO.deleteSupplier(id);
            tblSupplier.getItems().remove(tblSupplier.getSelectionModel().getSelectedItem());
            tblSupplier.getSelectionModel().clearSelection();
            //initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the supplier " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
                return supplierBO.existSupplier(id);

            }

    /*private void loadAllSupplier() {
        var model = new SupplierModel();

        ObservableList<SupplierTable> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDto> dtoList = model.getAllSupplier();

            for (SupplierDto dto : dtoList) {
                obList.add(
                        new SupplierTable(
                                dto.getSupplierID(),
                                dto.getSupplierName(),
                                dto.getInvoiceName(),
                                dto.getDate(),
                                dto.getSupplierContact()
                        )
                );
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    private void loadAllSupplier() {
        tblSupplier.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<SupplierDto> allCustomer = supplierBO.getAllSupplier();
            for (SupplierDto dto:allCustomer) {
                tblSupplier.getItems().add(
                        new SupplierTable(
                                dto.getSupplierID(),
                                dto.getSupplierName(),
                                dto.getInvoiceName(),
                                dto.getDate(),
                                dto.getSupplierContact()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void setCellValueFactory() {
        clmnID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        clmnSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        clmnInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("InvoiceNum"));
        clmnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        clmnSupplierContact.setCellValueFactory(new PropertyValueFactory<>("SupplierContact"));
    }
    public void ClearField(){
        txtSupplierID.setText("");
        txtSupplierName.setText("");
        txtInvoiceName.setText("");
        txtDate.setAccessibleText("");
        txtSupplierContact.setText("");
    }
    private void tableListener() {
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }
    private void setData(SupplierTable row) {
        txtSupplierID.setText(row.getSupplierID());
        txtSupplierName.setText(row.getSupplierName());
        txtInvoiceName.setText(row.getInvoiceNum());
        txtSupplierContact.setText(String.valueOf(row.getSupplierContact()));
    }
    private void setFields(SupplierDto dto) {
        txtSupplierID.setText(dto.getSupplierID());
        txtSupplierName.setText(dto.getSupplierName());
        txtInvoiceName.setText(String.valueOf(dto.getInvoiceName()));
        txtDate.setAccessibleText(String.valueOf(dto.getDate()));
        txtSupplierContact.setText(String.valueOf(dto.getSupplierContact()));
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Supplier.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasprePrint = JasperFillManager.fillReport(jasperReport,
                null,
                DBConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasprePrint,false);
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtInvoiceNumberOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtSupplierContactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.PHONE,txtSupplierContact);
    }

    @FXML
    void txtSupplierIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.ID,txtSupplierID);
    }

    @FXML
    void txtSupplierNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(TextFields.NAME,txtSupplierName);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
