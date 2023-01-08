package attornatus.controller;

import attornatus.app.expose.api.PessoaController;
import attornatus.builder.dto.PessoaDTOBuilder;
import attornatus.domain.error.PessoaNaoEncontradaException;
import attornatus.infra.dtos.request.PessoaDTO;
import attornatus.infra.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    private static final String PESSOA_API_URL_PATH = "/api/v1/pessoa";

    private MockMvc mockMvc;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void quandoPOSTChamadoEntaoPessoaCriada() throws Exception{
        PessoaDTO pessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();

        when(pessoaService.encontraPessoa(pessoaDTO.getNomeCompleto())).thenReturn(pessoaDTO);

        mockMvc.perform(post(PESSOA_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(PessoaDTOBuilder.asJsonString(pessoaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCompleto", is(pessoaDTO.getNomeCompleto())))
                .andExpect(jsonPath("$.dataNascimento", is(pessoaDTO.getDataNascimento())))
                .andExpect(jsonPath("$.enderecos", is(pessoaDTO.getEnderecos())));

    }
    @Test
    void quandoPOSTChamadoComCampoObrigatorioEntaoErroRetornado() throws Exception {
        PessoaDTO pessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();
        pessoaDTO.setNomeCompleto(null);

        mockMvc.perform(post(PESSOA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PessoaDTOBuilder.asJsonString(pessoaDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void quandoGETChamadoComNomeValidoRegistradoEntaoStatusOKRetornado()  throws Exception {
        PessoaDTO pessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();

        when(pessoaService.encontraPessoa(pessoaDTO.getNomeCompleto())).thenReturn(pessoaDTO);

        mockMvc.perform(get(PESSOA_API_URL_PATH+ "/" + pessoaDTO.getNomeCompleto())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCompleto", is(pessoaDTO.getNomeCompleto())))
                .andExpect(jsonPath("$.dataNascimento", is(pessoaDTO.getDataNascimento())))
                .andExpect(jsonPath("$.enderecos", is(pessoaDTO.getEnderecos())));
    }

    @Test
    void quandoGETChamadoComNenhumNomeRegistradoEntaoStatusNaoEncontradoRetornado() throws Exception {
        PessoaDTO pessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();

        when(pessoaService.encontraPessoa(pessoaDTO.getNomeCompleto())).thenThrow(PessoaNaoEncontradaException.class);

        mockMvc.perform(get(PESSOA_API_URL_PATH + "/" + pessoaDTO.getNomeCompleto())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void quandoGETListaComPessoasChamadoEntaoStatusOkERetornado() throws Exception {
        PessoaDTO pessoaDTO = PessoaDTOBuilder.builder().build().toPessoaDto();

        when(pessoaService.listarPessoas()).thenReturn(Collections.singletonList(pessoaDTO));

        mockMvc.perform(get(PESSOA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCompleto", is(pessoaDTO.getNomeCompleto())))
                .andExpect(jsonPath("$.dataNascimento", is(pessoaDTO.getDataNascimento())))
                .andExpect(jsonPath("$.enderecos", is(pessoaDTO.getEnderecos())));
    }

    @Test
    void quandoGETListaSemPessoasChamadoEntaoStatusOkERetornado() throws Exception {
        when(pessoaService.listarPessoas()).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(get(PESSOA_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
