package co.edu.poli.springsolucion.persistence.repository;

import co.edu.poli.springsolucion.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {



}
