package attornatus.builder.dto;

import attornatus.domain.model.enums.EnderecoType;
import attornatus.infra.dtos.request.EnderecoDTO;
import attornatus.infra.dtos.request.PessoaDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Builder
public class PessoaDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nomeCompleto = "Gabriel Mota Melo";


    @Builder.Default
    private LocalDate dataNascimento = LocalDate.of(2009,12,12);


    @Builder.Default
    List<EnderecoDTO> enderecos = Collections.singletonList(EnderecoDTO.builder()
                    .enderecoType(EnderecoType.PRINCIPAL)
            .logradouro("Rua dos Palmares")
            .cep("05677888")
            .numero("232")
            .complemento("b")
            .cidade("Sao Paulo").build());


    public PessoaDTO toPessoaDto(){
        return new PessoaDTO(id, nomeCompleto, dataNascimento, enderecos);
    }
}
