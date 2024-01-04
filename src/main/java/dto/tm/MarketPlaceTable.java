package dto.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketPlaceTable {
    private String marketPlaceId;
    private String marketName;
    private String address;
    private int hotline;
    private String emai;
}
