package attornatus.app.expose.api;


import attornatus.domain.error.PessoaJaFoiRegistradaException;
import attornatus.infra.dtos.request.PessoaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Gerenciar pessoas")
public interface PessoaControllerDocs {

    @ApiOperation(value = "Hello Word!!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a simple hello world")
    })
    PessoaDTO criarPessoa(PessoaDTO pessoaDTO) throws PessoaJaFoiRegistradaException;
}
