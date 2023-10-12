package co.edu.poli.springsolucion.persistence.repository;

import co.edu.poli.springsolucion.persistence.entity.Delivery;
import co.edu.poli.springsolucion.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<OrderEntity, Long> {


}
