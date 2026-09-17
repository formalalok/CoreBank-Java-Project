package com.corebank.dao;

import com.corebank.model.Customer;

public class CustomerDAOTest {

    public static void main(String[] args) {

        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = customerDAO.getCustomer(1);

        if (customer != null) {
            System.out.println("Customer found!");
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
        } else {
            System.out.println("Customer not found!");
        }
    }
}