package com.example.jobapplicationsystem;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;


public class HelloController {


    @FXML
    private void handleUploadImage() {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        java.io.File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            profileImageView.setImage(new javafx.scene.image.Image(file.toURI().toString()));
        }
    }
    @FXML
    private void handleUploadCoverLetter() {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        // تصفية الملفات لتكون نصية فقط
        fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Text Files", "*.txt"));
        java.io.File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // قراءة محتوى الملف وعرضه في الـ TextArea
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                coverLetterArea.setText(content);
            } catch (java.io.IOException e) {
                System.out.println("Error reading file");
            }
        }
    }
    // حقول المعلومات الشخصية
    @FXML private TextField txtFullName;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhone;
    @FXML private DatePicker birthDatePicker;
    @FXML private ToggleGroup statusGroup; // للحالة الاجتماعية

    // المهارات
    @FXML private ListView<String> availableSkills;
    @FXML private ListView<String> selectedSkills;

    // رسالة التغطية والصورة
    @FXML private TextArea coverLetterArea;
    @FXML private ImageView profileImageView;
    @FXML private Slider fontSizeSlider;

    // الألوان والخلفية
    @FXML private ColorPicker bgColorPicker;
    @FXML private AnchorPane mainPane; // الـ Root container

    @FXML
    private void moveToSelected() {
        String selected = availableSkills.getSelectionModel().getSelectedItem();
        if (selected != null) {
            availableSkills.getItems().remove(selected);
            selectedSkills.getItems().add(selected);
        }
    }

    @FXML
    private void moveToAvailable() {
        String selected = selectedSkills.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectedSkills.getItems().remove(selected);
            availableSkills.getItems().add(selected);
     س   }
    }
    @FXML
    public void initialize() {
        // 1. تحديد التاريخ الأقصى المسموح به (1 يناير 2010)
        LocalDate maxDate = LocalDate.of(2010, 1, 1);

        // 2. برمجة الـ DatePicker لتعطيل ما بعد هذا التاريخ
        birthDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // إذا كان التاريخ بعد maxDate، سيتم تعطيله (Disable)
                if (date != null && date.isAfter(maxDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // اختيار اختياري لتغيير لون الأيام المعطلة
                }
            }
        });

        // إضافة بعض المهارات للقائمة لكي لا تكون فارغة عند التشغيل
        if (availableSkills != null) {
            availableSkills.getItems().addAll("Java", "JavaFX", "Python", "SQL", "Problem Solving");
        }
    }

}