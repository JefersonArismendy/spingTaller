package co.edu.poli.springsolucion.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="Delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Delivery_ID")
    private Long deliveryid;



    //@Pattern(regexp = "^(RAPPY|EATS|DIDY|UBER)$", message = "El valor del campo 'company' no es válido.")
    @Enumerated(EnumType.STRING) // Indica que el campo "company" es un enum
    @Column(name = "company")
    private DeliveryCompany company;


    @Min(value = 1, message = "La duración debe ser de al menos 1 minuto.")
    @Max(value = 60, message = "La duración no puede ser superior a 60 minutos.")
    @Column(name = "duration")
    private int duration;

    @Enumerated(EnumType.STRING) // Indica que el campo "type" es un enum
    @Column(name = "type")
    private DeliveryType type= DeliveryType.ORDER;


    @OneToMany(mappedBy = "delivery", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore // Evita la serialización de la relación orders
    private List<OrderEntity> order;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery delivery)) return false;
        return Objects.equals(getDeliveryid(), delivery.getDeliveryid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeliveryid());
    }
}
