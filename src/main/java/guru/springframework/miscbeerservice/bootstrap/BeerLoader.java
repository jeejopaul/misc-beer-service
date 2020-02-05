package guru.springframework.miscbeerservice.bootstrap;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.miscbeerservice.domain.Beer;
import guru.springframework.miscbeerservice.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;
	
	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if(beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
								.beerName("Mango Bobs")
								.beerStyle("IPA")
								.quantityToBrew(200)
								.minOnHand(12)
								.upc(32127198L)
								.price(new BigDecimal("12.95"))
								.build());
			
			beerRepository.save(Beer.builder()
					.beerName("Galaxy Cat")
					.beerStyle("PALE_ALE")
					.quantityToBrew(300)
					.minOnHand(12)
					.upc(32127578L)
					.price(new BigDecimal("10.95"))
					.build());
		}
			System.out.println("****** Loaded beers ****** " + beerRepository.count());
	}
}
