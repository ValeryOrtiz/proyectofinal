package co.edu.uniquindio.poo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDateTime;

public class InterfazParqueadero extends Application {

    private Parqueadero parqueadero;

    @Override
    public void start(Stage primaryStage) {
        parqueadero = new Parqueadero();

        Button registrarVehiculoBtn = new Button("Registrar Vehículo");
        registrarVehiculoBtn.setOnAction(e -> mostrarVentanaRegistro());

        Button generarReporteBtn = new Button("Generar Reporte Monetario");
        generarReporteBtn.setOnAction(e -> generarReporteMonetario());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(registrarVehiculoBtn, generarReporteBtn);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Interfaz Parqueadero");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void mostrarVentanaRegistro() {
        Stage ventanaRegistro = new Stage();
        ventanaRegistro.initModality(Modality.APPLICATION_MODAL);
        ventanaRegistro.setTitle("Registro de Vehículo");

        Label tipoLabel = new Label("Tipo de vehículo:");
        ChoiceBox<String> tipoChoiceBox = new ChoiceBox<>();
        tipoChoiceBox.getItems().addAll("Carro", "Moto Híbrida", "Moto Clásica");
        tipoChoiceBox.setValue("Carro");

        TextField marcaField = new TextField();
        marcaField.setPromptText("Marca del vehículo");

        TextField modeloField = new TextField();
        modeloField.setPromptText("Modelo del vehículo");

        TextField placaField = new TextField();
        placaField.setPromptText("Placa del vehículo");

        // Agregar campos adicionales para la moto (velocidad máxima)
        Label velocidadLabel = new Label("Velocidad Máxima:");
        TextField velocidadField = new TextField();
        velocidadField.setPromptText("Velocidad Máxima");

        // Campos para seleccionar la fila y la columna
        Label filaLabel = new Label("Fila:");
        TextField filaField = new TextField();
        filaField.setPromptText("Número de fila");

        Label columnaLabel = new Label("Columna:");
        TextField columnaField = new TextField();
        columnaField.setPromptText("Número de columna");

        Button confirmarBtn = new Button("Confirmar Registro");
        confirmarBtn.setOnAction(e -> {
            String tipo = tipoChoiceBox.getValue();
            String marca = marcaField.getText();
            String modelo = modeloField.getText();
            String placa = placaField.getText();

            // Crear el objeto Vehiculo adecuado según el tipo seleccionado
            Vehiculo vehiculo;
            if (tipo.equals("Carro")) {
                vehiculo = new Carro(placa, modelo, marca, LocalDateTime.now());
            } else if (tipo.equals("Moto Híbrida")) {
                int velocidadMaxima = Integer.parseInt(velocidadField.getText());
                vehiculo = new Moto(placa, modelo, marca, LocalDateTime.now(), velocidadMaxima, TipoMoto.HIBRIDA);
            } else { // Moto Clásica
                int velocidadMaxima = Integer.parseInt(velocidadField.getText());
                vehiculo = new Moto(placa, modelo, marca, LocalDateTime.now(), velocidadMaxima, TipoMoto.CLASICA);
            }

            // Obtener la fila y la columna seleccionadas
            int fila = Integer.parseInt(filaField.getText());
            int columna = Integer.parseInt(columnaField.getText());

            // Registrar el vehículo en el parqueadero
            parqueadero.registrarVehiculo(fila, columna, vehiculo);

            ventanaRegistro.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                tipoLabel, tipoChoiceBox,
                marcaField, modeloField, placaField,
                velocidadLabel, velocidadField,
                filaLabel, filaField,
                columnaLabel, columnaField,
                confirmarBtn
        );

        Scene scene = new Scene(layout, 300, 300);
        ventanaRegistro.setScene(scene);
        ventanaRegistro.showAndWait();
    }



    private void generarReporteMonetario() {
        Stage ventanaReporte = new Stage();
        ventanaReporte.initModality(Modality.APPLICATION_MODAL);
        ventanaReporte.setTitle("Reporte Monetario");

        // Aquí puedes crear los elementos de la ventana de reporte monetario
        // Por ejemplo, puedes mostrar información sobre los ingresos diarios o mensuales del parqueadero

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 200);
        ventanaReporte.setScene(scene);
        ventanaReporte.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
