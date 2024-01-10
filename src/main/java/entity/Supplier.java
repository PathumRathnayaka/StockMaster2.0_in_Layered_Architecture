package entity;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String supplierID;
    private String supplierName;
    private String invoiceName;
    private LocalDate date;
    private int supplierContact;
}
