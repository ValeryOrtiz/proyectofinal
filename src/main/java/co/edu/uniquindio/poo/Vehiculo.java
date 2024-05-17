package co.edu.uniquindio.poo;

import java.time.LocalDateTime;


// Clase para representar un Vehículo
class Vehiculo {
    private String placa;
    private String modelo;
    private String propietario;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    // Constructor
    public Vehiculo(String placa, String modelo, String propietario, LocalDateTime fechaEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
        this.fechaEntrada = fechaEntrada;
    }

    // Constructor con fecha de entrada y salida
    public Vehiculo(String placa, String modelo, String propietario, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        this(placa, modelo, propietario, fechaEntrada);
        this.fechaSalida = fechaSalida;
    }

    // Getters y setters
    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPropietario() {
        return propietario;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}