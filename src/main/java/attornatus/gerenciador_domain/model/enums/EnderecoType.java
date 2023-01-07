package attornatus.gerenciador_domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnderecoType {

    PRINCIPAL("Principal"),
    ALTERNATIVO("Alternativo");

    private final String descricao;
}
