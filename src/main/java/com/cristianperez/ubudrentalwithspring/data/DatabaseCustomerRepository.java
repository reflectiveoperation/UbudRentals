package com.cristianperez.ubudrentalwithspring.data;

import com.cristianperez.ubudrentalwithspring.logic.interfaces.CustomerRepository;
import com.cristianperez.ubudrentalwithspring.logic.models.Customer;
import com.cristianperez.ubudrentalwithspring.logic.models.Token;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


@Repository
public class DatabaseCustomerRepository implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcCustomerTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DatabaseCustomerRepository(JdbcTemplate jdbcCustomerTemplate) {
        this.jdbcCustomerTemplate = jdbcCustomerTemplate;
    }

    @Override
    public Customer validateFirstAndLastName(String firstAndLastName) {
        String[] fNameAndLastNameArr = firstAndLastName.split("\\s");
        return validateAndRetrievePersonFromDB(fNameAndLastNameArr[0],fNameAndLastNameArr[1]);
    }

    @Override
    public String saveTokenInDatabase(String tokenCode) {
        String sqlQueryToInsertToken = "INSERT INTO api_tokens (token_code" +
                "VALUES (:tokenCode);";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("tokenCode",tokenCode);
        namedParameterJdbcTemplate.update(sqlQueryToInsertToken,namedParameters);
        return tokenCode;
    }


    private Customer validateAndRetrievePersonFromDB(String firstName, String lastName) {
        String sqlQueryToFindCustomerByFirstNameAndLastName = "SELECT * FROM customers WHERE c_first_name = \'"
                + firstName + "\' AND c_last_name = \'" + lastName + "\';";
        try {
            return jdbcCustomerTemplate.queryForObject(sqlQueryToFindCustomerByFirstNameAndLastName, (resultSet, i) -> {
                Customer customer = new Customer();
                customer.setCustomerFirstName(resultSet.getString("c_first_name"));
                customer.setCustomerLastName(resultSet.getString("c_last_name"));
                return customer;
            });
        } catch (Exception exc) {
            return null;
        }
    }
}
