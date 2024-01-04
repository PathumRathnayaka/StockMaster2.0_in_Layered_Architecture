package dto.tm;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryTable {
    private String inventoryId;
    private String itemID;
    private String godownID;
    private String trackID;
    private String invoiceNumber;
    private int quantity;
    private LocalDate date;
}
