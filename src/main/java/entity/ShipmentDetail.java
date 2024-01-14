package entity;

import dto.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDetail {
    private String shipmentID;
    private String itemID;
    private int qty;
    private double unitPtice;
}
