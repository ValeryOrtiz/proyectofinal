package co.edu.uniquindio.poo;
import java.util.ArrayList;
import java.util.List;

//clase para registrar vehiculos
public class Registro extends Parqueadero {
    private final List<Vehiculo> vehiculosRegistrados;

    public Registro() {
        vehiculosRegistrados = new ArrayList<>();
    }

    //para agregar vehiculos al registro
    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculosRegistrados.add(vehiculo);
    }

    //para crear la lista de ese registro
    public List<Vehiculo> getVehiculosRegistrados() {
        return vehiculosRegistrados;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
    }
}
