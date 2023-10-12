package co.edu.poli.springsolucion.services.DTO;

import co.edu.poli.springsolucion.persistence.entity.DeliveryCompany;
import co.edu.poli.springsolucion.persistence.entity.DeliveryType;
import lombok.Data;

@Data
public class DeliveryDTO {
    private DeliveryCompany company;
    private int duration;
    private DeliveryType type;
}
