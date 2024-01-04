package dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierDto {
    private String supplierID;
    private String supplierName;
    private String invoiceName;
    private LocalDate date;
    private int supplierContact;


}
