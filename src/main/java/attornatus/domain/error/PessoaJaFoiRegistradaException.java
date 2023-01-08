package attornatus.domain.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PessoaJaFoiRegistradaException extends Exception{

    public PessoaJaFoiRegistradaException(String nomeCompleto) {
        super(String.format("Pessoa com o nome %s já foi registrada no sistema.", nomeCompleto));
    }
}
