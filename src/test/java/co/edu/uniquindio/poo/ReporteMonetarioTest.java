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
import java.util.LinkedList;
import java.util.List;


import scala.collection.AbstractSeq;


/**
 * Unit test for simple App.
 */
public class ReporteMonetarioTest {
    private static final Logger LOG = Logger.getLogger(ReporteMonetarioTest.class.getName());

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
    public void coleccionDiariaConDatos(){
        LOG.info("Inicio prueba de coleccionDiariaConDatos");
        var parqueadero=new Parqueadero(4,5,2000,1900,3000);

        var vehiculo1=new Moto("123","A","Jade",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),80,CLASICA);
        var vehiculo2=new Carro("234","B","Anni",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));
        var vehiculo3=new Moto("345","C","Abril",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),100,HIBRIDA);
        var vehiculo4=new Carro("456","D","Pepe",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));

        var hora1=vehiculo1.calcularHoras(vehiculo1);
        var hora2=vehiculo2.calcularHoras(vehiculo2);
        var hora3=vehiculo3.calcularHoras(vehiculo3);
        var hora4=vehiculo4.calcularHoras(vehiculo4);

        var dinero1=parqueadero.calcularCosto(hora1);
        var dinero2=parqueadero.calcularCosto(hora2);
        var dinero3=parqueadero.calcularCosto(hora3);
        var dinero4=parqueadero.calcularCosto(hora4);

        
        var vehiculosEsperados= List.of(dinero1, dinero2, dinero3, dinero4);

        var vehiculosLista=ReporteMonetario.registrarDineroDiario(dinero1,dinero2,dinero3,dinero4);

        assertEquals(vehiculosEsperados, vehiculosLista);

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }
    @Test
    public void calcularCostoDiarioReal(){
        LOG.info("Inicio prueba de coleccionDiariaConDatos");
        var parqueadero=new Parqueadero(4,5,2000,1900,3000);

        var vehiculo1=new Moto("123","A","Jade",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),80,CLASICA);
        var vehiculo2=new Carro("234","B","Anni",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));
        var vehiculo3=new Moto("345","C","Abril",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),100,HIBRIDA);
        var vehiculo4=new Carro("456","D","Pepe",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));

        var hora1=vehiculo1.calcularHoras(vehiculo1);
        var hora2=vehiculo2.calcularHoras(vehiculo2);
        var hora3=vehiculo3.calcularHoras(vehiculo3);
        var hora4=vehiculo4.calcularHoras(vehiculo4);

        var dinero1=parqueadero.calcularCosto(hora1);
        var dinero2=parqueadero.calcularCosto(hora2);
        var dinero3=parqueadero.calcularCosto(hora3);
        var dinero4=parqueadero.calcularCosto(hora4);

        var vehiculos= List.of(dinero1, dinero2, dinero3, dinero4);

        var costoEsperado= dinero1+dinero2+dinero3+dinero4;
        var costoReal=ReporteMonetario.calcularDineroDiario(vehiculos);
        assertEquals(costoEsperado, costoReal);

        LOG.info("Inicio prueba de coleccionDiariaConDatos");
    }
    @Test
    public void coleccionMensualConDatos(){
        LOG.info("Fin prueba de coleccionDiariaConDatos");
        var parqueadero=new Parqueadero(4,5,2000,1900,3000);

        var vehiculo1=new Moto("123","A","Jade",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),80,CLASICA);
        var vehiculo2=new Carro("234","B","Anni",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));

        var hora1=vehiculo1.calcularHoras(vehiculo1);
        var hora2=vehiculo2.calcularHoras(vehiculo2);
        
        var dinero1=parqueadero.calcularCosto(hora1);
        var dinero2=parqueadero.calcularCosto(hora2);

        var vehiculo3=new Moto("123","A","Jade",LocalDateTime.of(15,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),80,CLASICA);
        var vehiculo4=new Carro("234","B","Anni",LocalDateTime.of(15,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));
        
        var hora3=vehiculo3.calcularHoras(vehiculo3);
        var hora4=vehiculo4.calcularHoras(vehiculo4);

        var dinero3=parqueadero.calcularCosto(hora3);
        var dinero4=parqueadero.calcularCosto(hora4);

        var vehiculosLista1=ReporteMonetario.registrarDineroDiario(dinero1,dinero2);
        var vehiculosLista2=ReporteMonetario.registrarDineroDiario(dinero3,dinero4);
        
        var listaMensual=ReporteMonetario.registrarDineroMensual(vehiculosLista1,vehiculosLista2);
        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }
    @Test 
    public void calcularCostoMensualReal(){
        LOG.info("Fin prueba de coleccionDiariaConDatos");
        var parqueadero=new Parqueadero(4,5,2000,1900,3000);

        var vehiculo1=new Moto("123","A","Jade",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),80,CLASICA);
        var vehiculo2=new Carro("234","B","Anni",LocalDateTime.of(14,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));

        var hora1=vehiculo1.calcularHoras(vehiculo1);
        var hora2=vehiculo2.calcularHoras(vehiculo2);
        
        var dinero1=parqueadero.calcularCosto(hora1);
        var dinero2=parqueadero.calcularCosto(hora2);

        var vehiculo3=new Moto("123","A","Jade",LocalDateTime.of(15,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0),80,CLASICA);
        var vehiculo4=new Carro("234","B","Anni",LocalDateTime.of(15,05,24,12,45,0),LocalDateTime.of(14,05,24,17,45,0));
        
        var hora3=vehiculo3.calcularHoras(vehiculo3);
        var hora4=vehiculo4.calcularHoras(vehiculo4);

        var dinero3=parqueadero.calcularCosto(hora3);
        var dinero4=parqueadero.calcularCosto(hora4);

        var vehiculosLista1=ReporteMonetario.registrarDineroDiario(dinero1,dinero2);
        var vehiculosLista2=ReporteMonetario.registrarDineroDiario(dinero3,dinero4);

        var sumaDia1= dinero1+dinero2;
        var sumaDia2= dinero3+dinero4;

        var listaDinero=List.of(vehiculosLista1,vehiculosLista2);

        var costoEsperado= sumaDia1+sumaDia2;
        var costoReal=ReporteMonetario.calcularDineroMensual(sumaDia1,sumaDia2);
        assertEquals(costoEsperado, costoReal);

    }
    @Test
    public void coleccionDiariaVacia(){
        LOG.info("Inicio prueba de coleccionDiariaConDatos");
        

        var motos=new LinkedList<Moto>();
        var carros= new LinkedList<Carro>();
        var vehiculosDiarios= ReporteMonetario.registrarDineroDiario(motos,carros);
        
        assertEquals(0, vehiculosDiarios.size());

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }
    @Test
    public void coleccionMensualVacia(){
        LOG.info("Inicio prueba de coleccionDiariaConDatos");
        

        
        var motos= new LinkedList<Moto>();
        var carros=new LinkedList<Carro>();
        var diario= ReporteMonetario.registrarDineroDiario(motos,carros);
        var vehiculosMensuales= ReporteMonetario.registrarDineroMensual(diario);
        
        assertEquals(0, vehiculosMensuales.size());

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }
}
