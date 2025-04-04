package org.example.voidfx2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Clock extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Создаем экземпляр класса ClockPane, который отвечает за отрисовку часов
        ClockPane clock = new ClockPane();

        // Создаем текстовое поле для отображения даты
        Text dateText = new Text();
        dateText.setStyle("-fx-font-size: 20px;"); // Устанавливаем размер шрифта для даты

        // Создаем VBox для размещения текстового поля с датой внизу
        VBox bottomPanel = new VBox(dateText);
        bottomPanel.setAlignment(Pos.CENTER); // Выравниваем текст по центру
        bottomPanel.setPadding(new Insets(10)); // Добавляем отступы

        // Создаем BorderPane для организации размещения ClockPane в окне
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(clock); // Размещаем ClockPane в центре BorderPane
        borderPane.setBottom(bottomPanel); // Размещаем дату внизу
        BorderPane.setMargin(clock, new Insets(12, 12, 0, 12)); // Добавляем отступы вокруг ClockPane

        // Создаем Timeline для обновления часов каждую секунду
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> { // Каждый 1 секунду (Duration.seconds(1))
                    clock.setCurrentTime(); // Вызываем метод setCurrentTime() для обновления времени на часах
                    dateText.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))); // Обновляем дату
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Устанавливаем бесконечное повторение анимации
        timeline.play(); // Запускаем анимацию

        // Создаем Scene (сцену) с BorderPane в качестве корневого элемента
        Scene scene = new Scene(borderPane, 400, 600); // Устанавливаем размер сцены 400x400
        primaryStage.setTitle("Мои часы"); // Устанавливаем заголовок окна
        primaryStage.setScene(scene); // Устанавливаем сцену на главное окно (Stage)
        primaryStage.show(); // Отображаем окно
    }

    public static void main(String[] args) {
        launch(args); // Запускаем JavaFX приложение
    }
}
