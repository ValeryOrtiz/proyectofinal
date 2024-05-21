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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;





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
        var parqueadero = new Parqueadero();

        var vehiculo1 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));
        var vehiculo3 = new Moto("345", "C", "Abril", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 100, TipoMoto.HIBRIDA);
        var vehiculo4 = new Carro("456", "D", "Pepe", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));



        var dinero1 = parqueadero.calcularCosto(vehiculo1);
        var dinero2 = parqueadero.calcularCosto(vehiculo2);
        var dinero3 = parqueadero.calcularCosto(vehiculo3);
        var dinero4 = parqueadero.calcularCosto(vehiculo4);

        var ingresosEsperados = List.of(dinero1, dinero2, dinero3, dinero4);

        Vehiculo[] vehiculos = {vehiculo1, vehiculo2, vehiculo3, vehiculo4};

        var ingresosDiarios = ReporteMonetario.registrarDineroDiario(vehiculos, parqueadero);

        assertEquals(ingresosEsperados, ingresosDiarios);

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }
    @Test
    public void calcularCostoDiarioReal() {
        LOG.info("Inicio prueba de calcularCostoDiarioReal");
        var parqueadero = new Parqueadero();

        var vehiculo1 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));
        var vehiculo3 = new Moto("345", "C", "Abril", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 100, TipoMoto.HIBRIDA);
        var vehiculo4 = new Carro("456", "D", "Pepe", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));



        var dinero1 = parqueadero.calcularCosto(vehiculo1);
        var dinero2 = parqueadero.calcularCosto(vehiculo2);
        var dinero3 = parqueadero.calcularCosto(vehiculo3);
        var dinero4 = parqueadero.calcularCosto(vehiculo4);

        List<Double> vehiculos = List.of(dinero1, dinero2, dinero3, dinero4);

        double costoEsperado = dinero1 + dinero2 + dinero3 + dinero4;
        var costoReal = ReporteMonetario.calcularDineroDiario(vehiculos);
        assertEquals(costoEsperado, costoReal, 0.001);
        LOG.info("Fin prueba de calcularCostoDiarioReal");
    }
    @Test
    public void coleccionMensualConDatos() {
        LOG.info("Inicio prueba de coleccionMensualConDatos");
        var parqueadero = new Parqueadero();

        var vehiculo1 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 24, 12, 45, 0), LocalDateTime.of(2024, 5, 24, 17, 45, 0));
        var vehiculo3 = new Moto("123", "A", "Jade", LocalDateTime.of(2024, 5, 25, 12, 45, 0), LocalDateTime.of(2024, 5, 25, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo4 = new Carro("234", "B", "Anni", LocalDateTime.of(2024, 5, 25, 12, 45, 0), LocalDateTime.of(2024, 5, 25, 17, 45, 0));



        var dinero1 = parqueadero.calcularCosto(vehiculo1);
        var dinero2 = parqueadero.calcularCosto(vehiculo2);
        var dinero3 = parqueadero.calcularCosto(vehiculo3);
        var dinero4 = parqueadero.calcularCosto(vehiculo4);

        Vehiculo[] vehiculos = {vehiculo1, vehiculo2, vehiculo3, vehiculo4};

        int mesActual = 5;
        int anoActual = 2024;

        List<Double> ingresosEsperados = List.of(dinero1, dinero2, dinero3, dinero4);

        List<Double> ingresosDiarios = ReporteMonetario.registrarDineroDiario(vehiculos, parqueadero);

        assertEquals(ingresosEsperados.size(), ingresosDiarios.size());

        for (int i = 0; i < ingresosEsperados.size(); i++) {
            assertEquals(ingresosEsperados.get(i), ingresosDiarios.get(i));
        }

        LOG.info("Fin prueba de coleccionMensualConDatos");
    }


    @Test
    public void calcularCostoMensualReal(){
        LOG.info("Inicio prueba de calcularCostoMensualReal");
        var parqueadero=new Parqueadero();

        var vehiculo1=new Moto("123","A","Jade",LocalDateTime.of(2024, 5, 24, 12, 45, 0),LocalDateTime.of(2024, 5, 24, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo2=new Carro("234","B","Anni",LocalDateTime.of(2024, 5, 24, 12, 45, 0),LocalDateTime.of(2024, 5, 24, 17, 45, 0));



        var dinero1=parqueadero.calcularCosto(vehiculo1);
        var dinero2=parqueadero.calcularCosto(vehiculo2);

        var vehiculo3=new Moto("123","A","Jade",LocalDateTime.of(2024, 5, 25, 12, 45, 0),LocalDateTime.of(2024, 5, 25, 17, 45, 0), 80, TipoMoto.CLASICA);
        var vehiculo4=new Carro("234","B","Anni",LocalDateTime.of(2024, 5, 25, 12, 45, 0),LocalDateTime.of(2024, 5, 25, 17, 45, 0));



        var dinero3=parqueadero.calcularCosto(vehiculo3);
        var dinero4=parqueadero.calcularCosto(vehiculo4);

        Vehiculo[] vehiculos = {vehiculo1, vehiculo2, vehiculo3, vehiculo4};

        int mesActual = 5; // Mayo
        int anoActual = 2024;

        var costoEsperado = dinero1 + dinero2 + dinero3 + dinero4;
        var costoReal = ReporteMonetario.calcularDineroMensual(vehiculos, mesActual, anoActual, parqueadero);
        assertEquals(costoEsperado, costoReal, 0.001);

        LOG.info("Fin prueba de calcularCostoMensualReal");
    }
    @Test
    public void coleccionDiariaVacia() {
        LOG.info("Inicio prueba de coleccionDiariaConDatos");

        var motos = new LinkedList<Moto>();
        var carros = new LinkedList<Carro>();
        Vehiculo[] vehiculos = Stream.concat(motos.stream(), carros.stream()).toArray(Vehiculo[]::new);

        // Pasar la instancia de parqueadero al método registrarDineroDiario
        var vehiculosDiarios = ReporteMonetario.registrarDineroDiario(vehiculos, parqueadero);

        assertEquals(0, vehiculosDiarios.size());

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }
    @Test
    public void coleccionMensualVacia() {
        LOG.info("Inicio prueba de coleccionDiariaConDatos");

        // Crear listas vacías de motos y carros
        var motos = new LinkedList<Moto>();
        var carros = new LinkedList<Carro>();

        // Convertir las listas de motos y carros en un array de Vehiculo[]
        Vehiculo[] vehiculos = Stream.concat(motos.stream(), carros.stream()).toArray(Vehiculo[]::new);

        // Definir mes y año actual
        int mesActual = LocalDate.now().getMonthValue();
        int anoActual = LocalDate.now().getYear();

        // Pasar los vehículos al método y obtener los ingresos mensuales
        
        var ingresosMensuales = ReporteMonetario.registrarDineroMensual(vehiculos, mesActual, anoActual, parqueadero);

        // Verificar que la lista de ingresos mensuales está vacía
        assertEquals(0, ingresosMensuales.size());

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }
}
