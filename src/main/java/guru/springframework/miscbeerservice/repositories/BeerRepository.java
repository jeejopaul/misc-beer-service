package guru.springframework.miscbeerservice.repositories;

import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import guru.springframework.miscbeerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
