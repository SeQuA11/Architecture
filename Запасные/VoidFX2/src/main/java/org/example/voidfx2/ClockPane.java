package org.example.voidfx2;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.time.LocalTime;

class ClockPane extends Pane {
    private int hour;       // Текущий час
    private int minute;     // Текущая минута
    private int second;     // Текущая секунда

    private double w = 250, h = 250; // Ширина и высота панели, на которой рисуются часы

    // Конструктор по умолчанию. Инициализирует часы текущим временем
    public ClockPane() {
        setCurrentTime();
    }

    // Конструктор, позволяющий задать время при создании объекта
    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        paintClock(); // Отрисовываем часы с заданным временем
    }

    // Геттеры и сеттеры для доступа к переменным hour, minute, second, w, h

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        paintClock(); // Перерисовываем часы после изменения времени
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        paintClock(); // Перерисовываем часы после изменения времени
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        paintClock(); // Перерисовываем часы после изменения времени
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
        paintClock(); // Перерисовываем часы после изменения размеров
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
        paintClock(); // Перерисовываем часы после изменения размеров
    }

    // Метод для установки текущего времени
    public void setCurrentTime() {
        LocalTime time = LocalTime.now(); // Получаем текущее время из LocalTime
        this.hour = time.getHour();     // Устанавливаем час
        this.minute = time.getMinute();   // Устанавливаем минуту
        this.second = time.getSecond();   // Устанавливаем секунду

        paintClock(); // Отрисовываем часы с новым временем
    }

    // Метод для отрисовки часов
    protected void paintClock() {
        // Вычисляем радиус часов, основываясь на меньшей из ширины и высоты
        double clockRadius = Math.min(w, h) * 0.5 * 0.75;
        double centerX = w / 2; // Вычисляем координату X центра
        double centerY = h / 2; // Вычисляем координату Y центра



        // Рисуем круг (циферблат)
        Circle circle = new Circle(centerX, centerY, clockRadius); // Создаем круг
        circle.setFill(Color.TRANSPARENT);   // Задаем белый цвет заливки
        circle.setStroke(Color.BLACK);  // Задаем черный цвет обводки
        getChildren().clear();        // Очищаем панель от предыдущих элементов
        getChildren().add(circle);   // Добавляем круг на панель

        Rectangle square = new Rectangle(centerX - w/2, centerY - h/2, w, h); // Рисуем квадрат в центре панели
        square.setFill(Color.TRANSPARENT);       // Заливаем белым цветом
        square.setStroke(Color.BLACK);      // Обводим черным цветом
        getChildren().add(square);         // Добавляем квадрат на панель

        // Рисуем цифры от 1 до 12
        for (int i = 1; i <= 12; i++) {
            // Вычисляем угол для каждой цифры
            double angleNumber = i * 2 * Math.PI / 12 - Math.PI / 2; // Располагаем 12 сверху
            // Вычисляем координаты X и Y для каждой цифры
            double x = centerX + clockRadius * 1.2 * Math.cos(angleNumber);
            double y = centerY + clockRadius * 1.2 * Math.sin(angleNumber);
            // Создаем текстовый объект для каждой цифры
            javafx.scene.text.Text text = new javafx.scene.text.Text(x - 4, y + 4, String.valueOf(i)); // Adjust position for better appearance
            getChildren().add(text); // Добавляем цифру на панель
        }

        for (int i = 0; i < 60; i++) {
            double angle = i * 2 * Math.PI / 12 - Math.PI / 2; // Вычисляем угол метки
            double tickStartRadius = clockRadius * 1.05;         // Радиус начала метки
            double tickEndRadius = (i % 5 == 0) ? clockRadius * 0.9 : clockRadius * 0.95; // Радиус конца метки (длинные метки для часов)

            // Вычисляем координаты начала и конца метки
            double startX = centerX + tickStartRadius * Math.cos(angle);
            double startY = centerY + tickStartRadius * Math.sin(angle);
            double endX = centerX + tickEndRadius * Math.cos(angle);
            double endY = centerY + tickEndRadius * Math.sin(angle);

            // Рисуем метку
            Line tick = new Line(startX, startY, endX, endY);
            tick.setStroke(Color.BLACK);
            getChildren().add(tick);
        }

        for (int i = 0; i < 6; i++) {
            double angle = i * 2 * Math.PI / 60 - Math.PI / 2; // Вычисляем угол метки
            double tickStartRadius = clockRadius * 1;         // Радиус начала метки
            double tickEndRadius = (i % 5 == 0) ? clockRadius * 0.9 : clockRadius * 0.95; // Радиус конца метки (длинные метки для часов)

            // Вычисляем координаты начала и конца метки
            double startX = centerX + tickStartRadius * Math.cos(angle);
            double startY = centerY + tickStartRadius * Math.sin(angle);
            double endX = centerX + tickEndRadius * Math.cos(angle);
            double endY = centerY + tickEndRadius * Math.sin(angle);

            // Рисуем метку
            Line tick = new Line(startX, startY, endX, endY);
            tick.setStroke(Color.BLACK);
            getChildren().add(tick);
        }

        // Рисуем секундную стрелку
        double sLength = clockRadius * 0.8; // Вычисляем длину секундной стрелки
        // Вычисляем координаты X и Y конца секундной стрелки
        double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY); // Создаем линию для секундной стрелки
        sLine.setStroke(Color.RED);  // Задаем красный цвет секундной стрелки
        getChildren().add(sLine);    // Добавляем секундную стрелку на панель

        // Рисуем минутную стрелку
        double mLength = clockRadius * 0.65; // Вычисляем длину минутной стрелки
        // Вычисляем координаты X и Y конца минутной стрелки
        double minuteX = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, minuteX, minuteY); // Создаем линию для минутной стрелки
        mLine.setStroke(Color.BLACK); // Задаем синий цвет минутной стрелки
        getChildren().add(mLine);   // Добавляем минутную стрелку на панель

        // Рисуем часовую стрелку
        double hLength = clockRadius * 0.5; // Вычисляем длину часовой стрелки
        // Вычисляем координаты X и Y конца часовой стрелки
        double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)); // учет минут для более точного положения часовой стрелки
        Line hLine = new Line(centerX, centerY, hourX, hourY); // Создаем линию для часовой стрелки
        hLine.setStroke(Color.BLACK); // Задаем зеленый цвет часовой стрелки
        hLine.setStrokeWidth(3);
        getChildren().add(hLine);    // Добавляем часовую стрелку на панель
    }

    // Переопределяем методы setWidth и setHeight, чтобы часы перерисовывались при изменении размеров панели
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        this.w = width;
        paintClock(); // Перерисовываем часы
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        this.h = height;
        paintClock(); // Перерисовываем часы
    }
}
