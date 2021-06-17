package carlos.souza.projetoapi.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import carlos.souza.projetoapi.module.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Integer> {
    

}
