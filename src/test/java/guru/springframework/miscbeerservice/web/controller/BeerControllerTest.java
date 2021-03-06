package guru.springframework.miscbeerservice.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.miscbeerservice.bootstrap.BeerLoader;
import guru.springframework.miscbeerservice.services.BeerService;
import guru.springframework.miscbeerservice.web.model.BeerDto;
import guru.springframework.miscbeerservice.web.model.BeerStyleEnum;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerService beerService;

	@Test
	void testGetBeerById() throws Exception {
		
		given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());
		
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON)).
				andExpect(status().isOk());
	}

	@Test
	void testSaveNewBeer() throws Exception {
		
		BeerDto beerDto = getValidBeerDto();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		given(beerService.saveNewBeer(any())).willReturn(beerDto);
		
		mockMvc.perform(post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testUpdateBeerById() throws Exception {
		
		given(beerService.updateBeerById(any(), any())).willReturn(getValidBeerDto());
		
		BeerDto beerDto = getValidBeerDto();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isNoContent());
	}
	
	BeerDto getValidBeerDto() {
		return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
	}

}
