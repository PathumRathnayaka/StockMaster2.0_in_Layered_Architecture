package controller;


import com.jfoenix.controls.JFXButton;
import db.DbConnection;
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
        String supplierID=txtSupplierID.getText();
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
        }
    }
    private void loadAllSupplier() {
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
                DbConnection.getInstance().getConnection()
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
