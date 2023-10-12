package co.edu.poli.springsolucion.controller;


import co.edu.poli.springsolucion.persistence.entity.Customer;
import co.edu.poli.springsolucion.persistence.entity.OrderEntity;
import co.edu.poli.springsolucion.services.CustomerService;
import co.edu.poli.springsolucion.services.DTO.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerid}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerid) {
        Optional<Customer> customer = customerService.getCustomerById(customerid);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    @PostMapping()
    public Customer create(@RequestBody CustomerDTO customer){

        return customerService.create(customer);
    }


    // Update (PUT)
    @PutMapping("/{customerid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerid, @RequestBody CustomerDTO customer) {
        Customer updatedCustomer = customerService.update(customerid, customer);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete (DELETE)
    @DeleteMapping("/{customerid}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerid) {
        boolean deleted = customerService.deleteCustomer(customerid);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
