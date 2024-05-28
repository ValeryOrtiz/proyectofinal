package co.edu.uniquindio.poo;

import java.time.*;
import java.util.*;

public class Parqueadero {
    private int filas;
    private int columnas;
    private static int tarifaMotoH;
    private static int tarifaMotoC;
    private static int tarifaCarro;
    private int mesActual;
    private int anoActual;
    private static List<List<Vehiculo>> registroVehiculo;

    public Parqueadero(int filas, int columnas, int tarifaMotoH, int tarifaMotoC, int tarifaCarro) {
        this.filas = filas;
        this.columnas = columnas;
        Parqueadero.tarifaMotoH = tarifaMotoH;
        Parqueadero.tarifaMotoC = tarifaMotoC;
        Parqueadero.tarifaCarro = tarifaCarro;

        this.mesActual = LocalDateTime.now().getMonthValue();
        this.anoActual = LocalDateTime.now().getYear();

        registroVehiculo = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            List<Vehiculo> fila = new ArrayList<>(columnas);
            for (int j = 0; j < columnas; j++) {
                fila.add(null);
            }
            registroVehiculo.add(fila);
        }
    }

    public Parqueadero() {
    }

    private int pedirEntero(String mensaje, Scanner input) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.println(mensaje);
                valor = Integer.parseInt(input.nextLine());
                if (valor < 0) {
                    System.out.println("Por favor, ingrese un número positivo.");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            }
        }
        return valor;
    }

    //comprobar una posicion valida en el parqueadero
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

    //existencia de un vehiculo dentro del parqueadero
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

    //para registrar un vehiculo en el parqueadero con toda su info
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

    //para saber la diferencia entre la fecha de entrada y de salida
    public static long obtenerDiferenciaHoras(Vehiculo vehiculo){
        Duration duracion = Duration.between(vehiculo.getFechaEntrada(), vehiculo.getFechaSalida());
        return duracion.toHours();
    }

    //para calcular cuanto debe pagar el vehiculo segun el tiempo en el parqueadero
    public static double calcularCosto(Vehiculo vehiculo) {
        if (vehiculo.getFechaEntrada() == null) {
            throw new IllegalArgumentException("La fecha de entrada no está inicializada");
        }
        if (vehiculo.getFechaSalida() == null) {
            throw new IllegalArgumentException("La fecha de salida no está inicializada");
        }
        if (vehiculo instanceof Carro) {
            return obtenerDiferenciaHoras(vehiculo) * tarifaCarro;
        } else if (vehiculo instanceof Moto) {
            TipoMoto tipoMoto = ((Moto) vehiculo).getTipo();
            if (tipoMoto == TipoMoto.CLASICA) {
                return obtenerDiferenciaHoras(vehiculo) * tarifaMotoC;
            } else {
                return obtenerDiferenciaHoras(vehiculo) * tarifaMotoH;
            }
        } else {
            throw new IllegalArgumentException("Tipo de vehículo no soportado");
        }
    }



    //para saber en que mes y año estamos
    public static int diasEnMes(int mesActual, int anoActual){
        return YearMonth.of(anoActual,mesActual).lengthOfMonth();
    }

    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (List<Vehiculo> fila : registroVehiculo) {
            for (Vehiculo vehiculo : fila) {
                if (vehiculo != null) {
                    vehiculos.add(vehiculo);
                }
            }
        }
        return vehiculos;
    }

    //para agregar un vehiculo al parqueadero
    public void add(int fila, int columna, Vehiculo vehiculo) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        if (verificarPuesto(fila, columna)) {
            System.out.println("El puesto en [" + fila + "][" + columna + "] ya está ocupado.");
        } else if (verificarVehiculoExiste(vehiculo)) {
            System.out.println("El vehículo con placa " + vehiculo.getPlaca() + " ya está registrado en el parqueadero.");
        } else {
            registroVehiculo.get(fila).set(columna, vehiculo);
            System.out.println("Vehículo agregado en la posición [" + fila + "][" + columna + "].");
        }
    }
    //para ingresar informacion de la salida de un vehiculo segun su placa
    public void registrarSalidaVehiculo(String placa, LocalDate fechaSalida, LocalTime horaSalida) {
        Vehiculo vehiculo = buscarVehiculoPorPlaca(placa);
        if (vehiculo != null) {
            LocalDateTime fechaSalidaDateTime = fechaSalida.atTime(horaSalida);
            vehiculo.setFechaSalida(fechaSalidaDateTime);
        } else {
            throw new IllegalArgumentException("No se encontró un vehículo con la placa " + placa);
        }
    }
    //para buscar un vehiculo registrado segun su placa
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (List<Vehiculo> fila : registroVehiculo) {
            for (Vehiculo vehiculo : fila) {
                if (vehiculo != null && vehiculo.getPlaca().equals(placa)) {
                    return vehiculo;
                }
            }
        }
        return null;
    }
}
