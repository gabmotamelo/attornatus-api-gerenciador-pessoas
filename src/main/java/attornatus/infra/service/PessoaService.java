package attornatus.infra.service;

import attornatus.domain.error.PessoaJaFoiRegistradaException;
import attornatus.domain.model.entities.Pessoa;
import attornatus.domain.model.repositories.PessoaRepository;
import attornatus.infra.dtos.mapper.PessoaMapper;
import attornatus.infra.dtos.request.PessoaDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    private void verificarPessoaExiste(String nome) throws PessoaJaFoiRegistradaException {
        Optional<Pessoa> optPessoaSalva = Optional.ofNullable(pessoaRepository.findByNomeCompleto(nome));
        if (optPessoaSalva.isPresent()) {
            throw new PessoaJaFoiRegistradaException(nome);
        }
    }

    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) throws PessoaJaFoiRegistradaException {
        verificarPessoaExiste(pessoaDTO.getNomeCompleto());
        Pessoa pessoa = pessoaMapper.toModel(pessoaDTO);
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return pessoaMapper.toDTO(pessoaSalva);
    }


}
