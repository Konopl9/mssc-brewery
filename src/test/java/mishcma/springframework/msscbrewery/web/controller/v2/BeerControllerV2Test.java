package mishcma.springframework.msscbrewery.web.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import mishcma.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import mishcma.springframework.msscbrewery.web.model.v2.BeerStyleEnum;
import mishcma.springframework.msscbrewery.web.services.v2.BeerServiceV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerControllerV2.class)
public class BeerControllerV2Test {

    @MockBean
    BeerServiceV2 beerServiceV2;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDtoV2 validBeerV2;

    @BeforeEach
    public void setUp() {
        validBeerV2 = BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName("Beer1")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(123456789012L)
                .build();
    }

    @Test
    public void getBeer() throws Exception {
        given(beerServiceV2.getBeerById(any(UUID.class))).willReturn(validBeerV2);

        mockMvc.perform(get("/api/v2/beer/" + validBeerV2.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(validBeerV2.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Beer1")));
    }

    @Test
    public void handlePost() throws Exception {
        //given
        BeerDtoV2 beerDto = validBeerV2;
        beerDto.setId(null);
        BeerDtoV2 savedDto = BeerDtoV2.builder().id(UUID.randomUUID()).beerName("New Beer").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerServiceV2.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v2/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        BeerDtoV2 beerDto = validBeerV2;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(put("/api/v2/beer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerServiceV2).should().updateBeer(any(), any());

    }
}