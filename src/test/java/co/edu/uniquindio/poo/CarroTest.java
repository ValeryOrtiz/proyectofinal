/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

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
    public void PlacaRepetida2() {
        LOG.info("Iniciando test PlacaRepetida2");

        Vehiculo vehiculo = new Vehiculo("carro");
        Carro carro1 = new Carro("TAB13G","AUDI XX","DAVID FERNANDEZ HOGUERA");
        Carro carro2 = new Carro("TAB13G","SUZUKI FOX","VALERY ANDREA MARTINEZ");

        vehiculo.carroAdd(carro1);
        assertThrows(Throwable.class, () -> vehiculo.carroAdd(carro2));

        LOG.info("Finalizando test PlacaRepetida2");
    }

}