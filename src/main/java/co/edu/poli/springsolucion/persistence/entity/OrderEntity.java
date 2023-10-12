package co.edu.poli.springsolucion.persistence.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="Orders")
public class OrderEntity {


    @Id
    @GeneratedValue (strategy =GenerationType.AUTO)
    @Column(name = "Order_ID")
    private Long orderid;

    @Column(name = "Order_date")
    private LocalDate orderDate;


    //aqui se hace la relacion muchos a uno con customer
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer") //aqui se coloca el atributo que sera llamado por la otra entdiad
    private Customer customer; //aqui se coloca la entidad con la que se hace la relacion

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery")
    private Delivery delivery;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;
        return Objects.equals(getOrderid(), that.getOrderid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderid());
    }
}
