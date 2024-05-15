/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
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
public class VehiculoTest {
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
    /*Realizo prueba para verificar la info completa */
      @Test
    public void CompleteInfo(){
        LOG.info("Iniciando test PetsTestComplete");
        Vehiculo vehiculo1 = new Vehiculo("TAB23G","AUDI XX","DAVID FERNANDEZ HOGUERA");
        assertEquals(vehiculo1.placa(), "TAB23G");
        assertEquals(vehiculo1.modelo(), "AUDI XX");
        assertEquals(vehiculo1.propietario(), "DAVID FERNANDEZ HOGUERA");
        
        LOG.info("Finalizando test PetsTestComplete");
    }
    /*Realizo prueba para verificar que la info no este nula */
    @Test
    public void InfoNULL(){
        LOG.info("Iniciando test de InfoNull");
        assertThrows(Throwable.class,() -> new Vehiculo(null,null,null));
        LOG.info("finalizando test InfoNull");
    }
    /*test para que la velocidadmaxima de la moto no sea negativa */
    @Test
    public void VelocidadNegativa(){
        LOG.info("Inicio test VelocidadNegativa");
        assertThrows(Throwable.class,() -> new Pets("Abril","gato","angora",(byte)-4,"hembra","blanco con gris",4.2));
        LOG.info("Finalizando test NegativeAge");
    }
    /*test informacion en blanco */
    @Test
    public void InfoBlank(){
        LOG.info("Iniciando test InfoBlank");
        assertThrows(Throwable.class, () -> new Vehiculo(null,null,null));
        LOG.info("Finalizando test InfoBlank");
    }


        
    

}
