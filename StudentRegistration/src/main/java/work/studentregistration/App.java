package work.studentregistration;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class App extends Application {

    TextField firstName = new TextField();
    TextField lastName = new TextField();
    TextField email = new TextField();
    TextField confirmEmail = new TextField();
    PasswordField password = new PasswordField();
    PasswordField confirmPassword = new PasswordField();

    ComboBox<Integer> yearBox = new ComboBox<>();
    ComboBox<String> monthBox = new ComboBox<>();
    ComboBox<Integer> dayBox = new ComboBox<>();

    RadioButton male = new RadioButton("Male");
    RadioButton female = new RadioButton("Female");

    ToggleGroup genderGroup = new ToggleGroup();

    ComboBox<String> deptBox = new ComboBox<>();

    TextArea output = new TextArea();

    static int counter = 1;

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("First Name"), 0, 0);
        grid.add(firstName, 1, 0);

        grid.add(new Label("Last Name"), 0, 1);
        grid.add(lastName, 1, 1);

        grid.add(new Label("Email"), 0, 2);
        grid.add(email, 1, 2);

        grid.add(new Label("Confirm Email"), 0, 3);
        grid.add(confirmEmail, 1, 3);

        grid.add(new Label("Password"), 0, 4);
        grid.add(password, 1, 4);

        grid.add(new Label("Confirm Password"), 0, 5);
        grid.add(confirmPassword, 1, 5);

        // DOB
        grid.add(new Label("Date of Birth"), 0, 6);

        HBox dob = new HBox(5);
        dob.getChildren().addAll(yearBox, monthBox, dayBox);
        grid.add(dob, 1, 6);

        for (int i = 1960; i <= LocalDate.now().getYear(); i++)
            yearBox.getItems().add(i);

        monthBox.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");

        yearBox.setOnAction(e -> updateDays());
        monthBox.setOnAction(e -> updateDays());

        // Gender
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        HBox gender = new HBox(10, male, female);
        grid.add(new Label("Gender"), 0, 7);
        grid.add(gender, 1, 7);

        // Department
        deptBox.getItems().addAll(
                "Civil",
                "Computer Science and Engineering",
                "Electrical",
                "Electronics & Communication",
                "Mechanical"
        );

        grid.add(new Label("Department"), 0, 8);
        grid.add(deptBox, 1, 8);

        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        HBox buttons = new HBox(10, submit, cancel);
        grid.add(buttons, 1, 9);

        output.setEditable(false);
        output.setPrefWidth(300);

        VBox right = new VBox(new Label("Your Data is Below:"), output);

        HBox root = new HBox(20, grid, right);

        submit.setOnAction(e -> submit());
        cancel.setOnAction(e -> clear());

        stage.setScene(new Scene(root, 900, 400));
        stage.setTitle("New Student Registration");
        stage.show();
    }

    void updateDays() {
        dayBox.getItems().clear();
        if (yearBox.getValue() == null || monthBox.getValue() == null) return;

        int y = yearBox.getValue();
        int m = Integer.parseInt(monthBox.getValue());

        int days = LocalDate.of(y, m, 1).lengthOfMonth();

        for (int i = 1; i <= days; i++)
            dayBox.getItems().add(i);
    }

    void submit() {

        if (!validate()) return;

        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String dept = deptBox.getValue();

        LocalDate dob = LocalDate.of(yearBox.getValue(),
                Integer.parseInt(monthBox.getValue()),
                dayBox.getValue());

        String id = LocalDate.now().getYear() + "-" + String.format("%05d", counter++);

        String record = id + " | " +
                firstName.getText() + " " + lastName.getText() +
                " | " + gender +
                " | " + dept +
                " | " + dob +
                " | " + email.getText();

        output.appendText(record + "\n");

        saveCSV(record);

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully");
        a.show();
    }

    boolean validate() {

        if (firstName.getText().trim().isEmpty()) return error("First name required");
        if (lastName.getText().trim().isEmpty()) return error("Last name required");

        if (!Pattern.matches(".+@.+\\..+", email.getText()))
            return error("Invalid email");

        if (!email.getText().equals(confirmEmail.getText()))
            return error("Emails do not match");

        String pass = password.getText();

        if (pass.length() < 8 || pass.length() > 20 ||
                !pass.matches(".*[A-Za-z].*") ||
                !pass.matches(".*[0-9].*"))
            return error("Password must be 8–20 with letter and digit");

        if (!pass.equals(confirmPassword.getText()))
            return error("Passwords do not match");

        if (genderGroup.getSelectedToggle() == null)
            return error("Select gender");

        if (deptBox.getValue() == null)
            return error("Select department");

        if (yearBox.getValue() == null || monthBox.getValue() == null || dayBox.getValue() == null)
            return error("Select DOB");

        LocalDate dob = LocalDate.of(yearBox.getValue(),
                Integer.parseInt(monthBox.getValue()),
                dayBox.getValue());

        int age = Period.between(dob, LocalDate.now()).getYears();

        if (age < 16 || age > 60)
            return error("Age must be 16–60");

        return true;
    }

    boolean error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
        return false;
    }

    void saveCSV(String line) {
        try {
            FileWriter fw = new FileWriter("students.csv", true);
            fw.write(line + "\n");
        } catch (Exception ignored) {}
    }

    void clear() {
        firstName.clear();
        lastName.clear();
        email.clear();
        confirmEmail.clear();
        password.clear();
        confirmPassword.clear();
        deptBox.setValue(null);
        genderGroup.selectToggle(null);
        output.clear();
    }

    public static void main(String[] args) {
        launch();
    }
}