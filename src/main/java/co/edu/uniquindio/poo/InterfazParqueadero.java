package co.edu.uniquindio.poo;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.stage.Modality;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


public class InterfazParqueadero extends Application {

    private Parqueadero parqueadero;
    private Registro registro;
    private ListView<Vehiculo> listViewVehiculos = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        parqueadero = new Parqueadero();
        registro = new Registro();

        Button infoParqueaderoBtn = new Button("Registrar Información del Parqueadero");
        infoParqueaderoBtn.setOnAction(e -> mostrarVentanaInfoParqueadero());

        Button registrarVehiculoBtn = new Button("Registrar Vehículo");
        registrarVehiculoBtn.setOnAction(e -> mostrarVentanaRegistro());

        Button generarReporteBtn = new Button("Generar Reporte Monetario");
        generarReporteBtn.setOnAction(e -> generarReporteMonetario());

        Button verVehiculosBtn = new Button("Ver Vehículos Registrados");
        verVehiculosBtn.setOnAction(e -> mostrarVehiculosRegistrados());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(infoParqueaderoBtn, registrarVehiculoBtn, generarReporteBtn, verVehiculosBtn);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Interfaz Parqueadero");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarVentanaInfoParqueadero() {
        Stage ventanaInfoParqueadero = new Stage();
        ventanaInfoParqueadero.initModality(Modality.APPLICATION_MODAL);
        ventanaInfoParqueadero.setTitle("Información del Parqueadero");

        Label filasLabel = new Label("Número de Filas:");
        TextField filasField = new TextField();

        Label columnasLabel = new Label("Número de Columnas:");
        TextField columnasField = new TextField();

        Label tarifaCarroLabel = new Label("Tarifa Carro:");
        TextField tarifaCarroField = new TextField();

        Label tarifaMotoHLabel = new Label("Tarifa Moto Híbrida:");
        TextField tarifaMotoHField = new TextField();

        Label tarifaMotoCLabel = new Label("Tarifa Moto Clásica:");
        TextField tarifaMotoCField = new TextField();

        Button confirmarBtn = new Button("Confirmar");
        confirmarBtn.setOnAction(e -> {
            int filas = Integer.parseInt(filasField.getText());
            int columnas = Integer.parseInt(columnasField.getText());
            int tarifaCarro = Integer.parseInt(tarifaCarroField.getText());
            int tarifaMotoH = Integer.parseInt(tarifaMotoHField.getText());
            int tarifaMotoC = Integer.parseInt(tarifaMotoCField.getText());

            parqueadero = new Parqueadero(filas, columnas, tarifaMotoH, tarifaMotoC, tarifaCarro);

            ventanaInfoParqueadero.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                filasLabel, filasField,
                columnasLabel, columnasField,
                tarifaCarroLabel, tarifaCarroField,
                tarifaMotoHLabel, tarifaMotoHField,
                tarifaMotoCLabel, tarifaMotoCField,
                confirmarBtn
        );

        Scene scene = new Scene(layout, 300, 250);
        ventanaInfoParqueadero.setScene(scene);
        ventanaInfoParqueadero.showAndWait();
    }

    private void mostrarVentanaRegistro() {
        Stage ventanaRegistro = new Stage();
        ventanaRegistro.initModality(Modality.APPLICATION_MODAL);
        ventanaRegistro.setTitle("Registro de Vehículo");

        Label tipoLabel = new Label("Tipo de vehículo:");
        ChoiceBox<String> tipoChoiceBox = new ChoiceBox<>();
        tipoChoiceBox.getItems().addAll("Carro", "Moto Híbrida", "Moto Clásica");
        tipoChoiceBox.setValue("Carro");

        TextField placaField = new TextField();
        placaField.setPromptText("Placa del vehículo");

        TextField modeloField = new TextField();
        modeloField.setPromptText("Modelo del vehículo");

        TextField propietarioField = new TextField();
        propietarioField.setPromptText("Propietario del vehículo");

        Label velocidadLabel = new Label("Velocidad Máxima:");
        TextField velocidadField = new TextField();
        velocidadField.setPromptText("Velocidad Máxima");
        velocidadField.setVisible(false);

        tipoChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Moto Híbrida") || newValue.equals("Moto Clásica")) {
                velocidadField.setVisible(true);
            } else {
                velocidadField.setVisible(false);
            }
        });

        Label filaLabel = new Label("Fila:");
        TextField filaField = new TextField();
        filaField.setPromptText("Número de fila");

        Label columnaLabel = new Label("Columna:");
        TextField columnaField = new TextField();
        columnaField.setPromptText("Número de columna");

        Label fechaEntradaLabel = new Label("Fecha de Entrada:");
        TextField fechaEntradaField = new TextField();
        fechaEntradaField.setPromptText("Formato: YYYY-MM-DD HH:MM:SS");

        Label fechaSalidaLabel = new Label("Fecha de Salida:");
        TextField fechaSalidaField = new TextField();
        fechaSalidaField.setPromptText("Formato: YYYY-MM-DD HH:MM:SS");

        Button confirmarBtn = new Button("Confirmar Registro");
        confirmarBtn.setOnAction(e -> {
            String tipo = tipoChoiceBox.getValue();
            String placa = placaField.getText();
            String modelo = modeloField.getText();
            String propietario = propietarioField.getText();
            int fila = Integer.parseInt(filaField.getText());
            int columna = Integer.parseInt(columnaField.getText());
            LocalDateTime fechaEntrada = LocalDateTime.parse(fechaEntradaField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime fechaSalida = LocalDateTime.parse(fechaSalidaField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Vehiculo vehiculo;
            if (tipo.equals("Carro")) {
                vehiculo = new Carro(placa, modelo, propietario, fechaEntrada, fechaSalida);
            } else {
                int velocidadMaxima = Integer.parseInt(velocidadField.getText());
                TipoMoto tipoMoto = tipo.equals("Moto Híbrida") ? TipoMoto.HIBRIDA : TipoMoto.CLASICA;
                vehiculo = new Moto(placa, modelo, propietario, fechaEntrada, fechaSalida, velocidadMaxima, tipoMoto);
            }
            double costo = Parqueadero.calcularCosto(vehiculo);
            System.out.println("El costo del estacionamiento es: " + costo);

            if (parqueadero.verificarPuesto(fila, columna)) {
                mostrarAlerta("Posición Ocupada", "La posición seleccionada ya está ocupada.");
            } else {
                parqueadero.registrarVehiculo(fila, columna, vehiculo);
                registro.agregarVehiculo(vehiculo);
                listViewVehiculos.setItems(FXCollections.observableList(registro.getVehiculosRegistrados()));
                ventanaRegistro.close();
                generarReporteMonetario();
            }

        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                tipoLabel, tipoChoiceBox,
                placaField, modeloField, propietarioField,
                velocidadLabel, velocidadField,
                filaLabel, filaField,
                columnaLabel, columnaField,
                fechaEntradaLabel, fechaEntradaField,
                fechaSalidaLabel, fechaSalidaField,
                confirmarBtn
        );

        Scene scene = new Scene(layout, 300, 450);
        ventanaRegistro.setScene(scene);
        ventanaRegistro.showAndWait();
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void generarReporteMonetario() {
        Stage ventanaReporte = new Stage();
        ventanaReporte.initModality(Modality.APPLICATION_MODAL);
        ventanaReporte.setTitle("Reporte Monetario");

        TableView<String> tablaReporte = new TableView<>();

        TableColumn<String, String> ingresosDiariosCol = new TableColumn<>("Ingresos Diarios");
        ingresosDiariosCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        TableColumn<String, String> ingresosMensualesCol = new TableColumn<>("Ingresos Mensuales");
        ingresosMensualesCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        TableColumn<String, String> costoVehiculoCol = new TableColumn<>("Costo Vehículo");
        costoVehiculoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        TableColumn<String, String> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        tablaReporte.getColumns().addAll(ingresosDiariosCol, ingresosMensualesCol, costoVehiculoCol, totalCol);

        List<Double> ingresosDiarios = ReporteMonetario.registrarDineroDiario(registro.getVehiculosRegistrados().toArray(new Vehiculo[0]), parqueadero);
        List<Double> ingresosMensuales = ReporteMonetario.registrarDineroMensual(registro.getVehiculosRegistrados().toArray(new Vehiculo[0]), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), parqueadero);
        double total = ingresosDiarios.stream().mapToDouble(Double::doubleValue).sum();

        double costoTotal = registro.getVehiculosRegistrados().stream()
                .mapToDouble(vehiculo -> Parqueadero.calcularCosto(vehiculo))
                .sum();

        List<String> reporte = new ArrayList<>();
        double costoVehiculosTotal = 0.0;
        for (Vehiculo vehiculo : registro.getVehiculosRegistrados()) {
            double costoVehiculo = Parqueadero.calcularCosto(vehiculo);
            costoVehiculosTotal += costoVehiculo;
            reporte.add(String.valueOf(costoVehiculo));
        }
        reporte.addAll(ingresosDiarios.stream().map(String::valueOf).collect(Collectors.toList()));
        reporte.addAll(ingresosMensuales.stream().map(String::valueOf).collect(Collectors.toList()));
        reporte.add(String.valueOf(costoVehiculosTotal));

        tablaReporte.setItems(FXCollections.observableList(reporte));

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

        // Obtener la lista de vehículos registrados
        List<Vehiculo> vehiculosRegistrados = registro.getVehiculosRegistrados();

        // Configurar la lista de vehículos en el ListView
        ListView<Vehiculo> listViewVehiculos = new ListView<>();
        listViewVehiculos.setItems(FXCollections.observableList(vehiculosRegistrados));

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

