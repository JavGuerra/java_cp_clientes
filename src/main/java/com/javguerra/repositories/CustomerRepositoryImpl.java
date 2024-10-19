package main.java.com.javguerra.repositories;

import main.java.com.javguerra.entities.Customer;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class CustomerRepositoryImpl implements CustomerRepository {

    // atributos
    private List<Customer> customers = new ArrayList<>();

    // constructores
    public CustomerRepositoryImpl() {}

    public CustomerRepositoryImpl(List<Customer> customers) {
        this.customers = customers;
    }

    // métodos
    @Override
    public ArrayList<Customer> findAll() {
        // devolver un clon para evitar que lo modifiquen desde afuera.
        return new ArrayList<>(customers);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean save(Customer customer) {
        try {
            return customers.add(customer);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void update(Long id, Customer customer) {
        Customer cliente = findById(id).orElse(null);
        if (cliente == null) return;

        if (customer.getNombre() != null
                && customer.getNombre().length() < 50)
            cliente.setNombre(customer.getNombre());

        if (customer.getApellido() != null
        && customer.getApellido().length() < 50) {
            cliente.setApellido(customer.getApellido());
        }

        if (customer.getEmail() != null
                && customer.getEmail().contains("@")
                && customer.getEmail().contains(".")) {
            cliente.setEmail(customer.getEmail());
        }

        if (customer.getEdad() >= 18 && customer.getEdad() <= 110) {
            cliente.setEdad(customer.getEdad());
        }
    }

    @Override
    public boolean removeById(Long id) {
        return customers.removeIf(c -> c.getId().equals(id));
    }

    @Override
    public void removeAll() {
        customers.clear();
    }

}
