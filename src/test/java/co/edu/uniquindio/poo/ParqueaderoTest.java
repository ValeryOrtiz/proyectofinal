//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        Parqueadero parqueadero = new Parqueadero();
        // Creó una instancia de Carro con fecha de entrada
        Vehiculo carro = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0));
        // Agrego el carro al parqueadero
        parqueadero.add(parqueadero.getFilas(), parqueadero.getColumnas(), carro);
        // Establezco una fecha de salida
        carro.setFechaSalida(LocalDateTime.of(2024, 2, 22, 8, 0));
        // Obtengo la diferencia en horas
        long horas = Parqueadero.obtenerDiferenciaHoras(carro);
        // Verifico que sean iguales
        assertEquals(1, horas);
        LOG.info("Finalizando test para obtener diferencia en horas");
    }

    @Test
    public void calcularCostoTest(){
        LOG.info("Iniciando test para calcular costo");
        // Creó una instancia de Parqueadero
        Parqueadero parqueadero = new Parqueadero();
        // Creó una instancia de Carro con fecha de entrada
        Vehiculo carro = new Carro("VGT777", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0));
        // Agrego el carro al parqueadero
        parqueadero.add(parqueadero.getFilas(), parqueadero.getColumnas(), carro);
        // Establezco una fecha de salida
        carro.setFechaSalida(LocalDateTime.of(2024, 2, 22, 8, 0));
        // Si es de tipo Carro entonces calculta el costo total y lo verifica con un assertEquals
        // Repito el procedimiento para verificar moto hibrida y moto clasica
        Vehiculo motoHibrida = new Moto("XDJ324", "AKT", "Andres", LocalDateTime.of(2024, 2, 22, 7, 0), 70, TipoMoto.HIBRIDA);
        // Agrego la moto al parqueadero
        parqueadero.add(parqueadero.getFilas(), parqueadero.getColumnas(), motoHibrida);
        // Establezco una fecha de salida
        motoHibrida.setFechaSalida(LocalDateTime.of(2024, 2, 22, 9, 0));
        Vehiculo motoClasica = new Moto("GHY824", "MERC", "RayoMcQueen", LocalDateTime.of(2024, 2, 22, 7, 0), 70, TipoMoto.CLASICA);
        // Agrego la moto al parqueadero
        parqueadero.add(parqueadero.getFilas(), parqueadero.getColumnas(), motoClasica);
        // Establezco una fecha de salida
        motoClasica.setFechaSalida(LocalDateTime.of(2024, 2, 22, 11, 0));
        // Creó una lista esperada
        Collection<Integer> listaEsperada = List.of(3000, 3000, 4000);
        // Creó otra lista usando la función
        Collection<Double> listaCostos = new ArrayList<>();
        for (Vehiculo vehiculo : parqueadero.getVehiculos()) {
            Double calcularCosto = Parqueadero.calcularCosto(vehiculo);
            listaCostos.add(calcularCosto);
        }
        // Itero en ambas listas comparando
        assertIterableEquals(listaEsperada, listaCostos);
        LOG.info("Finalizando test para calcular costo");
    }

    @Test
    public void verificarPuestoTest() {
        LOG.info("Iniciando test para verificar puesto");

        var parqueadero = new Parqueadero();

        // Establecer filas y columnas del parqueadero
        parqueadero.setFilas(2);
        parqueadero.setColumnas(3);

        // Inicializar el registro de vehículos en el parqueadero
        List<List<Vehiculo>> registro = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Vehiculo> fila = new ArrayList<>(Collections.nCopies(3, null));
            registro.add(fila);
        }
        parqueadero.setRegistroVehiculo(registro);

        // Crear vehículos con placas diferentes
        Vehiculo vehiculo1 = new Carro("VGT771", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo2 = new Carro("VGT772", "BUGAT", "Juan", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo3 = new Carro("VGT773", "BUGAT", "Luis", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo4 = new Carro("VGT774", "BUGAT", "Ana", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo5 = new Carro("VGT775", "BUGAT", "Maria", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));

        // Agregar vehículos a la matriz
        parqueadero.add(0, 1, vehiculo1);
        parqueadero.add(0, 2, vehiculo2);
        parqueadero.add(1, 0, vehiculo3);
        parqueadero.add(1, 1, vehiculo4);
        parqueadero.add(1, 2, vehiculo5);

        // Verificar que la posición [0][0] está vacía
        assertFalse(parqueadero.verificarPuesto(0, 0), "El puesto en [0][0] debería estar vacío.");

        // Verificar que las posiciones con vehículos están ocupadas
        assertTrue(parqueadero.verificarPuesto(0, 1), "El puesto en [0][1] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(0, 2), "El puesto en [0][2] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(1, 0), "El puesto en [1][0] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(1, 1), "El puesto en [1][1] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(1, 2), "El puesto en [1][2] debería estar ocupado.");

        LOG.info("Finalizando test para verificar puesto");
    }

    @Test
    public void verificarVehiculoExisteTest(){
        LOG.info("Iniciando test para verificar puesto");

        Parqueadero parqueadero = new Parqueadero();
        Vehiculo vehiculo1 = new Carro("VGT771", "BUGAT", "Pedro", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo2 = new Carro("VGT772", "BUGAT", "Juan", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo3 = new Carro("VGT773", "BUGAT", "Luis", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo4 = new Carro("VGT774", "BUGAT", "Ana", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));
        Vehiculo vehiculo5 = new Carro("VGT775", "BUGAT", "Maria", LocalDateTime.of(2024, 2, 22, 7, 0), LocalDateTime.of(2024, 2, 22, 8, 0));

        // Inicializando el parqueadero con 2 filas y 3 columnas
        parqueadero.setFilas(2);
        parqueadero.setColumnas(3);
        parqueadero.setRegistroVehiculo(new ArrayList<>());
        for (int i = 0; i < 2; i++) {
            assertTrue(Parqueadero.getRegistroVehiculo().add(new ArrayList<>(List.of(new Vehiculo[3]))));
        }

        // Agregando vehículos a la matriz
        parqueadero.add(0, 1, vehiculo1);
        parqueadero.add(0, 2, vehiculo2);
        parqueadero.add(1, 0, vehiculo3);
        parqueadero.add(1, 1, vehiculo4);
        parqueadero.add(1, 2, vehiculo5);

        // Verificando que la posición [0][0] está vacía
        assertFalse(parqueadero.verificarPuesto(0, 0), "El puesto en [0][0] debería estar vacío.");

        // Verificando que las posiciones con vehículos están ocupadas
        assertTrue(parqueadero.verificarPuesto(0, 1), "El puesto en [0][1] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(0, 2), "El puesto en [0][2] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(1, 0), "El puesto en [1][0] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(1, 1), "El puesto en [1][1] debería estar ocupado.");
        assertTrue(parqueadero.verificarPuesto(1, 2), "El puesto en [1][2] debería estar ocupado.");

        LOG.info("Finalizando test para verificar puesto");
    }
}