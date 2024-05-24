package co.edu.uniquindio.poo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.stage.Modality;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

import static co.edu.uniquindio.poo.Parqueadero.calcularCosto;

public class InterfazParqueadero extends Application {

    private Parqueadero parqueadero;
    private ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList();
    private List<Double> ingresosDiarios = new ArrayList<>();
    private List<Double> ingresosMensuales = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Button configurarParqueaderoBtn = new Button("Configurar Parqueadero");
        configurarParqueaderoBtn.setOnAction(e -> mostrarVentanaConfiguracion());

        Button registrarVehiculoBtn = new Button("Registrar Vehículo");
        registrarVehiculoBtn.setOnAction(e -> mostrarVentanaRegistroVehiculo());

        Button reporteMonetarioBtn = new Button("Reporte Monetario");
        reporteMonetarioBtn.setOnAction(e -> mostrarVentanaReporteMonetario());

        Button listaVehiculosBtn = new Button("Lista de Vehículos");
        listaVehiculosBtn.setOnAction(e -> mostrarVentanaListaVehiculos());

        Button registrarSalidaButton = new Button("Registrar Salida de Vehículo");
        registrarSalidaButton.setOnAction(e -> mostrarVentanaSalidaVehiculo());

        root.getChildren().addAll(configurarParqueaderoBtn, registrarVehiculoBtn,registrarSalidaButton, reporteMonetarioBtn, listaVehiculosBtn);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Parqueadero");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarVentanaConfiguracion() {
        Stage stage = new Stage();
        stage.setTitle("Configurar Parqueadero");
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        Label filasLabel = new Label("Filas:");
        TextField filasField = new TextField();

        Label columnasLabel = new Label("Columnas:");
        TextField columnasField = new TextField();

        Label tarifaMotoHLabel = new Label("Tarifa Moto Horaria:");
        TextField tarifaMotoHField = new TextField();

        Label tarifaMotoCLabel = new Label("Tarifa Moto Clásica:");
        TextField tarifaMotoCField = new TextField();

        Label tarifaCarroLabel = new Label("Tarifa Carro:");
        TextField tarifaCarroField = new TextField();

        Button guardarBtn = new Button("Guardar");
        guardarBtn.setOnAction(e -> {
            int filas = Integer.parseInt(filasField.getText());
            int columnas = Integer.parseInt(columnasField.getText());
            int tarifaMotoH = Integer.parseInt(tarifaMotoHField.getText());
            int tarifaMotoC = Integer.parseInt(tarifaMotoCField.getText());
            int tarifaCarro = Integer.parseInt(tarifaCarroField.getText());

            parqueadero = new Parqueadero(filas, columnas, tarifaMotoH, tarifaMotoC, tarifaCarro);

            stage.close();
        });

        gridPane.add(filasLabel, 0, 0);
        gridPane.add(filasField, 1, 0);
        gridPane.add(columnasLabel, 0, 1);
        gridPane.add(columnasField, 1, 1);
        gridPane.add(tarifaMotoHLabel, 0, 2);
        gridPane.add(tarifaMotoHField, 1, 2);
        gridPane.add(tarifaMotoCLabel, 0, 3);
        gridPane.add(tarifaMotoCField, 1, 3);
        gridPane.add(tarifaCarroLabel, 0, 4);
        gridPane.add(tarifaCarroField, 1, 4);
        gridPane.add(guardarBtn, 0, 5, 2, 1);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    private void mostrarVentanaRegistroVehiculo() {
        Stage stage = new Stage();
        stage.setTitle("Registrar Vehículo");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label placaLabel = new Label("Placa:");
        TextField placaField = new TextField();

        Label modeloLabel = new Label("Modelo:");
        TextField modeloField = new TextField();

        Label propietarioLabel = new Label("Propietario:");
        TextField propietarioField = new TextField();

        Label fechaEntradaLabel = new Label("Fecha de Entrada:");
        DatePicker fechaEntradaPicker = new DatePicker();

        Label horaEntradaLabel = new Label("Hora de Entrada:");
        TextField horaEntradaField = new TextField();

        Label filaLabel = new Label("Fila:");
        TextField filaField = new TextField();

        Label columnaLabel = new Label("Columna:");
        TextField columnaField = new TextField();

        Label tipoVehiculoLabel = new Label("Tipo de Vehículo:");
        ComboBox<String> tipoVehiculoComboBox = new ComboBox<>();
        tipoVehiculoComboBox.getItems().addAll("Carro", "Moto");

        Label tipoMotoLabel = new Label("Tipo de Moto:");
        ComboBox<TipoMoto> tipoMotoComboBox = new ComboBox<>();
        tipoMotoComboBox.getItems().addAll(TipoMoto.CLASICA, TipoMoto.HIBRIDA);
        tipoMotoComboBox.setDisable(true);

        Label velocidadMaximaLabel = new Label("Velocidad Máxima (Moto):");
        TextField velocidadMaximaField = new TextField();
        velocidadMaximaField.setDisable(true);

        tipoVehiculoComboBox.setOnAction(e -> {
            if (tipoVehiculoComboBox.getValue().equals("Moto")) {
                tipoMotoComboBox.setDisable(false);
                velocidadMaximaField.setDisable(false);
            } else {
                tipoMotoComboBox.setDisable(true);
                velocidadMaximaField.setDisable(true);
            }
        });

        Button registrarButton = new Button("Registrar");
        registrarButton.setOnAction(e -> {
            String placa = placaField.getText();
            String modelo = modeloField.getText();
            String propietario = propietarioField.getText();
            LocalDate fechaEntrada = fechaEntradaPicker.getValue();
            LocalTime horaEntrada = LocalTime.parse(horaEntradaField.getText());
            int fila = Integer.parseInt(filaField.getText());
            int columna = Integer.parseInt(columnaField.getText());

            // Convertir LocalDate y LocalTime a LocalDateTime
            LocalDateTime fechaEntradaDateTime = fechaEntrada.atTime(horaEntrada);

            Vehiculo vehiculo;

            if (tipoVehiculoComboBox.getValue().equals("Carro")) {
                vehiculo = new Carro(placa, modelo, propietario, fechaEntradaDateTime);
            } else {
                TipoMoto tipoMoto = tipoMotoComboBox.getValue();
                int velocidadMaxima = Integer.parseInt(velocidadMaximaField.getText());
                vehiculo = new Moto(placa, modelo, propietario, fechaEntradaDateTime, null, velocidadMaxima, tipoMoto);
            }

            // Registrar el vehículo en el parqueadero
            parqueadero.registrarVehiculo(fila, columna, vehiculo);

            // Registrar el vehículo en la lista de todos los vehículos
            vehiculos.add(vehiculo);

            // Cerrar la ventana después de registrar el vehículo
            stage.close();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(placaLabel, 0, 0);
        gridPane.add(placaField, 1, 0);
        gridPane.add(modeloLabel, 0, 1);
        gridPane.add(modeloField, 1, 1);
        gridPane.add(propietarioLabel, 0, 2);
        gridPane.add(propietarioField, 1, 2);
        gridPane.add(fechaEntradaLabel, 0, 3);
        gridPane.add(fechaEntradaPicker, 1, 3);
        gridPane.add(horaEntradaLabel, 0, 4);
        gridPane.add(horaEntradaField, 1, 4);
        gridPane.add(filaLabel, 0, 5);
        gridPane.add(filaField, 1, 5);
        gridPane.add(columnaLabel, 0, 6);
        gridPane.add(columnaField, 1, 6);
        gridPane.add(tipoVehiculoLabel, 0, 7);
        gridPane.add(tipoVehiculoComboBox, 1, 7);
        gridPane.add(tipoMotoLabel, 0, 8);
        gridPane.add(tipoMotoComboBox, 1, 8);
        gridPane.add(velocidadMaximaLabel, 0, 9);
        gridPane.add(velocidadMaximaField, 1, 9);
        gridPane.add(registrarButton, 1, 10);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void mostrarVentanaSalidaVehiculo() {
        Stage stage = new Stage();
        stage.setTitle("Registrar Salida de Vehículo");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label placaLabel = new Label("Placa:");
        TextField placaField = new TextField();

        Label fechaSalidaLabel = new Label("Fecha de Salida:");
        DatePicker fechaSalidaPicker = new DatePicker();

        Label horaSalidaLabel = new Label("Hora de Salida:");
        TextField horaSalidaField = new TextField();

        Button registrarSalidaButton = new Button("Registrar Salida");
        registrarSalidaButton.setOnAction(e -> {
            String placa = placaField.getText();
            LocalDate fechaSalida = fechaSalidaPicker.getValue();
            LocalTime horaSalida = LocalTime.parse(horaSalidaField.getText());

            // Registrar la salida del vehículo en el parqueadero
            try {
                parqueadero.registrarSalidaVehiculo(placa, fechaSalida, horaSalida);
                // Cerrar la ventana después de registrar la salida del vehículo
                stage.close();
            } catch (IllegalArgumentException ex) {
                // Manejar el error si el vehículo no se encuentra
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Vehículo no encontrado");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(placaLabel, 0, 0);
        gridPane.add(placaField, 1, 0);
        gridPane.add(fechaSalidaLabel, 0, 1);
        gridPane.add(fechaSalidaPicker, 1, 1);
        gridPane.add(horaSalidaLabel, 0, 2);
        gridPane.add(horaSalidaField, 1, 2);
        gridPane.add(registrarSalidaButton, 1, 3);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();
    }


    private void mostrarVentanaReporteMonetario() {
        // Crear la ventana del reporte monetario
        Stage stage = new Stage();
        stage.setTitle("Reporte Monetario");
        stage.initModality(Modality.APPLICATION_MODAL);

        // Crear la tabla para mostrar el reporte
        TableView<Ingreso> tableView = new TableView<>();
        TableColumn<Ingreso, String> ingresosDiariosColumn = new TableColumn<>("Fecha");
        ingresosDiariosColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Ingreso, Double> totalDiarioColumn = new TableColumn<>("Total Diario");
        totalDiarioColumn.setCellValueFactory(new PropertyValueFactory<>("totalDiario"));

        tableView.getColumns().addAll(ingresosDiariosColumn, totalDiarioColumn);
        tableView.setItems(getIngresosDiarios());

        // Crear el contenedor para la tabla
        VBox vbox = new VBox(tableView);

        // Crear el botón para calcular el costo del vehículo
        Button calcularCostoBtn = new Button("Calcular Costo del Vehículo");
        calcularCostoBtn.setOnAction(e -> mostrarVentanaCalcularCosto());

        // Agregar la tabla y el botón al contenedor principal
        vbox.getChildren().addAll(calcularCostoBtn);

        // Crear la escena y mostrar la ventana
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private ObservableList<Ingreso> getIngresosDiarios() {
        ObservableList<Ingreso> ingresos = FXCollections.observableArrayList();
        for (int i = 0; i < ingresosDiarios.size(); i++) {
            ingresos.add(new Ingreso("Dia " + (i + 1), ingresosDiarios.get(i)));
        }
        return ingresos;
    }

    private void mostrarVentanaCalcularCosto() {
        Stage stage = new Stage();
        stage.setTitle("Calcular Costo del Vehículo");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label placaLabel = new Label("Placa:");
        TextField placaField = new TextField();

        Button calcularBtn = new Button("Calcular");
        calcularBtn.setOnAction(e -> {
            String placa = placaField.getText();
            Vehiculo vehiculo = parqueadero.buscarVehiculoPorPlaca(placa);

            if (vehiculo != null) {
                try {
                    double costo = Parqueadero.calcularCosto(vehiculo);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Costo del Vehículo");
                    alert.setHeaderText(null);
                    alert.setContentText("El costo a pagar por el vehículo con placa " + placa + " es: $" + costo);
                    alert.showAndWait();
                } catch (IllegalArgumentException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se encontró un vehículo con la placa " + placa);
                alert.showAndWait();
            }

            stage.close();
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(placaLabel, placaField, calcularBtn);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }


    private void mostrarVentanaListaVehiculos() {
        Stage stage = new Stage();
        stage.setTitle("Lista de Vehículos");
        stage.initModality(Modality.APPLICATION_MODAL);

        ListView<String> listView = new ListView<>();
        for (Vehiculo vehiculo : vehiculos) {
            String infoVehiculo = "Placa: " + vehiculo.getPlaca() +
                    ", Modelo: " + vehiculo.getModelo() +
                    ", Propietario: " + vehiculo.getPropietario();

            // Agregar la posición del vehículo en el parqueadero
            infoVehiculo += ", Posición: " + obtenerPosicionVehiculo(vehiculo);

            // Agregar las fechas de entrada y salida si están disponibles
            if (vehiculo.getFechaEntrada() != null) {
                infoVehiculo += ", Fecha de Entrada: " + vehiculo.getFechaEntrada();
            }
            if (vehiculo.getFechaSalida() != null) {
                infoVehiculo += ", Fecha de Salida: " + vehiculo.getFechaSalida();
            }

            listView.getItems().add(infoVehiculo);
        }

        VBox vbox = new VBox(listView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private String obtenerPosicionVehiculo(Vehiculo vehiculo) {
        List<List<Vehiculo>> registroVehiculo = parqueadero.getRegistroVehiculo();
        for (int fila = 0; fila < registroVehiculo.size(); fila++) {
            for (int columna = 0; columna < registroVehiculo.get(fila).size(); columna++) {
                if (registroVehiculo.get(fila).get(columna) == vehiculo) {
                    return "Fila: " + fila + ", Columna: " + columna;
                }
            }
        }
        return "No encontrado";
    }

    public static class Ingreso {
        private final String fecha;
        private final double totalDiario;

        public Ingreso(String fecha, double totalDiario) {
            this.fecha = fecha;
            this.totalDiario = totalDiario;
        }

        public String getFecha() {
            return fecha;
        }

        public double getTotalDiario() {
            return totalDiario;
        }
    }
}

