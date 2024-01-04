package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ShelfLifeCalculatorformContoller {
    @FXML
    private DatePicker datePickerExpirationdate;

    @FXML
    private DatePicker datePickerStartdate;

    @FXML
    private DatePicker datePickerTodaydate;

    @FXML
    private Label lblDateAt75;

    @FXML
    private Label lblDaysLeft;

    @FXML
    private Label lblPercentLeft;

    @FXML
    void btnCalculateOnAction(ActionEvent event) {
        LocalDate startDate = datePickerStartdate.getValue();
        LocalDate todayDate = datePickerTodaydate.getValue();
        LocalDate expirationDate = datePickerExpirationdate.getValue();

        if (startDate != null && todayDate != null && expirationDate != null) {
            long totalDays = ChronoUnit.DAYS.between(startDate, expirationDate);
            long daysPassed = ChronoUnit.DAYS.between(startDate, todayDate);

            // Calculate percentage left
            double percentLeft = ((double) (totalDays - daysPassed) / totalDays) * 100;

            // Calculate date at 75%
            long daysAt75Percent = (long) (totalDays * 0.75);
            LocalDate dateAt75Percent = startDate.plusDays(daysAt75Percent);

            // Update labels
            lblPercentLeft.setText(" " + String.format("%.2f", percentLeft) + "%");
            lblDaysLeft.setText(" " + (totalDays - daysPassed));
            lblDateAt75.setText(" " + dateAt75Percent.toString());
        } else {
            // Handle case where not all dates are selected
            lblPercentLeft.setText("Please select all dates");
            lblDaysLeft.setText("");
            lblDateAt75.setText("");
        }

    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        ClearField();
    }
    private void ClearField(){
        lblPercentLeft.setText("00%");
        lblDaysLeft.setText("00");
        lblDateAt75.setText("");
    }
}
