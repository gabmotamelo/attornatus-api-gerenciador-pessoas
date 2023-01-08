package attornatus.app.expose.api;


import attornatus.domain.error.PessoaJaFoiRegistradaException;
import attornatus.domain.error.PessoaNaoEncontradaException;
import attornatus.infra.dtos.request.PessoaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    @ApiOperation(value = "Retorna a lista de todas pessoas registrada no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de todas pessoas registradas no sistema"),
    })
    List<PessoaDTO> listarPessoas();

    @ApiOperation(value = "Deletar uma pessoa pelo nome dado valido")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Sucesso pessoa deletada do sistema."),
            @ApiResponse(code = 404, message = "Pessoa com o nome informado não foi encontrada.")
    })
    void deleteByNome(@PathVariable String nome) throws PessoaNaoEncontradaException;

}
