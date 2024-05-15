package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReporteMonetario extends Parqueadero {
    private int año;
    private int mes;

    public ReporteMonetario(int filas, int columnas, int tarifaMotoH, int tarifaMotoC, int tarifaCarro, Collection<Vehiculo>, int mes, int año){
    super(filas,columnas,tarifaMotoH,tarifaMotoC,tarifaCarro, Collection<Vehiculo>);
    this.año=año;
    this.mes=mes;
    }

    public List<Double> registrarDineroDiario(Collection<Vehiculo> vehiculos, int mes, int año){
        List<Double> ingresosDelDia=new ArrayList<>();
        for (Vehiculo vehiculo:vehiculos){
            ingresosDelDia.add(vehiculo.calcularCosto(fecha));
        }
        return Collections.unmodifiableList(ingresosDelDia);
    }

    public double calcularDineroDiario(List<Double> ingresosDelDia){
        double calcularDineroDiario=0.0;

        for (double costo: ingresosDelDia){
            calcularDineroDiario+=costo;
        }
        return calcularDineroDiario;
    }

    public List<Double> registrarDineroMensual(Collection<Vehiculo> vehiculos, int mes, int año) {
        List<Double> ingresosDelMes = new ArrayList<>();
        for (int dia = 1; dia <= Parqueadero.diasEnMes(mes, año); dia++) {
            LocalDateTime fecha = LocalDateTime.of(año, mes, dia, 0, 0);
            List<Double> costosPorDia = crearListaCostosPorDia(vehiculos, fecha);
            double sumaDineroDiario = calcularSumaCostosPorDia(costosPorDia);
            costosPorMes.add(sumaDineroDiario);
        }
        return Collections.unmodifiableList(ingresosDelMes);
    }

    public double calcularCostoTotalMes(Collection<Vehiculo> vehiculos, int mes, int año) {
        double calcularDineroMensual = 0.0;
        List<Double> costosPorMes = crearListaCostosPorMes(vehiculos, mes, año);
        for (double costoPorDia : costosPorMes) {
            calcularDineroMensual += costoPorDia;
        }
        return calcularDineroMensual;
}
