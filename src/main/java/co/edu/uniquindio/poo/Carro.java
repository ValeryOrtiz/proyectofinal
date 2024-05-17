package co.edu.uniquindio.poo;
import java.time.LocalDateTime;

class Carro extends Vehiculo {
    // Constructor
    public Carro(String placa, String modelo, String propietario, LocalDateTime fechaEntrada) {
        super(placa, modelo, propietario, fechaEntrada);
    }

    // Constructor con fecha de entrada y salida
    public Carro(String placa, String modelo, String propietario, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        super(placa, modelo, propietario, fechaEntrada, fechaSalida);
    }
}