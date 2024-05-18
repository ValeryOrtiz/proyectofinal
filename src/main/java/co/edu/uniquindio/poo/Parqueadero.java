package co.edu.uniquindio.poo;

import java.time.*;
import java.util.*;

public class Parqueadero {
    private int filas;
    private int columnas;
    private int tarifaMotoH;
    private int tarifaMotoC;
    private int tarifaCarro;
    private int mesActual;
    private int anoActual;
    private List<List<Vehiculo>> registroVehiculo;

    public Parqueadero(){
        LocalDate fechaActual = LocalDate.now();

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

        this.mesActual = fechaActual.getMonthValue();
        this.anoActual = fechaActual.getYear();

        registroVehiculo = new ArrayList<>();

        for (int i = 0; i < filas; i++) {
            List<Vehiculo> fila = new ArrayList<>(Collections.nCopies(columnas,null));
            registroVehiculo.add(fila);
        }
    }

    public boolean verificarPuesto(int fila, int columna){
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas){
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        return registroVehiculo.get(fila).get(columna) != null;
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

    public static List<List<Vehiculo>> getRegistroVehiculo() {
        return registroVehiculo;
    }

    public void setRegistroVehiculo(List<List<Vehiculo>> registroVehiculo) {
        this.registroVehiculo = registroVehiculo;
    }

    public boolean verificarVehiculoExiste(Vehiculo vehiculo) {
        for (List<Vehiculo> fila : registroVehiculo) {
            for (Vehiculo v : fila) {
                if (v != null && v.getPlaca().equals(vehiculo.getPlaca())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void registrarVehiculo(int fila, int columna, Vehiculo vehiculo) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || verificarPuesto(fila, columna) || verificarVehiculoExiste(vehiculo)) {
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

    public long obtenerDiferenciaHoras(Vehiculo vehiculo){
        Duration duracion = Duration.between(vehiculo.getFechaEntrada(), vehiculo.getFechaSalida());
        return duracion.toHours();
    }

    public double calcularCosto(Vehiculo vehiculo){
        if(vehiculo instanceof Carro){
            return (double) (obtenerDiferenciaHoras(vehiculo) * tarifaCarro);
        } else if (vehiculo instanceof Moto) {
            if (vehiculo.getTipo() == TipoMoto.CLASICA){
                return (double) (obtenerDiferenciaHoras(vehiculo) * tarifaMotoC);
            } else {
                return (double) (obtenerDiferenciaHoras(vehiculo) * tarifaMotoH);
            }
        } else {
            throw new IllegalArgumentException("Tipo de vehículo no soportado");
        }
    }

    public static int diasEnMes(int mesActual, int anoActual){
        return YearMonth.of(anoActual,mesActual).lengthOfMonth();
    }
}
