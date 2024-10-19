package main.java.com.javguerra.services;

import main.java.com.javguerra.entities.Customer;
import main.java.com.javguerra.repositories.CustomerRepository;

import java.util.Optional;
import java.util.List;

import static main.java.com.javguerra.utils.ConsoleInput.*;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public long getMaxId() {
        return customerRepository.findAll().stream()
                .mapToLong(Customer::getId)
                .max()
                .orElse(0L);
    }

    @Override
    public Optional<Customer> existById() {
        Optional<Customer> customer = findById();
        if (customer.isEmpty()) {
            System.out.println("No existe un cliente con ese id.");
            return Optional.empty();
        }
        return customer;
    }

    @Override
    public Optional<Customer> findById() {
        return customerRepository.findById(getLongIntPos("Id: "));
    }

    @Override
    public Optional<Customer> findById(long id) {
        if (id <= 0 || id > getMaxId()) return Optional.empty();
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void listAllAccounts() {
        for (Customer customer : getAllCustomers()) {
            System.out.println(customer);
        }
    }

    @Override
    public Customer createNewCustomer() {
        Customer customer = new Customer();
        customer.setId(getMaxId() + 1L);
        customer.setNombre(getString("Nombre: "));
        customer.setApellido(getString("Apellidos: "));
        customer.setEmail(getWord("Email: "));
        customer.setEdad(getLongIntPos("Edad: ").intValue());
        return customer;
    }

    @Override
    public void updateById(long id) {
        if (id <= 0 || id > getMaxId() || findById(id).isEmpty()) return;
        System.out.println(findById(id).get());
        if (getYesNo("¿Desea actualizar los datos de este cliente (S/N)? ")) {
                System.out.println("Escriba los nuevos datos del cliente:");
                Customer customer = createNewCustomer();
                if (getYesNo("¿Desea guardar los datos (S/N)? ")) {
                    customerRepository.update(id, customer);
                    System.out.println("Datos actualizados.");
                }
                else System.out.println("Datos no actualizados.");
        }
        else System.out.println("No se ha actualizado el cliente.");
    }

    @Override
    public boolean save(Customer customer) {
        if (getYesNo("¿Desea guardar los datos (S/N)? ")) {
            customerRepository.save(customer);
            System.out.println("Datos actualizados.");
            return true;
        }
        else System.out.println("Datos no actualizados.");
        return false;
    }

    @Override
    public void removeById(long id) {
        if (id <= 0 || id > getMaxId() || findById(id).isEmpty()) return;
        System.out.println(findById(id).get());
        if (getYesNo("¿Desea eliminar este cliente (S/N)? ")) {
            boolean remove = customerRepository.removeById(id);
            if (remove) System.out.println("Cliente eliminado.");
            else System.out.println("No ha sido posible eliminar el cliente.");
        }
        else System.out.println("No se ha eliminado el cliente.");
    }

    @Override
    public boolean removeAll() {
        if (getYesNo("¿Desea eliminar todos los clientes (S/N)? ")) {
            customerRepository.removeAll();
            System.out.println("Todos los clientes han sido eliminados.");
            return true;
        }
        else System.out.println("No se han eliminado los clientes.");
        return false;
    }

}