package co.edu.poli.springsolucion.services;


import co.edu.poli.springsolucion.persistence.entity.Customer;
import co.edu.poli.springsolucion.persistence.entity.Delivery;
import co.edu.poli.springsolucion.persistence.entity.OrderEntity;
import co.edu.poli.springsolucion.persistence.repository.CustomerRepository;
import co.edu.poli.springsolucion.persistence.repository.DeliveryRepository;
import co.edu.poli.springsolucion.persistence.repository.OrderRepository;
import co.edu.poli.springsolucion.services.DTO.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }


    @Override
    public Optional<OrderEntity> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public OrderEntity create(OrderDTO orderDTO) {

        // Verificar si el customerid proporcionado existe en la tabla Customer
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("El customerid no existe en la tabla Customer."));

        // Verificar si el deliveryid proporcionado existe en la tabla Delivery
        Delivery delivery = deliveryRepository.findById(orderDTO.getDeliveryId())
                .orElseThrow(() -> new IllegalArgumentException("El deliveryid no existe en la tabla Delivery."));


        // Asignar las entidades Customer y Delivery a la orden
        OrderEntity order = new OrderEntity();
        order.setOrderDate(orderDTO.getOrderDate());
        order.setCustomer(customer);
        order.setDelivery(delivery);

        return orderRepository.save(order);
    }

    @Override
    public OrderEntity edit(Long orderId, OrderDTO orderDTO) {
        // Verifica si la orden existe
        OrderEntity existingOrder = orderRepository.findById(orderId).orElse(null);
        if (existingOrder != null) {

            // Verificar si el customerid proporcionado existe en la tabla Customer
            Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("El customerid no existe en la tabla Customer."));

            // Verificar si el deliveryid proporcionado existe en la tabla Delivery
            Delivery delivery = deliveryRepository.findById(orderDTO.getDeliveryId())
                    .orElseThrow(() -> new IllegalArgumentException("El deliveryid no existe en la tabla Delivery."));

            // Actualiza los campos de la orden existente con los valores de la orden actualizada
            existingOrder.setOrderDate(orderDTO.getOrderDate());
            existingOrder.setCustomer(customer);
            existingOrder.setDelivery(delivery);

            // Guarda la orden actualizada
            return orderRepository.save(existingOrder);

        } else {
            throw new IllegalArgumentException("La orden no existe.");
        }
    }

    @Override
    public boolean delete(Long orderId) {
        // Verifica si la orden existe
        OrderEntity existingOrder = orderRepository.findById(orderId).orElse(null);

        if (existingOrder != null) {
            // Elimina la orden
            orderRepository.delete(existingOrder);
            return true; // Devolver true si se eliminó con éxito

        }

            throw new IllegalArgumentException("La orden no existe.");


    }


}
