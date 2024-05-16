package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class CarroTest {
    private static final Logger LOG = Logger.getLogger(AppTest.class.getName());

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        LOG.info("Iniciado test shouldAnswerWithTrue");
        assertTrue(true);
        LOG.info("Finalizando test shouldAnswerWithTrue");
    }
    @Test
    public void testPlacaUnica() {
        // Crear dos carros con la misma placa
        Carro carro1 = new Carro("TAB23G", "Corolla", "ANDRES");
        Carro carro2 = new Carro("TAB13G", "Civic","FELIPE");

        // Verificar que las placas sean distintas
        assertNotEquals(carro1.getPlaca(), carro2.getPlaca());
    }

    @Test
    public void testPlacaRepetida() {
        // Crear dos carros con placas diferentes
        Carro carro1 = new Carro("ABC123", "Corolla", "CARLOS");
        Carro carro2 = new Carro("CB234", "Civic", "ANTONIO");

        // Asignar la misma placa al segundo carro
        carro2.setPlaca("ABC123");

        // Verificar que la placa del segundo carro sea igual a la del primero
        assertEquals(carro1.getPlaca(), carro2.getPlaca());
    }

}