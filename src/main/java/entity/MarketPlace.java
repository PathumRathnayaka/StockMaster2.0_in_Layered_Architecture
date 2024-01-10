package entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketPlace {
    private String marketPlaceId;
    private String marketName;
    private String address;
    private int hotline;
    private String email;
}
