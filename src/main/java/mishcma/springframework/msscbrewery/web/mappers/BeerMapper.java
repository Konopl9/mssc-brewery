package mishcma.springframework.msscbrewery.web.mappers;

import mishcma.springframework.msscbrewery.domain.Beer;
import mishcma.springframework.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);

    Beer BeerDtoToBeer(BeerDto beerDto);
}