package co.edu.poli.springsolucion.services.DTO;


import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    private String name;
    private String email;
    private Date birthdate;
}
