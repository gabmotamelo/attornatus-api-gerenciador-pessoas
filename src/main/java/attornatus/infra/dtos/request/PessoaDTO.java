package attornatus.infra.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String nomeCompleto;

    @NotNull
    private LocalDate dataNascimento;

    @Valid
    @NotBlank
    private List<EnderecoDTO> enderecos;

}
