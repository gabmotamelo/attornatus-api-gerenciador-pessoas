package attornatus.service;

import attornatus.builder.dto.PessoaDTOBuilder;
import attornatus.domain.error.PessoaJaFoiRegistradaException;
import attornatus.domain.error.PessoaNaoEncontradaException;
import attornatus.domain.model.entities.Pessoa;
import attornatus.domain.model.repositories.PessoaRepository;
import attornatus.infra.dtos.mapper.PessoaMapper;
import attornatus.infra.dtos.request.PessoaDTO;
import attornatus.infra.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    private PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    private static final String INVALIDO_PESSOA_NOME = "Gabriel";

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void quandoNovaPessoaInformadaEntaoDeveraSerCriada() throws PessoaJaFoiRegistradaException{
        PessoaDTO pessoaDTO = PessoaDTO.builder().build();
        Pessoa expectedPessoaSalva = pessoaMapper.toModel(pessoaDTO);

        when(pessoaRepository.save(expectedPessoaSalva)).thenReturn(expectedPessoaSalva);

        PessoaDTO criadaPessoaDTO = pessoaService.criarPessoa(pessoaDTO);

        assertEquals(pessoaDTO.getId(), criadaPessoaDTO.getId());
        assertEquals(pessoaDTO.getNomeCompleto(), criadaPessoaDTO.getNomeCompleto());
        assertEquals(pessoaDTO.getDataNascimento(), criadaPessoaDTO.getDataNascimento());
        assertEquals(pessoaDTO.getEnderecos(), criadaPessoaDTO.getEnderecos());
    }

    @Test
    void quandoJaFoiResgistradaPessoaInformadaExceptionLancada(){
        PessoaDTO pessoaDTO = PessoaDTO.builder().build();
        Pessoa pessoaDuplicada = pessoaMapper.toModel(pessoaDTO);

        when(pessoaRepository.findByNomeCompleto(pessoaDTO.getNomeCompleto())).thenReturn(pessoaDuplicada);

        assertThrows(PessoaJaFoiRegistradaException.class,() -> pessoaService.criarPessoa(pessoaDTO));
    }

    @Test
    void quandoUmPessoaValidaForInformadaRetornaUmaPessoa() throws PessoaNaoEncontradaException{
        PessoaDTO esperadaPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();
        Pessoa esperadaPessoaEncontrada = pessoaMapper.toModel(esperadaPessoaDTO);

        when(pessoaRepository.findByNomeCompleto(esperadaPessoaDTO.getNomeCompleto())).thenReturn(esperadaPessoaEncontrada);

        PessoaDTO foundPessoaDTO = pessoaService.encontraPessoa(esperadaPessoaDTO.getNomeCompleto());

        assertEquals(esperadaPessoaDTO, foundPessoaDTO);
    }

    @Test
    void quandoListaPessoasChamadaEntaoretornaUmaListaDePessoas() {
        PessoaDTO expectedPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();
        Pessoa expectedFoundPessoa = pessoaMapper.toModel(expectedPessoaDTO);

        when(pessoaRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPessoa));

        List<PessoaDTO> encontradoPessoaDTO = pessoaService.listarPessoas();

        assertFalse(encontradoPessoaDTO.isEmpty());
        assertEquals(expectedPessoaDTO, encontradoPessoaDTO.get(0));
    }

    @Test
    void quandoListaPessoaChamadaEntaoRetornaUmaListaVazia() {
        when(pessoaRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<PessoaDTO> encontradoPessoaDTO = pessoaService.listarPessoas();

        assertTrue(encontradoPessoaDTO.isEmpty());
    }

    @Test
    void quandoExclusaoEChamadoComNomeValidoEntaoRetornaUmaPessoaQueDeveriaSerDeletada() throws PessoaNaoEncontradaException {
        PessoaDTO expectedExcludedPessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();
        Pessoa expectedExcludedPessoa = pessoaMapper.toModel(expectedExcludedPessoaDTO);

        when(pessoaRepository.findByNomeCompleto(expectedExcludedPessoaDTO.getNomeCompleto())).thenReturn(Optional.of(expectedExcludedPessoa));
        doNothing().when(pessoaRepository).deleteByNomeCompleto(expectedExcludedPessoa.getNomeCompleto());

        pessoaService.deleteByNome(expectedExcludedPessoaDTO.getNomeCompleto());

        verify(pessoaRepository, times(1)).findByNomeCompleto(expectedExcludedPessoaDTO.getNomeCompleto());
        verify(pessoaRepository, times(1)).deleteByNomeCompleto(expectedExcludedPessoaDTO.getNomeCompleto());
    }

    @Test
    void quandoExclusaoEChamadoComNomeInvalidoEntaoRetornaUmaExceptionQueDeveriaSerLancada() {

        when(pessoaRepository.findByNomeCompleto(INVALIDO_PESSOA_NOME)).thenReturn(Optional.empty());

        assertThrows(PessoaNaoEncontradaException.class,() -> pessoaService.encontraPessoa(INVALIDO_PESSOA_NOME));
    }
}