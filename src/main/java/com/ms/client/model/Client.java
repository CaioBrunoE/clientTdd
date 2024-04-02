package com.ms.client.model;
import com.ms.client.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "clients")
public class Client implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;
    private String cel;
    private String cpf;
    private String created;
    private String updated;
    private String registryUser;

    public Client (ClientDTO clientDTO){
        BeanUtils.copyProperties(clientDTO, this);
    }

    public Client() {
        super();
    }
}
