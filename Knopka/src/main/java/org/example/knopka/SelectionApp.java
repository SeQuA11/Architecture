package org.example.knopka;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectionApp extends Application {

    // Label для отображения выбранного варианта еды (под кнопками).  Объявлена как переменная экземпляра.
    private Label mealSelectionLabel = new Label("Выбранное время суток: ");
    // Label для отображения выбранных сессий (под чек боксами).  Объявлена как переменная экземпляра.
    private Label sessionSelectionLabel = new Label("Выбранные пары: ");

    // Чек боксы, объявленные как переменные экземпляра, чтобы к ним был доступ из нескольких методов
    private CheckBox session1Check = new CheckBox("1 пара");
    private CheckBox session2Check = new CheckBox("2 пара");
    private CheckBox session3Check = new CheckBox("3 пара");


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Кнопочки");

        // Radio Buttons (Кружочки - Single Selection)
        Label mealLabel = new Label("Выберите время дня:");
        RadioButton morningRadio = new RadioButton("Утро");
        RadioButton afternoonRadio = new RadioButton("Обед");
        RadioButton eveningRadio = new RadioButton("Вечер");

        // Группа для кнопок, чтобы можно было выбрать только один вариант
        ToggleGroup mealGroup = new ToggleGroup();
        morningRadio.setToggleGroup(mealGroup);
        afternoonRadio.setToggleGroup(mealGroup);
        eveningRadio.setToggleGroup(mealGroup);

        // Устанавливаем вариант по умолчанию (Утро) и обновляем соответствующий Label
        morningRadio.setSelected(true);
        updateMealSelectionLabel(morningRadio.getText());


        // Добавляем слушатель (listener) для обработки выбора кнопок
        // Слушатель реагирует на изменение выбранной радиокнопки.
        mealGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            // Проверяем, что что-то было выбрано (newValue != null)
            if (newValue != null) {
                // Получаем выбранную радиокнопку
                RadioButton selectedRadio = (RadioButton) newValue;
                // Обновляем label с выбранным вариантом
                updateMealSelectionLabel(selectedRadio.getText());
            }
        });

        // Layout для кнопок и label выбора
        VBox mealBox = new VBox(10, mealLabel, morningRadio, afternoonRadio, eveningRadio, mealSelectionLabel);
        mealBox.setPadding(new Insets(10));

        // CheckBoxes (Квадратики - Multiple Selection)
        Label sessionLabel = new Label("Выберите пары:");


        // Добавляем слушатели для каждого чекбокса.  Эти слушатели реагируют на изменение состояния чек боксов.
        session1Check.selectedProperty().addListener((observable, oldValue, newValue) -> updateSessionSelectionLabel());
        session2Check.selectedProperty().addListener((observable, oldValue, newValue) -> updateSessionSelectionLabel());
        session3Check.selectedProperty().addListener((observable, oldValue, newValue) -> updateSessionSelectionLabel());


        // Layout для чек боксов и label выбора
        VBox sessionBox = new VBox(10, sessionLabel, session1Check, session2Check, session3Check, sessionSelectionLabel);
        sessionBox.setPadding(new Insets(10));

        // Основной layout, объединяющий mealBox и sessionBox
        HBox mainLayout = new HBox(20, mealBox, sessionBox);
        mainLayout.setPadding(new Insets(20));

        // Создаем scene и отображаем ее
        Scene scene = new Scene(mainLayout, 460, 190); // Увеличили высоту, чтобы вместить label
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для обновления label с выбранным вариантом еды.
    private void updateMealSelectionLabel(String meal) {
        mealSelectionLabel.setText("Выбранная еда: " + meal);
    }

    // Метод для обновления label с выбранными сессиями (через запятую).
    private void updateSessionSelectionLabel() {
        // Строитель строки для создания списка выбранных сессий
        StringBuilder selectedSessions = new StringBuilder("Выбранные сессии: ");
        boolean anySessionSelected = false;  // Флаг, чтобы проверить, выбрана ли хоть одна сессия.

        // Проверяем, какие чек боксы выбраны, и добавляем их текст в строку
        if (session1Check.isSelected()) {
            selectedSessions.append("1 пара, ");
            anySessionSelected = true; // Устанавливаем флаг, если выбрана хоть одна сессия.
        }
        if (session2Check.isSelected()) {
            selectedSessions.append("2 пара, ");
            anySessionSelected = true; // Устанавливаем флаг, если выбрана хоть одна сессия.
        }
        if (session3Check.isSelected()) {
            selectedSessions.append("3 пара, ");
            anySessionSelected = true; // Устанавливаем флаг, если выбрана хоть одна сессия.
        }

        // Удаляем последнюю запятую и пробел, если есть, но только если были выбраны сессии.
        if (anySessionSelected) {
            selectedSessions.delete(selectedSessions.length() - 2, selectedSessions.length());
        }
        else{
            // Если ничего не выбрано, отображаем "Ничего"
            selectedSessions.append("Ничего");
        }

        // Устанавливаем текст label
        sessionSelectionLabel.setText(selectedSessions.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
