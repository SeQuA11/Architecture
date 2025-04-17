package org.example.clock12;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.time.LocalTime;

class Clock extends Pane {
    private int hour;       // Текущий час
    private int minute;     // Текущая минута
    private int second;     // Текущая секунда

    private double w = 250, h = 250; // Ширина и высота панели, на которой рисуются часы

    // Конструктор по умолчанию. Инициализирует часы текущим временем
    public Clock() {
        setCurrentTime();
    }

    // Конструктор, позволяющий задать время при создании объекта
    public Clock(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        paintClock(); // Отрисовываем часы с заданным временем
    }

    public void setCurrentTime() {
        LocalTime time = LocalTime.now(); // Получаем текущее время из LocalTime
        this.hour = time.getHour();     // Устанавливаем час
        this.minute = time.getMinute();   // Устанавливаем минуту
        this.second = time.getSecond();   // Устанавливаем секунду

        paintClock(); // Отрисовываем часы с новым временем
    }

    protected void paintClock() {

        // Квадрат
        Rectangle square = new Rectangle(40, 40, 400, 400); // x, y, width, height
        square.setFill(Color.TRANSPARENT); // Устанавливаем цвет заливки
        square.setStroke(Color.BLACK);
        square.setStrokeWidth(3);

        // Добавляем квадрат на панель
        getChildren().add(square);

        // Боковые линии снизу

// Создаём дугу
        Arc arc = new Arc();

        // Определяем координаты левой и правой точек дуги
        double leftX = 100; // координата левой точки (начало дуги)
        double rightX = 540; // координата правой точки (конец дуги)

        double topY = 570; // Y-координата верхней точки (центр по вертикали)
        double arcHeight = 50; // Высота всей дуги (диаметр по вертикали)

        // Вычисляем центр и радиусы
        double centerX2 = (leftX + rightX) / 2;
        double radiusX = (rightX - leftX) / 2;
        double radiusY = arcHeight / 2;

        arc.setCenterX(centerX2); //  Устанавливает координату X центра эллипса.
        arc.setCenterY(topY); // Устанавливает координату Y центра эллипса.
        arc.setRadiusX(radiusX); //  Устанавливает горизонтальный радиус (радиус по оси X) эллипса.
        arc.setRadiusY(radiusY); // Устанавливает вертикальный радиус (радиус по оси Y) эллипса.

        // Определяем углы начала и длины дуги
        arc.setStartAngle(0); // Угол начала дуги (0 градусов - правая сторона)
        arc.setLength(180); // Длина дуги (180 градусов - половина эллипса/левая сторона)

        // Тип замыкания дуги (как она соединяет концы)
        arc.setType(ArcType.OPEN); // OPEN - для незамкнутой дуги
        arc.setFill(Color.TRANSPARENT); // Устанавливаем цвет заливки на прозрачный
        arc.setStroke(Color.BLACK);
        getChildren().add(arc);
    }
}