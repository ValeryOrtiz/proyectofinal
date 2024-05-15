package co.edu.uniquindio.poo;

// Clase Moto que hereda de Vehiculo
class Moto extends Vehiculo {
    private TipoMoto tipo;

    // Constructor
    public Moto(String placa, String modelo, String propietario, TipoMoto tipo) {
        super(placa, modelo,propietario);
        this.tipo = tipo;
    }

    // Getter y Setter para tipo
    public TipoMoto getTipo() {
        return tipo;
    }

    public void setTipo(TipoMoto tipo) {
        this.tipo = tipo;
    }
    
}
