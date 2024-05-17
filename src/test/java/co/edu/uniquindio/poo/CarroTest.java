package co.edu.uniquindio.poo;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CarroTest {

    @Test
    public void crearCarro() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 18, 10, 0);
        Carro carro = new Carro("ABC123", "Toyota", "Juan", fechaEntrada);

        assertEquals("ABC123", carro.getPlaca());
        assertEquals("Toyota", carro.getModelo());
        assertEquals("Juan", carro.getPropietario());
        assertEquals(fechaEntrada, carro.getFechaEntrada());
        assertNull(carro.getFechaSalida());
    }
}