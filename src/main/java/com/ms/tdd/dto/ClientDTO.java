package com.ms.tdd.dto;

import com.ms.tdd.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private String id;
    private String name;
    private String email;
    private String cel;
    private String cpf;


    public  ClientDTO (Client entity){
        BeanUtils.copyProperties(entity, this);
    }

}
