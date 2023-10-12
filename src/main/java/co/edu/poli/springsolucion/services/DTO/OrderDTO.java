package co.edu.poli.springsolucion.services.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {
    private LocalDate orderDate;
    private Long customerId;
    private Long deliveryId;
}
