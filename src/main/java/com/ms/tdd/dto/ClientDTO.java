package com.ms.tdd.dto;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
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
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String cel;
    @NonNull
    private String cpf;

    public  ClientDTO (Client entity){
        BeanUtils.copyProperties(entity, this);
    }

}
