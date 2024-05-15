package co.edu.uniquindio.poo;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.nio.channels.ServerSocketChannel;
import java.sql.SQLOutput;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class Parqueadero {
    private int filas;
    private int columnas;
    private int tarifaMotoH;
    private int tarifaMotoC;
    private int tarifaCarro;
    private List<List<Vehiculo>> registroVehiculo;

    public Parqueadero(){
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese el número de filas: ");
        this.filas = Integer.parseInt(input.nextLine());

        System.out.println("Ingrese el número de columnas: ");
        this.columnas = Integer.parseInt(input.nextLine());

        System.out.println("Ingrese la tarifa por hora para una moto híbrida: ");
        this.tarifaMotoH = Integer.parseInt(input.nextLine());

        System.out.println("Ingrese la tarifa por hora para una moto clásica: ");
        this.tarifaMotoC = Integer.parseInt(input.nextLine());

        System.out.println("Ingrese la tarifa por hora para un carro: ");
        this.tarifaCarro = Integer.parseInt(input.nextLine());

        System.out.println("--------------------------------------------------------------");

        registroVehiculo = new ArrayList<>();

        for (int i = 0; i < filas; i++) {
            registroVehiculo.add(new ArrayList<>());
            for (int j = 0; j < columnas; j++) {
                registroVehiculo.add(new ArrayList<>());
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getTarifaMotoH() {
        return tarifaMotoH;
    }

    public void setTarifaMotoH(int tarifaMotoH) {
        this.tarifaMotoH = tarifaMotoH;
    }

    public int getTarifaMotoC() {
        return tarifaMotoC;
    }

    public void setTarifaMotoC(int tarifaMotoC) {
        this.tarifaMotoC = tarifaMotoC;
    }

    public int getTarifaCarro() {
        return tarifaCarro;
    }

    public void setTarifaCarro(int tarifaCarro) {
        this.tarifaCarro = tarifaCarro;
    }

    public Collection<Vehiculo> getRegistroVehiculo() {
        return registroVehiculo;
    }

    public void setRegistroVehiculo(List<List<Vehiculo>> registroVehiculo) {
        this.registroVehiculo = registroVehiculo;
    }

    public void registrarVehiculo(int fila, int columna, Vehiculo vehiculo) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || vehiculo.verificarPuesto((fila-1), (columna-1)) == true || vehiculo.verificarVehiculoExiste() == true) {
            System.out.println("Posición inválida/Puesto ocupado/Vehiculo ya existe.");
        } else {
            registroVehiculo.get(fila).set(columna, vehiculo);
        }
    }

    public void listarVehiculos() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Vehiculo vehiculo = registroVehiculo.get(i).get(j);
                if (vehiculo != null) {
                    System.out.println("Vehículo en [" + i + "][" + j + "]: " + vehiculo);
                } else {
                    System.out.println("Espacio en [" + i + "][" + j + "]: vacío");
                }
            }
        }
    }
}
