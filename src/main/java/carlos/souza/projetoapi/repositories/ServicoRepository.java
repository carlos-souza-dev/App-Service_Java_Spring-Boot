package carlos.souza.projetoapi.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import carlos.souza.projetoapi.module.Servico;

@Repository
public interface ServicoRepository extends PagingAndSortingRepository<Servico, Integer>{

}
