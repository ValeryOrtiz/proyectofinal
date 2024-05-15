package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReporteMonetario extends Parqueadero {
    private int año;
    private int mes;

    public ReporteMonetario(int filas, int columnas, int tarifaMotoH, int tarifaMotoC, int tarifaCarro, int mes, Collection<Vehiculo>, int año){
    super(filas,columnas,tarifaMotoH,tarifaMotoC,tarifaCarro, List<Vehiculo>);
    this.año=año;
    this.mes=mes;
    }



    public static List<Double> registrarDineroDiario(Collection<Vehiculo> vehiculos){
        List<Double> ingresosDelDia=new ArrayList<>();
        for (Vehiculo vehiculo:vehiculos){
            ingresosDelDia.add(vehiculo.calcularCosto());
        }
        return Collections.unmodifiableList(ingresosDelDia);
    }

    public static double calcularDineroDiario(List<Double> ingresosDelDia){
        double calcularDineroDiario=0.0;

        for (double costo: ingresosDelDia){
            calcularDineroDiario+=costo;
        }
        return calcularDineroDiario;
    }

    public static List<Double> registrarDineroMensual(Collection<Vehiculo> vehiculos, int mes, int año) {
        List<Double> ingresosDelMes = new ArrayList<>();
        for (int dia = 1; dia <= Parqueadero.diasEnMes(mes, año); dia++) {
            LocalDateTime fecha = LocalDateTime.of(año, mes, dia, 0, 0);
            List<Double> costosPorDia = registrarDineroDiario(vehiculos, fecha);
            double calcularDineroDiario = calcularDineroDiario(costosPorDia);
            ingresosDelMes.add(calcularDineroDiario);
        }
        return Collections.unmodifiableList(ingresosDelMes);
    }

    public double calcularDineroMensual(Collection<Vehiculo> vehiculos, int mes, int año) {
        double calcularDineroMensual = 0.0;
        List<Double> costosPorMes = registrarDineroMensual(vehiculos, mes, año);
        for (double costoPorDia : costosPorMes) {
            calcularDineroMensual += costoPorDia;
        }
        return calcularDineroMensual;
}

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
