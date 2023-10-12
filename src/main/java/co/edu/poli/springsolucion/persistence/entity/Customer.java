package co.edu.poli.springsolucion.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Customer_ID")
    private Long customerid;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "Birthdate")
    private Date Birthdate;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)// esto permite hacer la relacion
    @JsonIgnore // Evita la serialización de la relación orders
    private List<OrderEntity> order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(customerid, customer.customerid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerid);
    }
}
