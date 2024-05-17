package co.edu.uniquindio.poo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class MotoTest {

    @Test
    public void crearMoto() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 18, 10, 0);
        Moto moto = new Moto("ABC123", "Honda", "Juan", fechaEntrada, 150, TipoMoto.CLASICA);

        assertEquals("ABC123", moto.getPlaca());
        assertEquals("Honda", moto.getModelo());
        assertEquals("Juan", moto.getPropietario());
        assertEquals(fechaEntrada, moto.getFechaEntrada());
        assertEquals(150, moto.getVelocidadMaxima());
        assertEquals(TipoMoto.CLASICA, moto.getTipo());
    }

    @Test
    public void crearMotoConFechaSalida() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 18, 10, 0);
        LocalDateTime fechaSalida = LocalDateTime.of(2024, 5, 18, 15, 0);
        Moto moto = new Moto("XYZ456", "Yamaha", "Ana", fechaEntrada, fechaSalida, 180, TipoMoto.HIBRIDA);

        assertEquals("XYZ456", moto.getPlaca());
        assertEquals("Yamaha", moto.getModelo());
        assertEquals("Ana", moto.getPropietario());
        assertEquals(fechaEntrada, moto.getFechaEntrada());
        assertEquals(fechaSalida, moto.getFechaSalida());
        assertEquals(180, moto.getVelocidadMaxima());
        assertEquals(TipoMoto.HIBRIDA, moto.getTipo());
    }

    @Test
    public void establecerFechaSalidaMoto() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 18, 10, 0);
        Moto moto = new Moto("DEF789", "Suzuki", "Maria", fechaEntrada, 160, TipoMoto.CLASICA);

        LocalDateTime fechaSalida = LocalDateTime.of(2024, 5, 18, 16, 0);
        moto.setFechaSalida(fechaSalida);

        assertEquals(fechaSalida, moto.getFechaSalida());
    }
}


