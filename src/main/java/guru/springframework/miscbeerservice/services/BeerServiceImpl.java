package guru.springframework.miscbeerservice.services;

import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import guru.springframework.miscbeerservice.domain.Beer;
import guru.springframework.miscbeerservice.repositories.BeerRepository;
import guru.springframework.miscbeerservice.web.controller.NotFoundException;
import guru.springframework.miscbeerservice.web.mappers.BeerMapper;
import guru.springframework.miscbeerservice.web.model.BeerDto;
import guru.springframework.miscbeerservice.web.model.BeerPagedList;
import guru.springframework.miscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	
	@Override
	public BeerDto getBeerById(UUID beerId) {
		return beerMapper.beerToBeerDto(
				beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeerById(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());
		
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
		// TODO Auto-generated method stub
		return null;
	}

}
