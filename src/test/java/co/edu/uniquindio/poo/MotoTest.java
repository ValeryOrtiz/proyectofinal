package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.logging.Logger;

import javax.management.ConstructorParameters;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class MotoTest {
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
    /*Realizo prueba para verificar que la placa de moto no se repita */
    @Test
    public void PlacaRepetida() {
        LOG.info("Iniciando test PlacaRepetida");

        Vehiculo vehiculo = new Vehiculo("Moto");
        Moto moto1 = new Moto("TAB23G","AUDI XX","DAVID FERNANDEZ HOGUERA");
        Moto moto2 = new Moto("TAB23G","SUZUKI FOX","VALERY ANDREA MARTINEZ");

        vehiculo.motoAdd(moto1);
        assertThrows(Throwable.class, () -> vehiculo.motoAdd(moto2));

        LOG.info("Finalizando test PlacaRepetida");
    }
    /*test para que la velocidadmaxima de la moto no sea negativa */
    @Test
    public void VelocidadNegativa(){
        LOG.info("Inicio test VelocidadNegativa");
        assertThrows(Throwable.class,() -> new Moto("TAB23G","AUDI XX","DAVID FERNANDEZ HOGUERA",(byte)-100));
        LOG.info("Finalizando test NegativeAge");
    }
}

