package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReporteMonetario extends Parqueadero {

    public ReporteMonetario(int filas, int columnas, int tarifaMotoH, int tarifaMotoC, int tarifaCarro) {
        super(filas, columnas, tarifaMotoH, tarifaMotoC, tarifaCarro);
    }

    public List<Double> registrarDineroDiario(LocalDateTime dia) {
        List<Double> ingresosDelDia = new ArrayList<>();
        for (Vehiculo vehiculo : getVehiculos()) {
            if (vehiculo.getFechaSalida() != null && vehiculo.getFechaSalida().toLocalDate().equals(dia.toLocalDate())) {
                ingresosDelDia.add(calcularCosto(vehiculo));
            }
        }
        return Collections.unmodifiableList(ingresosDelDia);
    }

    public double calcularDineroDiario(LocalDateTime dia) {
        double calcularDineroDiario = 0.0;
        List<Double> ingresosDelDia = registrarDineroDiario(dia);
        for (double costo : ingresosDelDia) {
            calcularDineroDiario += costo;
        }
        return calcularDineroDiario;
    }

    public List<Double> registrarDineroMensual(int mesActual, int anoActual) {
        List<Double> ingresosDelMes = new ArrayList<>();
        int diasEnMes = Parqueadero.diasEnMes(mesActual, anoActual);
        for (int dia = 1; dia <= diasEnMes; dia++) {
            LocalDateTime fecha = LocalDateTime.of(anoActual, mesActual, dia, 0, 0);
            double calcularDineroDiario = calcularDineroDiario(fecha);
            ingresosDelMes.add(calcularDineroDiario);
        }
        return Collections.unmodifiableList(ingresosDelMes);
    }

    public double calcularDineroMensual(int mesActual, int anoActual) {
        double calcularDineroMensual = 0.0;
        List<Double> costosPorMes = registrarDineroMensual(mesActual, anoActual);
        for (double costoPorDia : costosPorMes) {
            calcularDineroMensual += costoPorDia;
        }
        return calcularDineroMensual;
    }
}
