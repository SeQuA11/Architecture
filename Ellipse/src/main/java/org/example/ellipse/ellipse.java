package org.example.ellipse;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ellipse extends Application {

    private RotateTransition rotateTransition; // Объект для анимации вращения
    private boolean isRotating = false; // Флаг, показывающий, вращается ли эллипс
    private double rotationSpeed = 3.0; // Начальная скорость вращения

    @Override
    public void start(Stage primaryStage) {
        // 1. Создание элементов UI

        // Эллипс, который будет вращаться
        Ellipse ellipse = new Ellipse(100, 50); // Ширина 100, высота 50
        ellipse.setCenterX(150); // Центр по X
        ellipse.setCenterY(100); // Центр по Y
        ellipse.setFill(javafx.scene.paint.Color.RED); // Цвет заливки

        // Кнопка для запуска/остановки вращения
        Button startStopButton = new Button("Start/Stop");

        // Кнопка для изменения скорости вращения
        Button changeSpeedButton = new Button("Change Speed");

        // 2. Настройка анимации вращения

        // Создаем объект RotateTransition для вращения эллипса
        rotateTransition = new RotateTransition(Duration.seconds(rotationSpeed), ellipse);
        rotateTransition.setByAngle(360); // Вращение на 360 градусов
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Бесконечное вращение

        // 3. Обработчики событий для кнопок

        // Обработчик нажатия кнопки Start/Stop
        startStopButton.setOnAction(event -> {
            if (isRotating) {
                rotateTransition.pause(); // Остановить анимацию
                isRotating = false; // Установить флаг в false
                startStopButton.setText("Start"); // Изменить текст кнопки
            } else {
                rotateTransition.play(); // Запустить анимацию
                isRotating = true; // Установить флаг в true
                startStopButton.setText("Stop"); // Изменить текст кнопки
            }
        });

        // Обработчик нажатия кнопки Change Speed
        changeSpeedButton.setOnAction(event -> {
            rotationSpeed = rotationSpeed * 0.5;  // Уменьшаем скорость вдвое

            // Останавливаем текущую анимацию
            rotateTransition.stop();

            // Создаем новую анимацию с новой скоростью. Важно, чтобы не дублировать объект
            rotateTransition = new RotateTransition(Duration.seconds(rotationSpeed), ellipse);
            rotateTransition.setByAngle(360); // Вращение на 360 градусов
            rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Бесконечное вращение

            // Если эллипс вращался, запускаем новую анимацию
            if(isRotating){
                rotateTransition.play();
            }

            System.out.println("Speed changed to: " + rotationSpeed); // Выводим скорость в консоль (для отладки)
        });


        // 4. Компоновка UI

        // Создаем вертикальный контейнер для размещения элементов управления
        VBox root = new VBox(10); // Отступ между элементами 10 пикселей
        root.getChildren().addAll(ellipse, startStopButton, changeSpeedButton); // Добавляем элементы
        root.setStyle("-fx-padding: 20px;"); // Отступ от краев окна

        // 5. Создание и отображение Scene

        // Создаем сцену с указанным размером и корневым элементом
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Rotating Ellipse"); // Устанавливаем заголовок окна
        primaryStage.setScene(scene); // Устанавливаем сцену для отображения
        primaryStage.show(); // Отображаем окно
    }

    public static void main(String[] args) {
        launch(args); // Запускаем приложение JavaFX
    }
}
