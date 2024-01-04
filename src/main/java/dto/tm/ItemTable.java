package dto.tm;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemTable {
    private String supplierID;
    private String itemID;
    private String name;
    private double price;
    private String category;
    private LocalDate date;
    private String description;
    private int qty;
}
