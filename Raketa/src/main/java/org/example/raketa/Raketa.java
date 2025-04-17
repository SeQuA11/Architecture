package org.example.raketa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.Random;

public class Raketa extends Application {

    // Графические элементы ракеты
    private Rectangle Korpus; // Корпус ракеты
    private Polygon noseCone; // Верхний треугольник
    private Circle illuminator; // Иллюминатор
    private Polygon triL; // Левый нижний треугольник
    private Polygon triR; // Правый нижний треугольник
    private Line supportLine; // Опорная линия
    private Button launchButton; // Кнопка запуска

    // Координаты для позиции ракеты
    private final double x = 105; // Смещение по X
    private final double y = 110; // Смещение по Y
    private final Random random = new Random(); // Генератор случайных чисел для небольших вариаций

    @Override
    public void start(Stage stage) {
        // Размеры и параметры ракеты
        final double korpusW = 50; // Ширина корпуса ракеты
        final double korpusH = 140; // Высота корпуса ракеты
        final double finHeight = 35; // Высота стабилизаторов
        final double aRadius = 20; // Радиус иллюминатора
        final double buttonW = 85; // Ширина кнопки
        final double buttonH = 25; // Высота кнопки
        final double line = korpusW * 0.75; // Длина опорной линии

        // Создание компонентов ракеты

        // Корпус
        Korpus = new Rectangle(x, y, korpusW, korpusH);
        Korpus.setFill(Color.GREY);
        Korpus.setStroke(Color.BLACK);

        // Верхний треугольник
        noseCone = new Polygon(x, y, x + korpusW, y, x + korpusW / 2, y - finHeight);
        noseCone.setFill(Color.GREY);
        noseCone.setStroke(Color.BLACK);

        // Иллюминатор
        illuminator = new Circle(x + korpusW / 2, y + korpusH / 2 - 40, aRadius);
        illuminator.setFill(Color.BLUE);
        illuminator.setStroke(Color.BLACK);

        // Опорная линия
        supportLine = new Line(x + korpusW * (1 - line/korpusW)/2, y + korpusH, x + korpusW * (1 + line/korpusW)/2, y + korpusH);
        supportLine.setStroke(Color.BLACK);

        // Левый стабилизатор
        triL = new Polygon(
                x + korpusW * (1 - line/korpusW)/2 - finHeight / 2 , y + korpusH,
                x + korpusW * (1 - line/korpusW)/2 + finHeight / 2, y + korpusH,
                x + korpusW * (1 - line/korpusW)/2, y + korpusH + finHeight
        );
        triL.setFill(Color.WHITE);
        triL.setStroke(Color.TRANSPARENT);

        // Правый стабилизатор
        triR = new Polygon(
                x + korpusW * (1 + line/korpusW)/2 - finHeight / 2, y + korpusH,
                x + korpusW * (1 + line/korpusW)/2 + finHeight / 2, y + korpusH,
                x + korpusW * (1 + line/korpusW)/2, y + korpusH + finHeight
        );
        triR.setFill(Color.WHITE);
        triR.setStroke(Color.TRANSPARENT);

        // Кнопка запуска
        launchButton = new Button("Запуск");
        launchButton.setPrefWidth(buttonW);
        launchButton.setPrefHeight(buttonH);
        launchButton.setLayoutX(x + (korpusW - buttonW) / 2 + (random.nextDouble() - 0.5) * 5); // Случайный сдвиг
        launchButton.setLayoutY(y + korpusH + finHeight + 15 + (random.nextDouble() - 0.5) * 5); // Случайный сдвиг

        // Обработчик события для кнопки запуска
        launchButton.setOnAction(event -> { // Лямбда-выражение
            triL.setFill(Color.RED);
            triR.setFill(Color.RED);
            illuminator.setFill(Color.GREEN);
        });

        // Создание и настройка сцены
        Pane root = new Pane();
        root.getChildren().addAll(Korpus, noseCone, illuminator, supportLine, triL, triR, launchButton);

        Scene scene = new Scene(root, 250, 400);
        stage.setTitle("Проект Ракета");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
