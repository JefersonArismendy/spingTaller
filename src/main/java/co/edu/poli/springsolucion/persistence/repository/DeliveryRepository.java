package co.edu.poli.springsolucion.persistence.repository;

import co.edu.poli.springsolucion.persistence.entity.Customer;
import co.edu.poli.springsolucion.persistence.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
