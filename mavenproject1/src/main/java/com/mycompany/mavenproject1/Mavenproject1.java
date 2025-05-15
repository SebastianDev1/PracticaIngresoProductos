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
        ArrayList<Productos> productos = cargarDesdeJSON(); // Cargar productos previos

        int opcion = -1;

        while (opcion != 4) {
            System.out.println("\n-------------------- MENU --------------------");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos ingresados");
            System.out.println("3. Modificar productos (no implementado)");
            System.out.println("4. Salir");
            System.out.print("Elija una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (opcion) {
                        case 1 -> {
                            try {
                                String codigo;
                                String nombre;
                                double precio;
                                int idCat;
                                String nombreCat;

                                System.out.print("Código del producto: ");
                                codigo = scanner.nextLine();

                                // Validación de nombre
                                System.out.print("Nombre del producto: ");
                                nombre = scanner.nextLine();
                                if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                                    throw new IllegalArgumentException("El nombre del producto debe contener solo letras.");
                                }

                                // Validación de precio
                                System.out.print("Precio del producto: ");
                                String precioStr = scanner.nextLine();
                                if (!precioStr.matches("\\d+(\\.\\d{1,2})?")) {
                                    throw new IllegalArgumentException("El precio debe ser un número válido.");
                                }
                                precio = Double.parseDouble(precioStr);
                                if (precio <= 0) {
                                    throw new IllegalArgumentException("El precio debe ser mayor a 0.");
                                }

                                // Validación de ID categoría
                                System.out.print("ID de la categoría: ");
                                String idStr = scanner.nextLine();
                                if (!idStr.matches("\\d+")) {
                                    throw new IllegalArgumentException("El ID debe ser un número entero.");
                                }
                                idCat = Integer.parseInt(idStr);
                                if (idCat <= 0) {
                                    throw new IllegalArgumentException("El ID de la categoría debe ser mayor que 0.");
                                }

                                // Validación de nombre categoría
                                System.out.print("Nombre de la categoría: ");
                                nombreCat = scanner.nextLine();
                                if (!nombreCat.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                                    throw new IllegalArgumentException("El nombre de la categoría debe contener solo letras.");
                                }

                                Categoria categoria = new Categoria(idCat, nombreCat);
                                Productos nuevoProducto = new Productos(codigo, nombre, precio, categoria);
                                productos.add(nuevoProducto);
                                System.out.println("Producto agregado.");

                                guardarCSV(productos);
                                guardarJSON(productos);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error inesperado: " + e.getMessage());
                            }
                        }

                        case 2 -> {
                            if (productos.isEmpty()) {
                                System.out.println("No hay productos registrados.");
                            } else {
                                System.out.println("\n--- LISTA DE PRODUCTOS ---");
                                for (Productos p : productos) {
                                    p.mostrarResumen();
                                }
                            }
                        }

                        case 3 -> System.out.println("Función de modificación no implementada.");
                        case 4 -> System.out.println("Programa finalizado.");
                        default -> System.out.println("Opción inválida. Intenta otra vez.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine();
                }
            } else {
                System.out.println("Entrada no válida. Debes ingresar un número.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void guardarCSV(ArrayList<Productos> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_CSV))) {
            writer.write("Codigo,Nombre,Precio,CategoriaID,CategoriaNombre\n");
            for (Productos p : productos) {
                writer.write(p.getCodigo() + "," + p.getNombre() + "," + p.getPrecio() + "," +
                        p.getCategoria().getId() + "," + p.getCategoria().getNombre() + "\n");
            }
            System.out.println("Archivo CSV guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar CSV: " + e.getMessage());
        }
    }

    private static void guardarJSON(ArrayList<Productos> productos) {
        try (FileWriter writer = new FileWriter(RUTA_JSON)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(productos, writer);
            System.out.println("Archivo JSON guardado correctamente.");
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
                System.out.println("Productos cargados desde archivo JSON.");
            } catch (Exception e) {
                System.out.println("No se pudo cargar JSON existente: " + e.getMessage());
            }
        }

        return productos;
    }
}
