package attornatus.domain.model.repositories;

import attornatus.domain.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

  Pessoa findByNomeCompleto(String nome);

}
