package attornatus.builder.dto;

import attornatus.domain.model.enums.EnderecoType;
import lombok.Builder;

@Builder
public class EnderecoDTOBuilder {

    private Long id = 1L;

    private EnderecoType tipo;


    private String logradouro;


    private String cep;


    private String numero;


    private String complemento;

    private String cidade;
}
