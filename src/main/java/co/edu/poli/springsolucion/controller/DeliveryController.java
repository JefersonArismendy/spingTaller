package co.edu.poli.springsolucion.controller;


import co.edu.poli.springsolucion.persistence.entity.Customer;
import co.edu.poli.springsolucion.persistence.entity.Delivery;
import co.edu.poli.springsolucion.services.CustomerService;
import co.edu.poli.springsolucion.services.DTO.DeliveryDTO;
import co.edu.poli.springsolucion.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {


    private final DeliveryService deliveryService;



    @GetMapping
    public List<Delivery> findAll(){
        return deliveryService.findAll();
    }



    @GetMapping("/{deliveryid}")
    public ResponseEntity<Delivery> getCustomerById(@PathVariable Long deliveryid) {
        Optional<Delivery> delivery = deliveryService.getDeliveryById(deliveryid);
        if (delivery.isPresent()) {
            return new ResponseEntity<>(delivery.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping()
    public Delivery create(@RequestBody DeliveryDTO delivery){
        return deliveryService.create(delivery);
    }




    // Update (PUT)
    @PutMapping("/{deliveryid}")
    public ResponseEntity<Delivery> update(@PathVariable Long deliveryid, @RequestBody DeliveryDTO delivery) {
        Delivery updatedDelivery = deliveryService.update(deliveryid, delivery);
        if (updatedDelivery != null) {
            return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete (DELETE)
    @DeleteMapping("/{deliveryid}")
    public ResponseEntity<Void> delete(@PathVariable Long deliveryid) {
        boolean deleted = deliveryService.delete(deliveryid);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
