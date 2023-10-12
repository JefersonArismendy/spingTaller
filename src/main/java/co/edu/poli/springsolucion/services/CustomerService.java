package co.edu.poli.springsolucion.services;

import co.edu.poli.springsolucion.persistence.entity.Customer;
import co.edu.poli.springsolucion.persistence.entity.OrderEntity;
import co.edu.poli.springsolucion.services.DTO.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {


    List<Customer> getAllCustomer();

    Optional<Customer> getCustomerById(Long customerid);

    Customer create(CustomerDTO customerDTO);

    // Update (Método para actualizar un cliente existente)
    Customer update(Long customerid, CustomerDTO customerDTO);

    // Delete (Método para eliminar un cliente por su ID)
    boolean deleteCustomer(Long customerid);
}
