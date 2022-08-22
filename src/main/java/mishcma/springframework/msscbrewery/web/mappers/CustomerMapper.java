package mishcma.springframework.msscbrewery.web.mappers;

import mishcma.springframework.msscbrewery.domain.Customer;
import mishcma.springframework.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}
