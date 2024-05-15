package co.edu.uniquindio.poo;

import javax.sound.sampled.SourceDataLine;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    
    Moto motoClasica = new Moto("Honda", "CB750", "javier", TipoMoto.CLASICA);
    System.out.println("Moto: " + motoClasica.getPlaca() + " " + motoClasica.getModelo() + " " + motoClasica.getPropietario() + " " + motoClasica.getTipo());

    // Crear un carro
    Carro carro = new Carro("Toyota", "Corolla", "daniel");
    System.out.println("Carro: " + carro.getPlaca() + " " + carro.getModelo() + " " + carro.getPropietario());
    }
}


