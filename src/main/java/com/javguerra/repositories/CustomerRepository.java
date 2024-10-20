package main.java.com.javguerra.repositories;

import main.java.com.javguerra.entities.Customer;

import java.util.Optional;
import java.util.ArrayList;

/*
Repositorio de objetos Customer
Equivale a una tabla Customer en base de datos
No usa base de datos, usa un ArrayList en memoria
Tiene las operaciones CRUD sobre Customer.
 */
public interface CustomerRepository {

    /**
     * Devuelve una lista de usuarios
     * @return ArrayList<Customer>
     */
    ArrayList<Customer> findAll();

    /**
     * Devuelve un usuario por id
     * @param id Long
     * @return Optional<Customer>
     */
     Optional<Customer> findById(Long id);

    /**
     * Agrega un usuario
     * @param customer Customer
     * @return boolean
     */
    boolean save(Customer customer);

    /**
     * Actualiza un usuario
     * @param id Long
     * @param customer Customer
     */
    void update(Long id, Customer customer);

    /**
     * Elimina un usuario
     * @param id Long
     * @return boolean
     */
    boolean removeById(Long id);

    /**
     * Elimina todos los usuarios
     */
    void removeAll();

}
