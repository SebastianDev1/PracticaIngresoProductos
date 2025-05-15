/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;
import java.util.ArrayList;
import java.util.Scanner;

public class Mavenproject1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Productos> productos = new ArrayList<>();
        int opcion = -1;

        while (opcion != 3) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos ingresados");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  // limpiar buffer

                try {
                    switch (opcion) {
                        case 1:
                            System.out.print("Código del producto: ");
                            String codigo = scanner.nextLine();

                            System.out.print("Nombre del producto: ");
                            String nombre = scanner.nextLine();

                            System.out.print("Precio del producto: ");
                            if (!scanner.hasNextDouble()) {
                                System.out.println("❌ Precio inválido. Usa solo números.");
                                scanner.nextLine(); // limpiar
                                break;
                            }
                            double precio = scanner.nextDouble();
                            scanner.nextLine(); // limpiar

                            System.out.print("ID de la categoría: ");
                            if (!scanner.hasNextInt()) {
                                System.out.println("❌ ID inválido. Debe ser un número.");
                                scanner.nextLine(); // limpiar
                                break;
                            }
                            int idCat = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Nombre de la categoría: ");
                            String nombreCat = scanner.nextLine();

                            Categoria categoria = new Categoria(idCat, nombreCat);
                            Productos nuevoProducto = new Productos(codigo, nombre, precio, categoria);
                            productos.add(nuevoProducto);
                            System.out.println("✅ Producto agregado.");
                            break;

                        case 2:
                            if (productos.isEmpty()) {
                                System.out.println("⚠️ No hay productos registrados.");
                            } else {
                                System.out.println("\n--- LISTA DE PRODUCTOS ---");
                                for (Productos p : productos) {
                                    p.mostrarResumen();
                                }
                            }
                            break;

                        case 3:
                            System.out.println("👋 Programa finalizado.");
                            break;

                        default:
                            System.out.println("❌ Opción inválida. Intenta otra vez.");
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Error: " + e.getMessage());
                    scanner.nextLine(); // limpiar entrada
                }
            } else {
                System.out.println("❌ Entrada no válida. Debes ingresar un número.");
                scanner.nextLine(); // limpiar entrada incorrecta
            }
        }

        scanner.close();
    }
}
