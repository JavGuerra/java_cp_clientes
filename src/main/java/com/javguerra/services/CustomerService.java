package main.java.com.javguerra.services;

import main.java.com.javguerra.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    /**
     * Devuelve el id máximo de los clientes
     * @return long
     */
    long getMaxId();

    /**
     * Comprueba si existe un cliente por su id,
     * si no existe, muestra un mensaje y devuelve vacío
     * @return Optional<Customer>
     */
    Optional<Customer> existById();

    /**
     * Devuelve un cliente del que se pide la id
     * @return Optional<Customer>
     */
    Optional<Customer> findById();
    /**
     * Devuelve un cliente por su id dada
     * @param id long
     * @return Optional<Customer>
     */
    Optional<Customer> findById(long id);

    /**
     * Devuelve la lista de clientes
     * @return ArrayList<Customer>
     */
    List<Customer> getAllCustomers();

    /**
     * Lista todos los clientes
     */
    void listAllAccounts();

    /**
     * Crea un nuevo cliente
     * @return Customer
     */
    Customer createNewCustomer();

    /**
     * Actualiza un cliente por su id dada
     * Si no existe el cliente, no hace nada
     * @param id long
     */
    void updateById(long id);

    /**
     * Agrega un cliente
     * @param customer Customer
     */
    boolean save(Customer customer);

    /**
     * Elimina un cliente por su id dada
     * Si no existe el cliente, no hace nada
     * @param id long
     */
    void removeById(long id);

    /**
     * Elimina todos los clientes
     * @return boolean
     */
    boolean removeAll();
}