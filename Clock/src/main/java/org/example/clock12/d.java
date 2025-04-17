package org.example.clock12;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application; // Основной класс для JavaFX приложений.
import javafx.scene.Scene; // Контейнер для всех видимых элементов.
import javafx.scene.control.Label;
import javafx.scene.layout.Pane; // Простой контейнер, в котором мы можем размещать линии.
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage; // Основное окно приложения.
import javafx.util.Duration;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class d extends Application {
    Circle circle;
    Circle littleCircle;
    Line hourHand;
    Line minutesHand;
    Line secondHand;
    Polygon hourArrow;
    Polygon minutesArrow;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        // Создаём метку
        Label timeLabel = new Label();

        // Устанавливаем размер шрифта в пунктах
        double fontSize2 = 24;
        // Создает новый объект Font с указанным размером шрифта
        timeLabel.setFont(Font.font(fontSize2));

        /* // Изменяем формат даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");*/

        double LimeLabelCoordinateX = 283;
        double LimeLabelCoordinateY = 100;
        timeLabel.setLayoutX(LimeLabelCoordinateX);
        timeLabel.setLayoutY(LimeLabelCoordinateY);

        // Обновление времени каждую секунду
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalTime currentTime = LocalTime.now();

                    // Извлекаем часы, минуты и секунды из LocalTime как целые числа
                    int hour = currentTime.getHour();
                    int minutes = currentTime.getMinute();
                    int second = currentTime.getSecond();

                    /* Используем метод String.format() для создания отформатированной строки
                       %d: это спецификатор формата для целых чисел (int)
                       час., мин., сек. - текст (формати?) для отображения */
                    String formattedTime = String.format("%d час. %d мин. %d сек.", hour, minutes, second);
                    timeLabel.setText(formattedTime); // Устанавливаем отформатированную строку в Label (метку)
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // бесконечно обновляем таймер
        timeline.play(); // запускаем время

        /*// Обновление времени каждую секунду
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalTime currentTime = LocalTime.now();
                    timeLabel.setText(currentTime.format(formatter));
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // бесконечно обновляем таймер
        timeline.play(); // запускаем время*/


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



        //  [Создает контейнер Pane, на котором мы будем рисовать трапецию]
        //  [Pane позволяет размещать узлы (в нашем случае, линии)]

        // создаём панель для рисования
        Pane root = new Pane(dateLabel, timeLabel);


        //---------------------------------------
        //-----------------ТРАПЕЦИЯ--------------
        //---------------------------------------

        //Определяем координаты углов трапеции
        double x1 = 220;  // Левая верхняя точка
        double y1 = 100;
        double x2 = 580;  // Правая верхняя точка
        double y2 = 100;
        double x3 = 700;  // Правая нижняя точка
        double y3 = 450;
        double x4 = 100;   // Левая нижняя точка
        double y4 = 450;

        //----------Нижняя часть часов-----------

        // левый край
        double x5 = 100;
        double y5 = 450;
        double x6 = 220;
        double y6 = 570;

        // Правый край
        double x7 = 700;
        double y7 = 450;
        double x8 = 580;
        double y8 = 570;

        //-------Линии по горизонтали------
        // левая линия
        double x9 = 220;
        double y9 = 570;
        double x10 = 250;
        double y10 = 570;

        // правая линия
        double x11 = 580;
        double y11 = 570;
        double x12 = 540;
        double y12 = 570;

        // [Line - cоздает линию, соединяющую точки (x1, y1) и (x2, y2) и так далее]

        // Соединяем линии и создаём стороны трапеции
        Line line1 = new Line(x1,y1, x2, y2);
        Line line2 = new Line(x2,y2, x3, y3);
        Line line3 = new Line(x3,y3, x4, y4);
        Line line4 = new Line(x4,y4, x1, y1);

        // Нижняя часть
        Line line5 = new Line(x5, y5, x6, y6);
        Line line6 = new Line(x7, y7, x8, y8);

        // линии по горизонтали
        Line line7 = new Line(x9, y9, x10, y10);
        Line line8 = new Line(x11, y11, x12, y12);

        // [Добавляет все линии на панель Pane. Линии будут отображаться в окне]
        root.getChildren().addAll(line1, line2, line3, line4, line5, line6,
                line7, line8);


        //---------------------------------------
        //-----------------ДУГА-----------------
        //---------------------------------------

        // Создаём дугу
        Arc arc = new Arc();

        // Определяем координаты левой и правой точек дуги
        double leftX = 250; // координата левой точки (начало дуги)
        double rightX = 540; // координата правой точки (конец дуги)

        double topY = 570; // Y-координата верхней точки (центр по вертикали)
        double arcHeight = 50; // Высота всей дуги (диаметр по вертикали)

        // Вычисляем центр и радиусы
        double centerX = (leftX + rightX) / 2;
        double radiusX = (rightX - leftX) / 2;
        double radiusY = arcHeight / 2;

        arc.setCenterX(centerX); //  Устанавливает координату X центра эллипса.
        arc.setCenterY(topY); // Устанавливает координату Y центра эллипса.
        arc.setRadiusX(radiusX); //  Устанавливает горизонтальный радиус (радиус по оси X) эллипса.
        arc.setRadiusY(radiusY); // Устанавливает вертикальный радиус (радиус по оси Y) эллипса.

        /*arc.setCenterX(leftX);
        arc.setCenterY(rightX);
        arc.setRadiusX(topY);
        arc.setRadiusY(arcHeight);*/

        // Определяем углы начала и длины дуги
        arc.setStartAngle(0); // Угол начала дуги (0 градусов - правая сторона)
        arc.setLength(180); // Длина дуги (180 градусов - половина эллипса/левая сторона)

        // Тип замыкания дуги (как она соединяет концы)
        arc.setType(ArcType.OPEN); // OPEN - для незамкнутой дуги

        // Заливка
        arc.setFill(Color.TRANSPARENT); // Устанавливаем цвет заливки на прозрачный

        // Цвет контура
        arc.setStroke(Color.BLACK);


        // [Добавляем дугу в Pane, откуда она рисуется и повяляется в нашей сцене]
        root.getChildren().add(arc);

        //---------------------------
        //---------КРУГ(ЧАСЫ)--------
        //---------------------------

        circle = new Circle();
        circle.setCenterX(400);
        circle.setCenterY(290);
        circle.setRadius(120);
        circle.setStroke(Color.BLACK); // Цвет контура
        circle.setFill(Color.TRANSPARENT); // Заливка

        root.getChildren().add(circle);


        // Создаём стрелки часов
        hourHand = new Line();
        hourHand.setStroke(Color.BLACK); // цвет стрелки
        hourHand.setStrokeWidth(3); // Длина стрелки
        root.getChildren().add(hourHand);

        minutesHand = new Line();
        minutesHand.setStroke(Color.BLACK); // цвет стрелки
        minutesHand.setStrokeWidth(2); // Длина стрелки
        root.getChildren().add(minutesHand);

        secondHand = new Line();
        secondHand.setStroke(Color.RED); // цвет стрелки
        root.getChildren().add(secondHand);

        // Создаём маленький круг по середине
        littleCircle = new Circle();
        littleCircle.setCenterX(circle.getCenterX());
        littleCircle.setCenterY(circle.getCenterY());
        littleCircle.setRadius(5);
        littleCircle.setStroke(Color.BLACK); // Цвет контура

        root.getChildren().add(littleCircle);


        // Создаем наконечники стрелок
        hourArrow = createArrow();
        minutesArrow = createArrow();

        hourArrow.setFill(Color.PURPLE);
        hourArrow.setStroke(Color.BLACK);
        minutesArrow.setFill(Color.ORANGE);
        minutesArrow.setStroke(Color.BLACK);


        root.getChildren().addAll(hourArrow, minutesArrow);  // Добавляем на сцену



        // Добавляем линии деления часа
        for (int i = 0; i < 12; i++) {

            // 360 градусов / 12 часов = 30 градусов на час

            /*Вычисляем угол для каждого часа (0, 30, 60, …, 330 градусов).
            Так как полный круг - 360 градусов, и на часах 12 делений,
            каждое деление отстоит от другого на 30 градусов.

            Потом Преобразуем угол из градусов в радианы,
            так как функции Math.sin() и Math.cos() принимают углы в радианах
            */
            double angle = Math.toRadians(i * 30);

            // Координаты начала линии (окружности круга)

            /* 1. Получаем координаты центра круга
             *  2. Получаем радиус круга
             *  3. Используем синус и косинус для определения координат точки
             *  на окружности круга, соответствующей текущему углу.
             * */

            double startX = circle.getCenterX() + circle.getRadius() * Math.sin(angle);
            double startY = circle.getCenterY() + circle.getRadius() * Math.cos(angle);


            // Координаты конца линии (внутрь окружности)
            /* Вычисляем координаты конца линии. Умножаем радиус на 0.9,
               чтобы линия была немного короче радиуса и не выходила за пределы круга.
            */
            double endX = circle.getCenterX() + (circle.getRadius() * 0.9) * Math.sin(angle);
            double endY = circle.getCenterY() + (circle.getRadius() * 0.9) * Math.cos(angle);

            // Создаем Line, задавая координаты начала и конца линии.
            Line lineDel = new Line(startX, startY, endX, endY);
            lineDel.setStroke(Color.BLACK); // Цвет линии - чёрный
            root.getChildren().add(lineDel); // Добавляем на сцену (приложение)

        }

        // Добавляем деления для минут
        double startAngle = Math.toRadians(0); // Угол для 12 часов - 0 градусов
        double endAngle = Math.toRadians(30); // Угол для 1 часа - 30 градусов
        int minutesDivisions = 4; // Количество делений (4 деления для минут)

        /* Проходим по всем делениям минут
         *  1. Определяются углы начала и конца для сектора, где нужно нарисовать деления минут
         *  2. В цикле вычисляется угол для каждой линии минут, равномерно распределяя линии в заданном секторе.
         *     Укорачиваем деления для минут, чтобы она были меньше, чем деления для часов
         *  3. Вычисляются координаты начала и конца линии для каждого деления минут,
         *     и линия добавляется на сцену.
         * */

        for (int i = 1; i <= minutesDivisions; i++) {
            double angle = startAngle + (endAngle - startAngle) * i / (minutesDivisions + 1);
            double startX = circle.getCenterX() + circle.getRadius() * Math.sin(angle);
            double startY = circle.getCenterY() - circle.getRadius() * Math.cos(angle);
            double endX = circle.getCenterX() + (circle.getRadius() * 0.95) * Math.sin(angle);
            double endY = circle.getCenterY() - (circle.getRadius() * 0.95) * Math.cos(angle);

            Line mitutesLine = new Line(startX, startY, endX, endY);
            mitutesLine.setStroke(Color.BLACK); // цвет делений - чёрный
            root.getChildren().add(mitutesLine);
        }

        // Добавляем цифры от 1 до 12
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30);

            // Увеличиваем радиус, чтобы цифры были неподалёку от краёв круга, но не слишком далеко
            double x = circle.getCenterX() + (circle.getRadius() * 1.15) * Math.sin(angle); // Координаты x для цифры
            double y = circle.getCenterY() - (circle.getRadius() * 1.15) * Math.cos(angle); // Координаты y для цифры

            // Создаём объект Text для отображения текущей цифры.
            Text text = new Text(String.valueOf(i));
            text.setFont(new Font(18)); // Размер шрифта
            text.setFill(Color.BLACK); // Цвет шрифта

            // Получаем ширину и высоту текста для центрирования (Получаем фактическую ширину и высоту отрисованного текста)
            double textWidth = text.getLayoutBounds().getWidth();
            double textHeight = text.getLayoutBounds().getHeight();

            // Сдвигаем текст, чтобы он был центрирован относительно точки
            // (Корректируем координаты X и Y так, чтобы текст был выровнен по центру относительно вычисленной точки)
            text.setX(x - textWidth / 2);
            text.setY(y + textHeight / 4); // Небольшая коррекция для вертикального центрирования

            root.getChildren().add(text); // добавляем цифры на сцену (рисуем в приложении)
        }


        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();


        //---------------------------------------
        //-----------------Сцена-----------------
        //---------------------------------------


        // Создаем Scene и добавляем его в Stage, используя Pane
        Scene scene = new Scene(root, 800, 700);

        // Делаем окно статическим
        primaryStage.setResizable(false);

        primaryStage.setTitle("At Clock"); // заголовок окна
        primaryStage.setScene(scene); // устанавливаем сцену на главное окно

        primaryStage.show(); // отображаем нашу сцену на экране

        updateClock();


    }

    public  void  updateClock() {
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minutes = time.getMinute();
        int second = time.getSecond();

        // Вычиялем углы стрелок
        double hourAngle = (hour % 12 + minutes / 60.0) * 30;  // Учитываем минуты для более точного позиционирования
        double minutesAgnle = (minutes + second / 60.0) * 6; // Учитываем секунды
        double secondAngle = second * 6;

        // Обновляем положение стрелок
        updateHand(hourHand, hourAngle, 0.6, hourArrow); // Длина часовой стрелки 60% радиуса
        updateHand(minutesHand, minutesAgnle, 0.8, minutesArrow); // Длина минутной стрелки 80% радиуса
        updateHand(secondHand, secondAngle, 0.9, null); // Длина секундной стрелки 90% радиуса
    }

    public void updateHand(Line hand, double angle, double lengthFactor, Polygon arrow) {
        double angleRadians = Math.toRadians(angle);
        double endX = circle.getCenterX() + circle.getRadius() * lengthFactor * Math.sin(angleRadians);
        double endY = circle.getCenterY() - circle.getRadius() * lengthFactor * Math.cos(angleRadians);

        hand.setStartX(circle.getCenterX());
        hand.setStartY(circle.getCenterY());
        hand.setEndX(endX);
        hand.setEndY(endY);

        // Обновляем положение и поворот наконечника стрелки
        if (arrow != null) {
            double arrowX = circle.getCenterX() + circle.getRadius() * lengthFactor * Math.sin(angleRadians);
            double arrowY = circle.getCenterY() - circle.getRadius() * lengthFactor * Math.cos(angleRadians);

            // Устанавливаем позицию *относительно* конца линии
            arrow.setLayoutX(arrowX);
            arrow.setLayoutY(arrowY);

            arrow.setRotate(angle + 32); // Поворачиваем наконечник стрелки
        }


    }

    public Polygon createArrow() {
        Polygon arrow = new Polygon();
        arrow.getPoints().addAll(
                0.0, -1.0, // Вершина треугольника
                -8.0, -5.0, // Левая точка основания
                -8.0, 5.0  // Правая точка основания
        );
        arrow.setFill(Color.GRAY);

        arrow.setTranslateX(4.332);
        arrow.setTranslateY(-1);

        return arrow;
    }

}
