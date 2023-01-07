package attornatus.gerenciador_app.expose.api;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/pessoas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaController implements PessoaControllerDocs {

    private final PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaDTO criarPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) throws PessoaJaFoiRegistradaException {
        return pessoaService.criarPessoa(pessoaDTO);
    }

}
