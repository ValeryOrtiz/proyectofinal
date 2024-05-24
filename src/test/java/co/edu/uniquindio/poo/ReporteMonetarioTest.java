/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;






/**
 * Unit test for simple App.
 */
public class ReporteMonetarioTest {
    private static final Logger LOG = Logger.getLogger(ReporteMonetarioTest.class.getName());
    private Parqueadero parqueadero;

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
    public void coleccionDiariaConDatos() {
        LOG.info("Inicio prueba de coleccionDiariaConDatos");
        var parqueadero = new ReporteMonetario(10, 10, 100, 80, 150);

        var vehiculo1 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));
        var vehiculo3 = new Moto("345", "C", "Abril", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 100, TipoMoto.HIBRIDA);
        var vehiculo4 = new Carro("456", "D", "Pepe", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));

        parqueadero.add(0, 0, vehiculo1);
        parqueadero.add(0, 1, vehiculo2);
        parqueadero.add(0, 2, vehiculo3);
        parqueadero.add(0, 3, vehiculo4);

        var ingresosEsperados = List.of(
                Parqueadero.calcularCosto(vehiculo1),
                Parqueadero.calcularCosto(vehiculo2),
                Parqueadero.calcularCosto(vehiculo3),
                Parqueadero.calcularCosto(vehiculo4)
        );

        var ingresosDiarios = parqueadero.registrarDineroDiario(LocalDateTime.of(2024, 5, 24, 0, 0, 0));

        assertEquals(ingresosEsperados, ingresosDiarios);

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }

    @Test
    public void calcularCostoDiarioReal() {
        LOG.info("Inicio prueba de calcularCostoDiarioReal");
        var parqueadero = new ReporteMonetario(10, 10, 100, 80, 150);

        var vehiculo1 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));
        var vehiculo3 = new Moto("345", "C", "Abril", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 100, TipoMoto.HIBRIDA);
        var vehiculo4 = new Carro("456", "D", "Pepe", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));

        parqueadero.add(0, 0, vehiculo1);
        parqueadero.add(0, 1, vehiculo2);
        parqueadero.add(0, 2, vehiculo3);
        parqueadero.add(0, 3, vehiculo4);

        var costoEsperado = Parqueadero.calcularCosto(vehiculo1) + Parqueadero.calcularCosto(vehiculo2) + Parqueadero.calcularCosto(vehiculo3) + Parqueadero.calcularCosto(vehiculo4);
        var costoReal = parqueadero.calcularDineroDiario(LocalDateTime.of(2024, 5, 24, 0, 0, 0));

        assertEquals(costoEsperado, costoReal, 0.001);
        LOG.info("Fin prueba de calcularCostoDiarioReal");
    }

    @Test
    public void coleccionMensualConDatos() {
        LOG.info("Inicio prueba de coleccionMensualConDatos");
        var parqueadero = new ReporteMonetario(10, 10, 100, 80, 150);

        var vehiculo1 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));
        var vehiculo3 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 25, 12, 45, 0), LocalDateTime.of(2024, 5, 25, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo4 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 25, 12, 45, 0), LocalDateTime.of(2024, 5, 25, 17, 45, 0));

        parqueadero.add(0, 0, vehiculo1);
        parqueadero.add(0, 1, vehiculo2);
        parqueadero.add(0, 2, vehiculo3);
        parqueadero.add(0, 3, vehiculo4);

        var dinero1 = Parqueadero.calcularCosto(vehiculo1);
        var dinero2 = Parqueadero.calcularCosto(vehiculo2);
        var dinero3 = Parqueadero.calcularCosto(vehiculo3);
        var dinero4 = Parqueadero.calcularCosto(vehiculo4);

        List<Double> ingresosEsperados = List.of(dinero1, dinero2, dinero3, dinero4);

        int mesActual = 5;
        int anoActual = 2024;

        var ingresosMensuales = parqueadero.registrarDineroMensual(mesActual, anoActual);

        // Check each day for expected income
        assertTrue(ingresosMensuales.containsAll(ingresosEsperados));

        LOG.info("Fin prueba de coleccionMensualConDatos");
    }



    @Test
    public void calcularCostoMensualReal() {
        LOG.info("Inicio prueba de calcularCostoMensualReal");
        var parqueadero = new ReporteMonetario(10, 10, 100, 80, 150);

        var vehiculo1 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));
        var vehiculo3 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 25, 12, 45, 0), LocalDateTime.of(2024, 5, 25, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo4 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 25, 12, 45, 0), LocalDateTime.of(2024, 5, 25, 17, 45, 0));

        parqueadero.add(0, 0, vehiculo1);
        parqueadero.add(0, 1, vehiculo2);
        parqueadero.add(0, 2, vehiculo3);
        parqueadero.add(0, 3, vehiculo4);

        var costoEsperado = Parqueadero.calcularCosto(vehiculo1) + Parqueadero.calcularCosto(vehiculo2) + Parqueadero.calcularCosto(vehiculo3) + Parqueadero.calcularCosto(vehiculo4);
        var costoReal = parqueadero.calcularDineroMensual(5, 2024);

        assertEquals(costoEsperado, costoReal, 0.001);
        LOG.info("Fin prueba de calcularCostoMensualReal");
    }


    @Test
    public void coleccionDiariaVacia() {
        LOG.info("Inicio prueba de coleccionDiariaVacia");
        var parqueadero = new ReporteMonetario(10, 10, 100, 80, 150);

        var ingresosDiarios = parqueadero.registrarDineroDiario(LocalDateTime.of(2024, 5, 24, 0, 0, 0));

        assertEquals(0, ingresosDiarios.size());

        LOG.info("Fin prueba de coleccionDiariaVacia");
    }


    @Test
    public void coleccionMensualVacia() {
        LOG.info("Inicio prueba de coleccionMensualVacia");
        var parqueadero = new ReporteMonetario(10, 10, 100, 80, 150);

        int mesActual = 5;
        int anoActual = 2024;

        var ingresosMensuales = parqueadero.registrarDineroMensual(mesActual, anoActual);

        assertEquals(0, ingresosMensuales.size());

        LOG.info("Fin prueba de coleccionMensualVacia");
    }

}
