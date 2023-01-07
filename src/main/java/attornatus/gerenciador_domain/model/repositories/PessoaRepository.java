package attornatus.gerenciador_domain.model.repositories;

import attornatus.gerenciador_domain.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

  Pessoa findByNomeCompleto(String nome);

}
