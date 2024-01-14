package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredItemDto {
    private String expiredID;
    private String inventoryId;
    private String itemName;
    private int quantity;
    private String supplierID;
    private String supplierName;
    private int supplierContact;
}
