package attornatus.infra.dtos.mapper;


import attornatus.domain.model.entities.Pessoa;
import attornatus.infra.dtos.request.PessoaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "dataDeNascimento", source = "dataDeNascimento", dateFormat = "dd-MM-yyyy")
    Pessoa toModel(PessoaDTO personDTO);

    @Mapping(target = "dataDeNascimento", source = "dataDeNascimento", dateFormat = "dd-MM-yyyy")
    PessoaDTO toDTO(Pessoa pessoa);

}
