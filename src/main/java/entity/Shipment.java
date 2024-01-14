package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    private String shipmentID;
    private String marketplaceId;
    private LocalDate date;
}
