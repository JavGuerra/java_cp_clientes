package main.java.com.javguerra.controllers;

import main.java.com.javguerra.entities.Customer;
import main.java.com.javguerra.services.CustomerService;

import java.util.Optional;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        thereAreCustomers();
    }

    /**
     * Comprueba si hay clientes y si no, crea uno
     */
    private void thereAreCustomers() {
        if (customerService.getAllCustomers().isEmpty()) {
            System.out.println("No hay clientes. Debe crear al menos uno.");
            boolean exit;
            do { // hasta que se guarde un cliente
                exit = newCustomer();
            } while (!exit);
        }
    }

    /**
     * Lista todos los clientes
     */
    public void listCustomers() {
        System.out.println("Listar clientes");
        customerService.listAllAccounts();
    }

    /**
     * Mostrar un cliente por su id
     */
    public void listCustomerById() {
        System.out.println("Mostrar un cliente");
        Optional<Customer> customer = customerService.existById();
        customer.ifPresent(System.out::println);
    }

    /**
     * Crea un nuevo cliente
     * @return boolean
     */
    public boolean newCustomer() {
        System.out.println("Crear un nuevo cliente");
        return customerService.save(customerService.createNewCustomer());
    }

    /**
     * Actualiza un cliente por su id
     */
    public void updateCustomerById() {
        System.out.println("Actualizar un cliente");
        customerService.listAllAccounts();
        Optional<Customer> customer = customerService.existById();
        customer.ifPresent(value -> customerService.updateById(value.getId()));
    }

    /**
     * Elimina un cliente por su id
     */
    public void deleteCustomerById() {
        System.out.println("Eliminar un cliente por su id");
        customerService.listAllAccounts();
        Optional<Customer> customer = customerService.existById();
        customer.ifPresent(value -> customerService.removeById(value.getId()));
        thereAreCustomers();
    }

    /**
     * Elimina todos los clientes
     */
    public void deleteAllCustomers() {
        if (customerService.removeAll()) thereAreCustomers();
    }
}
