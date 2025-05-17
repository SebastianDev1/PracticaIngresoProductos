package com.mycompany.mavenproject1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Mavenproject1 {
    private static final String RUTA_CSV = "productos.csv";
    private static final String RUTA_JSON = "productos.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Productos> productos = cargarDesdeJSON();

        int opcion = -1;

        while (opcion != 4) {
            System.out.println("\n-------------------- MENU --------------------");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos ingresados");
            System.out.println("3. Buscar producto por código (verifica duplicado)");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> agregarProducto(scanner, productos);
                    case 2 -> mostrarProductos(productos);
                    case 3 -> buscarDuplicado(scanner, productos);
                    case 4 -> System.out.println("Programa finalizado.");
                    default -> System.out.println("Opción inválida. Intenta otra vez.");
                }
            } else {
                System.out.println("Entrada no válida. Debes ingresar un número.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void agregarProducto(Scanner scanner, ArrayList<Productos> productos) {
        try {
            System.out.print("Código del producto: ");
            String codigo = scanner.nextLine();

            // Verificación de código duplicado
            for (Productos p : productos) {
                if (p.getCodigo().equalsIgnoreCase(codigo)) {
                    throw new DatosDuplicadosException("El código ya existe en la base de datos.");
                }
            }

            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();
            if (nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío.");

            System.out.print("Precio del producto: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Correo del proveedor: ");
            String correo = scanner.nextLine();

            System.out.print("ID de la categoría: ");
            int idCat = Integer.parseInt(scanner.nextLine());

            System.out.print("Nombre de la categoría: ");
            String nombreCat = scanner.nextLine();

            Categoria categoria = new Categoria(idCat, nombreCat);
            Productos nuevoProducto = new Productos(codigo, nombre, precio, categoria, correo);

            productos.add(nuevoProducto);
            System.out.println("Producto agregado correctamente........");

            guardarCSV(productos);
            guardarJSON(productos);

        } catch (FormatoCorreoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PrecioInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DatosDuplicadosException e) {
            System.out.println("Duplicado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error de argumento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static void mostrarProductos(ArrayList<Productos> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (Productos p : productos) {
                p.mostrarResumen();
            }
        }
    }

    private static void buscarDuplicado(Scanner scanner, ArrayList<Productos> productos) {
        System.out.print("Ingrese código a buscar: ");
        String codigo = scanner.nextLine();

        boolean encontrado = false;
        for (Productos p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("Código ya existe:");
                p.mostrarResumen();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Código disponible. No hay duplicados.");
        }
    }

    private static void guardarCSV(ArrayList<Productos> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_CSV))) {
            writer.write("Codigo,Nombre,Precio,CategoriaID,CategoriaNombre,Correo\n");
            for (Productos p : productos) {
                writer.write(p.getCodigo() + "," + p.getNombre() + "," + p.getPrecio() + "," +
                        p.getCategoria().getId() + "," + p.getCategoria().getNombre() + "," + p.getCorreo() + "\n");
            }
            System.out.println("CSV guardado correctamente!!.....");
        } catch (IOException e) {
            System.out.println("Error al guardar CSV: " + e.getMessage());
        }
    }

    private static void guardarJSON(ArrayList<Productos> productos) {
        try (FileWriter writer = new FileWriter(RUTA_JSON)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(productos, writer);
            System.out.println("JSON guardado correctamente!!..........");
        } catch (IOException e) {
            System.out.println("Error al guardar JSON: " + e.getMessage());
        }
    }

    private static ArrayList<Productos> cargarDesdeJSON() {
        ArrayList<Productos> productos = new ArrayList<>();
        File archivo = new File(RUTA_JSON);

        if (archivo.exists()) {
            try (Reader reader = new FileReader(archivo)) {
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<ArrayList<Productos>>() {}.getType();
                productos = gson.fromJson(reader, tipoLista);
                System.out.println("Productos cargados desde JSON correctamente.");
            } catch (Exception e) {
                System.out.println("No se pudo cargar JSON existente: " + e.getMessage());
            }
        }

        return productos;
    }
}
