package guru.springframework.miscbeerservice.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import guru.springframework.miscbeerservice.web.model.BeerDto;
import guru.springframework.miscbeerservice.web.model.BeerPagedList;
import guru.springframework.miscbeerservice.web.model.BeerStyleEnum;

public interface BeerService {
	
	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
	
	BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

	BeerDto getBeerById(UUID beerId);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeerById(UUID beerId, BeerDto beerDto);

}
