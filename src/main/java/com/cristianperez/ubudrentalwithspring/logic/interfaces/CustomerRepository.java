package com.cristianperez.ubudrentalwithspring.logic.interfaces;

import com.cristianperez.ubudrentalwithspring.logic.models.Customer;
import com.cristianperez.ubudrentalwithspring.logic.models.Token;

import java.util.List;

public interface CustomerRepository {
    Customer validateFirstAndLastName (String firstAndLastName);
    String saveTokenInDatabase(String tokenCode);
    Token validateApiToken(String apiToken);
}
