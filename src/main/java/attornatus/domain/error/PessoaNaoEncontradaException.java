package attornatus.domain.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNaoEncontradaException extends Exception {

    public PessoaNaoEncontradaException(String nomePessoa){
        super(String.format("Pessoa com o nome %s não foi encontrada no sistema.", nomePessoa));
    }
}
