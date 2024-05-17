/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class VehiculoTest {

    @Test
    public void crearVehiculo() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 18, 10, 0);
        Vehiculo vehiculo = new Vehiculo("ABC123", "Toyota", "Juan", fechaEntrada);

        assertEquals("ABC123", vehiculo.getPlaca());
        assertEquals("Toyota", vehiculo.getModelo());
        assertEquals("Juan", vehiculo.getPropietario());
        assertEquals(fechaEntrada, vehiculo.getFechaEntrada());
    }

    @Test
    public void crearVehiculoConFechaSalida() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 18, 10, 0);
        LocalDateTime fechaSalida = LocalDateTime.of(2024, 5, 18, 15, 0);
        Vehiculo vehiculo = new Vehiculo("XYZ456", "Honda", "Ana", fechaEntrada, fechaSalida);

        assertEquals("XYZ456", vehiculo.getPlaca());
        assertEquals("Honda", vehiculo.getModelo());
        assertEquals("Ana", vehiculo.getPropietario());
        assertEquals(fechaEntrada, vehiculo.getFechaEntrada());
        assertEquals(fechaSalida, vehiculo.getFechaSalida());
    }

    @Test
    public void establecerFechaSalida() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 18, 10, 0);
        Vehiculo vehiculo = new Vehiculo("DEF789", "Chevrolet", "Maria", fechaEntrada);

        LocalDateTime fechaSalida = LocalDateTime.of(2024, 5, 18, 16, 0);
        vehiculo.setFechaSalida(fechaSalida);

        assertEquals(fechaSalida, vehiculo.getFechaSalida());
    }
}


        
    


