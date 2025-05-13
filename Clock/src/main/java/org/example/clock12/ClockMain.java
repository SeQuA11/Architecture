package org.example.clock12;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClockMain extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Создаем экземпляр класса ClockPane, который отвечает за отрисовку часов
        Clock clock = new Clock();

        // Создаем BorderPane для организации размещения ClockPane в окне
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(clock); // Размещаем ClockPane в центре BorderPane
        BorderPane.setMargin(clock, new Insets(20, 20, 0, 20)); // Добавляем отступы вокруг ClockPane

        // Создаем Timeline для обновления часов каждую секунду
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> { // Каждый 1 секунду
                    clock.setCurrentTime(); // Вызываем метод setCurrentTime() для обновления времени на часах
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Устанавливаем бесконечное повторение анимации
        timeline.play(); // Запускаем анимацию

        // Создаем Scene (сцену) с BorderPane в качестве корневого элемента
        Scene scene = new Scene(borderPane, 520, 600); // Устанавливаем размер сцены 400x400
        primaryStage.setResizable(false);
        primaryStage.setTitle("Мои часы"); // Устанавливаем заголовок окна
        primaryStage.setScene(scene); // Устанавливаем сцену на главное окно (Stage)
        primaryStage.show(); // Отображаем окно
    }

    public static void main(String[] args) {
        launch(args); // Запускаем JavaFX приложение
    }
}
