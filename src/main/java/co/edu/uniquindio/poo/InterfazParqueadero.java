package co.edu.uniquindio.poo;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.util.List;

public class InterfazParqueadero extends Application {

    private Parqueadero parqueadero;
    private Registro registro;

    @Override
    public void start(Stage primaryStage) {
        parqueadero = new Parqueadero();
        registro = new Registro();

        Button registrarVehiculoBtn = new Button("Registrar Vehículo");
        registrarVehiculoBtn.setOnAction(e -> mostrarVentanaRegistro());

        Button generarReporteBtn = new Button("Generar Reporte Monetario");
        generarReporteBtn.setOnAction(e -> generarReporteMonetario());

        Button verVehiculosBtn = new Button("Ver Vehículos Registrados");
        verVehiculosBtn.setOnAction(e -> mostrarVehiculosRegistrados());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(registrarVehiculoBtn, generarReporteBtn, verVehiculosBtn);

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

        // Para meterle mas espacios para la moto (velocidad máxima)
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

            // Crea el objeto Vehiculo segun lo que se seleccione antes
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

            // Para obtener la fila y la columna seleccionadas
            int fila = Integer.parseInt(filaField.getText());
            int columna = Integer.parseInt(columnaField.getText());

            // Registra el vehículo en el parqueadero
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

        // Crea la tabla para mostrar el reporte monetario
        TableView<ReporteMonetario> tablaReporte = new TableView<>();


        //Para crear la columna de ingresos diarios
        TableColumn<ReporteMonetario, Double> ingresosDiariosCol = new TableColumn<>("Ingresos Diarios");
        ingresosDiariosCol.setCellValueFactory(cellData -> {
            List<Double> ingresosDelDia = ReporteMonetario.registrarDineroDiario(registro.getVehiculosRegistrados().toArray(new Vehiculo[0]), parqueadero);
            double totalIngresosDelDia = ReporteMonetario.calcularDineroDiario(ingresosDelDia);
            return new SimpleDoubleProperty(totalIngresosDelDia).asObject();
        });

        //Para crear la columna de los ingresos mensuales
        TableColumn<ReporteMonetario, Double> ingresosMensualesCol = new TableColumn<>("Ingresos Mensuales");
        ingresosMensualesCol.setCellValueFactory(cellData -> {
            int mesActual = LocalDateTime.now().getMonthValue();
            int anoActual = LocalDateTime.now().getYear();
            List<Double> ingresosDelMes = ReporteMonetario.registrarDineroMensual(registro.getVehiculosRegistrados().toArray(new Vehiculo[0]), mesActual, anoActual, parqueadero);
            double totalIngresosDelMes = ingresosDelMes.stream().mapToDouble(Double::doubleValue).sum();
            return new SimpleDoubleProperty(totalIngresosDelMes).asObject();
        });

        //Para crear la columna de el total de los ingresos mensuales
        TableColumn<ReporteMonetario, Double> totalIngresosMensualesCol = new TableColumn<>("Total Ingresos Mensuales");
        totalIngresosMensualesCol.setCellValueFactory(cellData -> {
            int mesActual = LocalDateTime.now().getMonthValue();
            int anoActual = LocalDateTime.now().getYear();
            double totalIngresosMensuales = ReporteMonetario.calcularDineroMensual(registro.getVehiculosRegistrados().toArray(new Vehiculo[0]), mesActual, anoActual, parqueadero);
            return new SimpleDoubleProperty(totalIngresosMensuales).asObject();
        });

        // Se agregan las columnas a la tabla
        tablaReporte.getColumns().addAll(ingresosDiariosCol, ingresosMensualesCol, totalIngresosMensualesCol);

        // Para el diseño de la ventana que va a salir
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(tablaReporte);

        Scene scene = new Scene(layout, 400, 300);
        ventanaReporte.setScene(scene);
        ventanaReporte.showAndWait();
    }

    private void mostrarVehiculosRegistrados() {
        Stage ventanaVehiculos = new Stage();
        ventanaVehiculos.initModality(Modality.APPLICATION_MODAL);
        ventanaVehiculos.setTitle("Vehículos Registrados");

        ListView<Vehiculo> listViewVehiculos = new ListView<>();

        listViewVehiculos.setItems(FXCollections.observableList(registro.getVehiculosRegistrados()));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(listViewVehiculos);

        Scene scene = new Scene(layout, 300, 200);
        ventanaVehiculos.setScene(scene);
        ventanaVehiculos.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
