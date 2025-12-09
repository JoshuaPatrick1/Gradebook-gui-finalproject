import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GradebookGUI extends Application {

    private Student currentStudent; // holds the active student

    @Override
    public void start(Stage primaryStage) {
        // Labels and input fields
        Label nameLabel = new Label("Student Name:");
        TextField nameField = new TextField();

        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();

        Label courseLabel = new Label("Course Name:");
        TextField courseField = new TextField();

        Label codeLabel = new Label("Course Code:");
        TextField codeField = new TextField();

        Label instructorLabel = new Label("Instructor:");
        TextField instructorField = new TextField();

        Label assignmentLabel = new Label("Assignment:");
        TextField assignmentField = new TextField();

        Label scoreLabel = new Label("Score:");
        TextField scoreField = new TextField();

        Label weightLabel = new Label("Weight:");
        TextField weightField = new TextField();

        Label dateLabel = new Label("Date:");
        TextField dateField = new TextField();

        // Output area
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Event 1: Create Student
        Button createStudentBtn = new Button("Create Student");
        createStudentBtn.setOnAction(e -> {
            String name = nameField.getText();
            int id = Integer.parseInt(idField.getText());
            currentStudent = new Student(name, id);
            outputArea.setText("Created student: " + currentStudent);
        });

        // Event 2: Add Course
        Button addCourseBtn = new Button("Add Course");
        addCourseBtn.setOnAction(e -> {
            if (currentStudent == null) {
                outputArea.setText("Please create a student first.");
                return;
            }
            Course c = new Course(courseField.getText(), codeField.getText(), instructorField.getText());
            currentStudent.addCourse(c);
            outputArea.setText("Added course: " + c + "\nStudent now has " + currentStudent.getCourses().size() + " courses.");
        });

        // Event 3: Add Grade
        Button addGradeBtn = new Button("Add Grade");
        addGradeBtn.setOnAction(e -> {
            if (currentStudent == null || currentStudent.getCourses().isEmpty()) {
                outputArea.setText("Please create a student and add a course first.");
                return;
            }
            Course c = currentStudent.getCourses().get(currentStudent.getCourses().size() - 1); // last course
            Grade g = new Grade(assignmentField.getText(),
                                Double.parseDouble(scoreField.getText()),
                                Double.parseDouble(weightField.getText()),
                                dateField.getText());
            c.addGrade(g);
            outputArea.setText("Added grade: " + g + "\nCourse average: " + c.calculateAverage());
        });

        // Event 4: Calculate GPA
        Button calcGpaBtn = new Button("Calculate GPA");
        calcGpaBtn.setOnAction(e -> {
            if (currentStudent == null) {
                outputArea.setText("No student created.");
                return;
            }
            double gpa = currentStudent.calculateGPA();
            outputArea.setText("Student GPA: " + gpa);
        });

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            nameLabel, nameField,
            idLabel, idField,
            courseLabel, courseField,
            codeLabel, codeField,
            instructorLabel, instructorField,
            assignmentLabel, assignmentField,
            scoreLabel, scoreField,
            weightLabel, weightField,
            dateLabel, dateField,
            createStudentBtn, addCourseBtn, addGradeBtn, calcGpaBtn,
            outputArea
        );

        Scene scene = new Scene(layout, 500, 600);
        primaryStage.setTitle("Gradebook System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
