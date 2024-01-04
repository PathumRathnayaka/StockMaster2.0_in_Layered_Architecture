package dto.tm;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierTable {
    private String SupplierID;
    private String SupplierName;
    private String InvoiceNum;
    private LocalDate Date;
    private int SupplierContact;
}
