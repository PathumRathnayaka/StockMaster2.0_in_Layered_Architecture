package entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpiredItem {
    private String expiredID;
    private String inventoryId;
    private String itemName;
    private int quantity;
    private String supplierID;
    private String supplierName;
    private int supplierContact;
}
