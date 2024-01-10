package entity;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    private String inventoryId;
    private String itemID;
    private String godownID;
    private String trackID;
    private String invoiceNumber;
    private int quantity;
    private LocalDate date;
}
