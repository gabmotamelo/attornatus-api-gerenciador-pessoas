package attornatus.infra.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespostaPessoaCriadaDTO {

    private String mensagemPessoaCriada;

}
