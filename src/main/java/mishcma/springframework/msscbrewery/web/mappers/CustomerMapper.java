package mishcma.springframework.msscbrewery.web.mappers;

import mishcma.springframework.msscbrewery.domain.Customer;
import mishcma.springframework.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface CustomerMapper {

    CustomerDto CustomerToCustomerDto(Customer customer);

    Customer CustomerDtoToCustomer(CustomerDto customerDto);
}
