package mishcma.springframework.msscbrewery.web.services;

import mishcma.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CustomerService {

    CustomerDto getCustomerById(UUID id);
}
