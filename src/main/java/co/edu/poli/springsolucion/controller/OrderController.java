package co.edu.poli.springsolucion.controller;



import co.edu.poli.springsolucion.persistence.entity.OrderEntity;
import co.edu.poli.springsolucion.services.DTO.DeliveryDTO;
import co.edu.poli.springsolucion.services.DTO.OrderDTO;
import co.edu.poli.springsolucion.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public List<OrderEntity> findAll(){
        return orderService.findAll();
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long orderId) {
        Optional<OrderEntity> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public OrderEntity create(@RequestBody OrderDTO order){
        return orderService.create(order);
    }


    // Update (PUT)
    @PutMapping("/{orderid}")
    public ResponseEntity<OrderEntity> update(@PathVariable Long orderid, @RequestBody OrderDTO order) {
        OrderEntity editedOrder = orderService.edit(orderid, order);
        if (editedOrder != null) {
            return new ResponseEntity<>(editedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Delete (DELETE)
    @DeleteMapping("/{orderid}")
    public ResponseEntity<Void> delete(@PathVariable Long orderid) {
        boolean deleted = orderService.delete(orderid);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
