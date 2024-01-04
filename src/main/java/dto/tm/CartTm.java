package dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.jfoenix.controls.JFXButton;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartTm {
    private String itemID;
    private String itemName;
    private String marketName;
    private double unitPrice;
    private int qty;
    private double tot;
    private Button btn;
}
