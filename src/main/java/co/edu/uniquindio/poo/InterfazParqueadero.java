package co.edu.uniquindio.poo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Modality;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDateTime;


public class InterfazParqueadero extends Application {

    private Parqueadero parqueadero;
    private Registro registro;

    @Override
    public void start(Stage primaryStage) {
        parqueadero = new Parqueadero();
        registro = new Registro();

        // Botones para acceder a la info
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
        root.getChildren().addAll(infoParqueaderoBtn,registrarVehiculoBtn, generarReporteBtn, verVehiculosBtn);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Interfaz Parqueadero");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarVentanaInfoParqueadero() {
        Stage ventanaInfoParqueadero = new Stage();
        ventanaInfoParqueadero.initModality(Modality.APPLICATION_MODAL);
        ventanaInfoParqueadero.setTitle("Información del Parqueadero");

        // Componentes para ingresar información del parqueadero (filas, columnas, tarifas)
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
            // Obtener la información ingresada por el usuario
            int filas = Integer.parseInt(filasField.getText());
            int columnas = Integer.parseInt(columnasField.getText());
            int tarifaCarro = Integer.parseInt(tarifaCarroField.getText());
            int tarifaMotoH = Integer.parseInt(tarifaMotoHField.getText());
            int tarifaMotoC = Integer.parseInt(tarifaMotoCField.getText());

            // Configurar el parqueadero con la información ingresada
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
        velocidadField.setVisible(false); // Inicialmente oculto

        // Para mostrar la velocidad solo si se selecciona moto híbrida o clásica
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
            // Obtener la información ingresada por el usuario
            String tipo = tipoChoiceBox.getValue();
            String placa = placaField.getText();
            String modelo = modeloField.getText();
            String propietario = propietarioField.getText();
            int fila = Integer.parseInt(filaField.getText());
            int columna = Integer.parseInt(columnaField.getText());
            LocalDateTime fechaEntrada = LocalDateTime.parse(fechaEntradaField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime fechaSalida = LocalDateTime.parse(fechaSalidaField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Crear el objeto vehículo según el tipo seleccionado
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

            // Verificar si la posición está ocupada
            if (parqueadero.verificarPuesto(fila, columna)) {
                // Mostrar mensaje de posición ocupada
                mostrarAlerta("Posición Ocupada", "La posición seleccionada ya está ocupada.");
            } else {
                // Registrar el vehículo en el parqueadero
                parqueadero.registrarVehiculo(fila, columna, vehiculo);
                ventanaRegistro.close();
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

        // Crea la tabla para mostrar el reporte monetario
        TableView<Vehiculo> tablaReporte = new TableView<>();

        // Configura las columnas de la tabla
        TableColumn<Vehiculo, String> placaCol = new TableColumn<>("Placa");
        placaCol.setCellValueFactory(new PropertyValueFactory<>("placa"));

        TableColumn<Vehiculo, String> modeloCol = new TableColumn<>("Modelo");
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Vehiculo, Double> costoCol = new TableColumn<>("Costo de Estacionamiento");
        costoCol.setCellValueFactory(cellData -> {
            Vehiculo vehiculo = cellData.getValue();
            double costo = Parqueadero.calcularCosto(vehiculo);
            return new SimpleDoubleProperty(costo).asObject();
        });

        // Agrega las columnas a la tabla
        tablaReporte.getColumns().addAll(placaCol, modeloCol, costoCol);

        // Establece los datos de la tabla
        tablaReporte.setItems(FXCollections.observableList(registro.getVehiculosRegistrados()));

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
