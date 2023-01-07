package attornatus.app.expose.api;


import attornatus.domain.error.PessoaJaFoiRegistradaException;
import attornatus.domain.error.PessoaNaoEncontradaException;
import attornatus.infra.dtos.request.PessoaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

@Api("Gerenciar pessoas")
public interface PessoaControllerDocs {

    @ApiOperation(value = "Hello Word!!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a simple hello world")
    })
    PessoaDTO criarPessoa(PessoaDTO pessoaDTO) throws PessoaJaFoiRegistradaException;

    @ApiOperation(value = "Retorna uma pessoa encontrada pelo nome dado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucess encontrado uma pessoa no sistema"),
            @ApiResponse(code = 404, message = "Pessoa com o nome dado não encontrado.")
    })
    PessoaDTO encontraPessoa(@PathVariable String nome) throws PessoaNaoEncontradaException;
}
