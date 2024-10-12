package main.java.com.javguerra;

import main.java.com.javguerra.entities.Customer;
import main.java.com.javguerra.repositories.CustomerRepositoryImpl;
import main.java.com.javguerra.services.CustomerService;
import main.java.com.javguerra.controllers.CustomerController;

import java.util.ArrayList;

import static main.java.com.javguerra.utils.ConsoleInput.*;

/**
 * Aplicación CRUD Customer
 * @author Javier Guerra
 * @version 1.0
 * @since 2024-10-12
 */
public class Main {

    private static CustomerController controller;

    /**
     * Aplicación
     * @param args
     */
    public static void main(String[] args) {
        // Crea el repositorio de clientes, e inicializa
        // el servicio y el controlador de la aplicación
        controller =
            new CustomerController(
                new CustomerService(
                    new CustomerRepositoryImpl(
                        getCustomers(true)
                    )
                )
            );
        // Llama al menú de la aplicación
        selectOptions();
        // Termina la aplicación
        System.out.println("La aplicación ha finalizado.");
        System.exit(0);
    }

    /**
     * Devuelve una lista de usuarios
     * @return ArrayList<Customer>
     */
    private static ArrayList<Customer> getCustomers(boolean withData) {
        ArrayList<Customer> customers = new ArrayList<>();
        if (withData) {
            customers.add(new Customer(1L, "c1", "garcia", "c1@gmail.com", 30));
            customers.add(new Customer(2L, "c2", "perez", "c2@gmail.com", 40));
            customers.add(new Customer(3L, "c3", "melgar", "c3@gmail.com", 50));
        }
         return customers;
    }

    /**
     * Menú de opciones
     */
    private static void selectOptions() {
        long totalOpts = 6L;
        String optMsg = "Seleccione una opción (0-6): ";
        String menuList = """
        -------------------
           CRUD Customer
        -------------------
        1. Listar clientes
        2. Mostrar un cliente
        3. Crear un nuevo cliente
        4. Actualizar un cliente
        5. Eliminar un cliente
        6. Eliminar todos los clientes
        0. Salir
        """;
        byte opt;

        while (true) {
            clearConsole();
            System.out.println(menuList);
            opt = getLongIntPosByRange(optMsg, 0L, totalOpts).byteValue();

            if (opt == 0) break;

            switch (opt) {
                case 1 -> controller.listCustomers();
                case 2 -> controller.listCustomerById();
                case 3 -> controller.newCustomer();
                case 4 -> controller.updateCustomerById();
                case 5 -> controller.deleteCustomerById();
                case 6 -> controller.deleteAllCustomers();
            }

            System.out.println("Pulse <Intro> para continuar.");
            getEnter();
        }

        closeScanner();
    }
}
