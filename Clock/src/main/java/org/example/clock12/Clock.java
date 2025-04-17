package org.example.clock12;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Clock extends Pane {
    private int hour;       // Текущий час
    private int minute;     // Текущая минута
    private int second;     // Текущая секунда

    // Конструктор по умолчанию. Инициализирует часы текущим временем
    public Clock() {
        setCurrentTime();
    }

    public void setCurrentTime() {
        LocalTime time = LocalTime.now(); // Получаем текущее время из LocalTime
        this.hour = time.getHour();     // Устанавливаем час
        this.minute = time.getMinute();   // Устанавливаем минуту
        this.second = time.getSecond();   // Устанавливаем секунду

        paintClock(); // Отрисовываем часы с новым временем
    }

    protected void paintClock() {

        //---------------------------------------
        //-----------------ДАТА------------------
        //---------------------------------------

        // Получает текущую дату
        LocalDate currentDate = LocalDate.now();

        // Форматируем дату

        // Создаем объект DateTimeFormatter для указания желаемого формата даты.
        // В данном случае, формат “dd.MM.yyyy” означает “день.месяц.год”.
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Используем метод format() объекта LocalDate
        // для преобразования даты в строку, используя указанный форматтер.
        String formattedDate = currentDate.format(formater);


        // Создаём элемент (метка?) для отображения даты
        Label dateLabel = new Label(formattedDate);

        // Устанавливаем размер шрифта в пунктах
        double fontSize = 24;
        // Создает новый объект Font с указанным размером шрифта
        dateLabel.setFont(Font.font(fontSize));

        // Задаем координаты элемента (метки)
        double CoordinateX = 350; // по горизонтали
        double CoordinateY = 452; // по вертикали
        dateLabel.setLayoutX(CoordinateX); // Устанавливаем X-координату элемента (метки)
        dateLabel.setLayoutY(CoordinateY); // Устанавливаем Y-координату элемента (метки)

        // Метод для отрисовки часов
        // Вычисляем радиус часов
        double clockRadius = 150;
        double centerX = 240;  // Вычисляем координату X центра
        double centerY = 240; // Вычисляем координату Y центра

        // Рисуем круг (циферблат)
        Circle circle = new Circle(centerX, centerY, clockRadius); // Создаем круг
        circle.setFill(Color.TRANSPARENT); // Задаем белый цвет заливки
        circle.setStroke(Color.BLACK);    // Задаем черный цвет обводки
        getChildren().clear();           // Очищаем панель от предыдущих элементов
        getChildren().add(circle);      // Добавляем круг на панель

        // Квадрат
        Rectangle square = new Rectangle(40, 40, 400, 400); // x, y, width, height
        square.setFill(Color.TRANSPARENT); // Устанавливаем цвет заливки
        square.setStroke(Color.BLACK); // Устанавливаем цвет линии
        square.setStrokeWidth(3); // Устанавливаем толщину линии
        getChildren().add(square); // Добавляем квадрат на панель

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

        for (int i = 0; i < 5; i++) {
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
}