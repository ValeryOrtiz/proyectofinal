//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParqueaderoTest {
    private static final Logger LOG = Logger.getLogger(co.edu.uniquindio.poo.AppTest.class.getName());

    //Duration duracion = Duration.between(carro.getFechaEntrada(), carro.getFechaSalida());
    //        long horas = duracion.toHours();
    // Así se hace el metodo obtenerDiferenciaHoras

    @Test
    public void obtenerDiferenciaHorasTest(){
        LOG.info("Iniciando test para obtener diferencia en horas");
        // Creó una instancia de Parqueadero
        Parqueadero parqueadero = new Parqueadero(2, 3, 1500, 1000, 3000);
        // Creó una instancia de Carro con fecha de entrada
        Vehiculo carro = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0));
        // Agrego el carro al parqueadero
        parqueadero.add(carro);
        // Establezco una fecha de salida
        carro.setFechaSalida(LocalDateTime.of(2024, 2, 22, 8, 0));
        // Obtengo la diferencia en horas
        long horas = parqueadero.obtenerDiferenciaHoras(carro);
        // Verifico que sean iguales
        assertEquals(1, horas);
        LOG.info("Finalizando test para obtener diferencia en horas");
    }

    @Test
    public void calcularCostoTest(){
        LOG.info("Iniciando test para calcular costo");
        // Creó una instancia de Parqueadero
        Parqueadero parqueadero = new Parqueadero(2, 3, 1000, 1500, 3000);
        // Creó una instancia de Carro con fecha de entrada
        Vehiculo carro = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0));
        // Agrego el carro al parqueadero
        parqueadero.add(carro);
        // Establezco una fecha de salida
        carro.setFechaSalida(LocalDateTime.of(2024, 2, 22, 8, 0));
        // Si es de tipo Carro entonces calculta el costo total y lo verifica con un assertEquals
        // Repito el procedimiento para verificar moto hibrida y moto clasica
        Vehiculo motoHibrida = new Moto("XDJ324", "AKT", "Andres", LocalDateTime.of(2024, 2, 22, 7, 0), TipoMoto.HIBRIDA);
        // Agrego la moto al parqueadero
        parqueadero.add(motoHibrida);
        // Establezco una fecha de salida
        motoHibrida.setFechaSalida(LocalDateTime.of(2024, 2, 22, 9, 0));
        Vehiculo motoClasica = new Moto("GHY824", "MERC", "RayoMcQueen", LocalDateTime.of(2024, 2, 22, 7, 0), TipoMoto.CLASICA);
        // Agrego la moto al parqueadero
        parqueadero.add(motoClasica);
        // Establezco una fecha de salida
        motoClasica.setFechaSalida(LocalDateTime.of(2024, 2, 22, 11, 0));
        // Creó una lista esperada
        Collection<Integer> listaEsperada = List.of(3000, 3000, 4000);
        // Creó otra lista usando la función
        Collection<Integer> listaCostos = parqueadero.getVehiculos().stream().map(Vehiculo::calcularCosto).collect(Collectors.toList());
        // Itero en ambas listas comparando
        assertIterableEquals(listaEsperada, listaCostos);
        LOG.info("Finalizando test para calcular costo");
    }

    @Test
    public void ingresarFilasYColumnasTest(){
        LOG.info("Iniciando test para ingresar las filas y columnas. ");
        // Instancio un Parqueadero
        Parqueadero parqueadero = new Parqueadero(2, 3, 1000, 1500, 3000);
        // Lista esperada según los parametros de fila y de columnas del objeto
        Collection<List<Collection<Vehiculo>>> listaEsperada = List.of(
                List.<Collection<Vehiculo>>of((Collection<Vehiculo>) List.of(), List.of(), List.of()),
                List.<Collection<Vehiculo>>of((Collection<Vehiculo>) List.of(), List.of(), List.of())
        );
        // Creo la lista con el metodo
        Collection<List<Collection<Vehiculo>>> listaFilasYColumnas = parqueadero.ingresarFilasYColumnas();
        // Comparo las listas
        assertIterableEquals(listaEsperada, listaFilasYColumnas);
        LOG.info("Finalizando test para ingresar las filas y columnas. ");
    }

    @Test
    public void verificarPuestoTest() {
        LOG.info("Iniciando test para verificar puesto");
        Vehiculo vehiculo1 = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo2 = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo3 = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo4 = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo5 = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        // Creó una lista verificando el puesto que esta ocupado
        Collection<List<Collection<Vehiculo>>> parqueadero = List.of(
                List.<Collection<Vehiculo>>of((Collection<Vehiculo>) List.of(), List.of(vehiculo1), List.of(vehiculo2)),
                List.<Collection<Vehiculo>>of((Collection<Vehiculo>) List.of(vehiculo5), List.of(vehiculo4), List.of(vehiculo3))
        );
        // espero que me salga un error porque estoy verificando en la primera fila la posición 0
        // el cual no tiene ningun vehiculo, por tal razon, me devolver un False
        // preguntar a profesor acerca del runtime
        assertThrows(RuntimeException.class, () -> parqueadero.verificarPuestoOcupado(0, 1));
        LOG.info("Finalizando test para verificar puesto");
    }

    @Test
    public void verificarVehiculoExisteTest(){
        LOG.info("Iniciando test para verificar si el vehiculo existe");
        // Creó una instancia de un carro
        Vehiculo vehiculo = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        // creo un parqueadero esperado que contenga el mismo vehiculo dos veces, para poder así verificar si está repetido
        Collection<List<Collection<Vehiculo>>> parqueadero = List.of(
                List.<Collection<Vehiculo>>of((Collection<Vehiculo>) List.of(), List.of(vehiculo), List.of()),
                List.<Collection<Vehiculo>>of((Collection<Vehiculo>) List.of(), List.of(), List.of())
        );
        // Verifico si el vehiculo existe, si existe me devuelve un true, por lo contrario, lo ideal es que me devuelva
        // un False
        boolean existe = parqueadero.verificarVehiculoExiste();
        // deberia estar bien puesto que en este caso el vehiculo si existe
        assertTrue(existe);
        LOG.info("Finalizando test para verificar si el vehiculo existe");
    }
}