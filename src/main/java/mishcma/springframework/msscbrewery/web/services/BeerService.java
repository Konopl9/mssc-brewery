package mishcma.springframework.msscbrewery.web.services;

import mishcma.springframework.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
