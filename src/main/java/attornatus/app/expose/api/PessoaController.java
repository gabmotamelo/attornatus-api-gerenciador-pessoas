package attornatus.app.expose.api;


import attornatus.domain.error.PessoaJaFoiRegistradaException;
import attornatus.domain.error.PessoaNaoEncontradaException;
import attornatus.infra.dtos.request.PessoaDTO;
import attornatus.infra.service.PessoaService;
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

    @GetMapping("/{nome}")
    public PessoaDTO encontraPessoa(@PathVariable String nome) throws PessoaNaoEncontradaException{
        return pessoaService.encontraPessoa(nome);
    }

}
