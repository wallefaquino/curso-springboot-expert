package com.walleftech.csbe.entities.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UsuarioDTO {

    public Long id;
    public String login;
}
