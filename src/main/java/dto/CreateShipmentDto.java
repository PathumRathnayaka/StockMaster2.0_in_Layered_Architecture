package dto;

import dto.tm.CartTm;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateShipmentDto {
    private String shipmentID;
    private LocalDate date;
    private String marketplaceId;
    private List<CartTm> cartTmList = new ArrayList<>();
}
