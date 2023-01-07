package attornatus.infra.dtos.request;

import attornatus.domain.model.enums.EnderecoType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private EnderecoType enderecoType;

    @NotBlank
    @Size(min = 5, max = 50)
    private String logradouro;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    @Size(min = 1, max = 8)
    private String numero;

    @NotBlank
    @Size(min = 1, max = 2)
    private String complemento;

    @NotBlank
    @Size(min = 2, max = 14)
    private String cidade;

}
