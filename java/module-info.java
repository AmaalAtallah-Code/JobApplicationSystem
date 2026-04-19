module com.example.jobapplicationsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jobapplicationsystem to javafx.fxml;
    exports com.example.jobapplicationsystem;
}