package co.edu.poli.springsolucion.services;


import co.edu.poli.springsolucion.persistence.entity.Customer;
import co.edu.poli.springsolucion.persistence.repository.CustomerRepository;
import co.edu.poli.springsolucion.services.DTO.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements  CustomerService {

    private  final CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerid) {
        return customerRepository.findById(customerid);
    }



    @Override
    public Customer create(CustomerDTO customerDTO) {

        // Crear un objeto Customer a partir de CustomerDTO
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setBirthdate(customerDTO.getBirthdate());

        Date currentDate = new Date();
        Date birthdate = customer.getBirthdate();
        long ageInMillis = currentDate.getTime() - birthdate.getTime();
        long ageInYears = TimeUnit.MILLISECONDS.toDays(ageInMillis) / 365;

        if (ageInYears  >= 18) {
            return customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("El cliente debe tener al menos 18 años para ser registrado.");
        }


    }


    @Override
    // Update (Método para actualizar un cliente existente)
    public Customer update(Long customerid, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(customerid).orElse(null);
        if (existingCustomer != null) {
            // Actualizar los campos del cliente existente con los valores del cliente actualizado
            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setBirthdate(customerDTO.getBirthdate());

            // Guardar el cliente actualizado en la base de datos
            return customerRepository.save(existingCustomer);
        }
        return null; // Devolver null si el cliente no existe
    }


    @Override
    // Delete (Método para eliminar un cliente por su ID)
    public boolean deleteCustomer(Long customerid) {
        Customer customerToDelete = customerRepository.findById(customerid).orElse(null);
        if (customerToDelete != null) {
            customerRepository.delete(customerToDelete);
            return true; // Devolver true si se eliminó con éxito
        }
        return false; // Devolver false si el cliente no existe
    }


}
