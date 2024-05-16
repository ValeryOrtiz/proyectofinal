package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReporteMonetario extends Parqueadero {
    private int ano;
    private int mes;

    public ReporteMonetario( int mes, int ano){
    this.ano=ano;
    this.mes=mes;
    }

    public static List<Double> registrarDineroDiario(Vehiculo[] vehiculos){
        List<Double> ingresosDelDia=new ArrayList<>();
        List<List<Vehiculo>> registroVehiculo = getRegistroVehiculo();
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

    public static List<Double> registrarDineroMensual(Vehiculo[] vehiculos,int mes, int ano) {
        List<Double> ingresosDelMes = new ArrayList<>();
        List<List<Vehiculo>> registroVehiculo = getRegistroVehiculo();
        for (int dia = 1; dia <= Parqueadero.diasEnMes(mes, ano); dia++) {
            LocalDateTime fecha = LocalDateTime.of(ano, mes, dia, 0, 0);
            List<Double> costosPorDia = registrarDineroDiario(vehiculos, fecha);
            double calcularDineroDiario = calcularDineroDiario(costosPorDia);
            ingresosDelMes.add(calcularDineroDiario);
        }
        return Collections.unmodifiableList(ingresosDelMes);
    }

    public double calcularDineroMensual(Vehiculo[] vehiculos, int mes, int ano) {

        List<List<Vehiculo>> registroVehiculo = getRegistroVehiculo();
        double calcularDineroMensual = 0.0;
        List<Double> costosPorMes = registrarDineroMensual(vehiculos, mes, ano);
        for (double costoPorDia : costosPorMes) {
            calcularDineroMensual += costoPorDia;
        }
        return calcularDineroMensual;
}

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
