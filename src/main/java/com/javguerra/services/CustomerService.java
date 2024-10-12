package main.java.com.javguerra.services;

import main.java.com.javguerra.entities.Customer;
import main.java.com.javguerra.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.Optional;

import static main.java.com.javguerra.utils.ConsoleInput.*;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Devuelve el id máximo de los clientes
     * @return long
     */
    public long getMaxId() {
        return customerRepository.findAll().stream()
                .mapToLong(Customer::getId)
                .max()
                .orElse(0L);
    }

    /**
     * Comprueba si existe un cliente por su id,
     * si no existe, muestra un mensaje y devuelve vacio
     * @return Optional<Customer>
     */
    public Optional<Customer> existById() {
        Optional<Customer> customer = findById();
        if (customer.isEmpty()) {
            System.out.println("No existe un cliente con ese id.");
            return Optional.empty();
        }
        return customer;
    }

    /**
     * Devuelve un cliente del que se pide la id
     * @return Optional<Customer>
     */
    public Optional<Customer> findById() {
        return customerRepository.findById(getLongIntPos("Id: "));
    }

    /**
     * Devuelve un cliente por la id dada
     * @param id long
     * @return Optional<Customer>
     */
    public Optional<Customer> findById(long id) {
        if (id <= 0 || id > getMaxId()) return Optional.empty();
        return customerRepository.findById(id);
    }

    /**
     * Devuelve la lista de clientes
     * @return ArrayList<Customer>
     */
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Lista todos los clientes
     */
    public void listAllAccounts() {
        for (Customer customer : getAllCustomers()) {
            System.out.println(customer);
        }
    }

    /**
     * Crea un nuevo cliente
     * @return Customer
     */
    public Customer createNewCustomer() {
        Customer customer = new Customer();
        customer.setId(getMaxId() + 1L);
        customer.setNombre(getWord("Nombre: "));
        customer.setApellido(getWord("Apellido: "));
        customer.setEmail(getWord("Email: "));
        customer.setEdad(getLongIntPos("Edad: ").intValue());
        return customer;
    }

    /**
     * Actualiza un cliente por su id dada
     * Si no existe el cliente, no hace nada
     * @param id long
     */
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

    /**
     * Agrega un cliente
     * @param customer Customer
     */
    public boolean save(Customer customer) {
        if (getYesNo("¿Desea guardar los datos (S/N)? ")) {
            customerRepository.save(customer);
            System.out.println("Datos actualizados.");
            return true;
        }
        else System.out.println("Datos no actualizados.");
        return false;
    }

    /**
     * Elimina un cliente por su id dada
     * Si no existe el cliente, no hace nada
     * @param id long
     */
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

    /**
     * Elimina todos los clientes
     * @return boolean
     */
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