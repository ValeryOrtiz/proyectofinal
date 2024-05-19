package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReporteMonetario extends Parqueadero {


    public ReporteMonetario() {

        super();
    }

    public static List<Double> registrarDineroDiario(Vehiculo[] vehiculos, Parqueadero parqueadero) {
        List<Double> ingresosDelDia = new ArrayList<>();
        List<List<Vehiculo>> registroVehiculo = getRegistroVehiculo();
        for (Vehiculo vehiculo : vehiculos) {
            ingresosDelDia.add(parqueadero.calcularCosto(vehiculo));
        }
        return Collections.unmodifiableList(ingresosDelDia);
    }

    public static double calcularDineroDiario(List<Double> ingresosDelDia) {
        double calcularDineroDiario = 0.0;

        for (double costo : ingresosDelDia) {
            calcularDineroDiario += costo;
        }
        return calcularDineroDiario;
    }

    public static List<Double> registrarDineroMensual(Vehiculo[] vehiculos, int mesActual, int anoActual, Parqueadero parqueadero) {
        List<Double> ingresosDelMes = new ArrayList<>();
        List<List<Vehiculo>> registroVehiculo = getRegistroVehiculo();
        for (int dia = 1; dia <= Parqueadero.diasEnMes(mesActual, anoActual); dia++) {
            LocalDateTime fecha = LocalDateTime.of(anoActual, mesActual, dia, 0, 0);
            List<Double> costosPorDia = registrarDineroDiario(vehiculos, parqueadero);
            double calcularDineroDiario = calcularDineroDiario(costosPorDia);
            ingresosDelMes.add(calcularDineroDiario);
        }
        return Collections.unmodifiableList(ingresosDelMes);
    }

    public double calcularDineroMensual(Vehiculo[] vehiculos, int mesActual, int anoActual, Parqueadero parqueadero) {

        List<List<Vehiculo>> registroVehiculo = getRegistroVehiculo();
        double calcularDineroMensual = 0.0;
        List<Double> costosPorMes = ReporteMonetario.registrarDineroMensual(vehiculos, mesActual, anoActual, parqueadero);
        for (double costoPorDia : costosPorMes) {
            calcularDineroMensual += costoPorDia;
        }
        return calcularDineroMensual;
    }
}