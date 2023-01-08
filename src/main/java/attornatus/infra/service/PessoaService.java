package attornatus.infra.service;

import attornatus.domain.error.PessoaJaFoiRegistradaException;
import attornatus.domain.error.PessoaNaoEncontradaException;
import attornatus.domain.model.entities.Pessoa;
import attornatus.domain.model.repositories.PessoaRepository;
import attornatus.infra.dtos.mapper.PessoaMapper;
import attornatus.infra.dtos.request.PessoaDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) throws PessoaJaFoiRegistradaException {
        verificarPessoaJaFoiRegistrada(pessoaDTO.getNomeCompleto());
        Pessoa pessoa = pessoaMapper.toModel(pessoaDTO);
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return pessoaMapper.toDTO(pessoaSalva);
    }

    public PessoaDTO encontraPessoa(String nome) throws PessoaNaoEncontradaException {
        Pessoa pessoaEncontrada =pessoaRepository.findByNomeCompleto(nome);
        return pessoaMapper.toDTO(pessoaEncontrada);
    }


    public List<PessoaDTO> listarPessoas() {
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteByNome(String nome) throws PessoaNaoEncontradaException {
        verificarSeExiste(nome);
        pessoaRepository.deleteByNomeCompleto(nome);
    }

    private void verificarPessoaJaFoiRegistrada(String nome) throws PessoaJaFoiRegistradaException {
        Optional<Pessoa> optPessoaSalva = Optional.ofNullable(pessoaRepository.findByNomeCompleto(nome));
        if (optPessoaSalva.isPresent()) {
            throw new PessoaJaFoiRegistradaException(nome);
        }
    }

    private void verificarSeExiste(String nome) throws PessoaNaoEncontradaException {
        Optional<Pessoa> optPessoaSalva = Optional.ofNullable(pessoaRepository.findByNomeCompleto(nome));
        if (optPessoaSalva.isEmpty()){
            throw new PessoaNaoEncontradaException(nome);
        }
    }

}
