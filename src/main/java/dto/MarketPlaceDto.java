package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketPlaceDto {
    private String marketPlaceId;
    private String marketName;
    private String address;
    private int hotline;
    private String emai;
}
