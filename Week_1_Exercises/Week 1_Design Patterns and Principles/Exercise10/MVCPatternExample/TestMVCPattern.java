public class TestMVCPattern {
    public static void main(String[] args) {
        Student student = new Student("12435", "Soumik Saha", "A+");
        StudentView studentView = new StudentView();
        StudentController controller = new StudentController(student, studentView);

        controller.updateView();

        controller.setStudentId("78945");
        controller.setStudentName("Sourav Saha");
        controller.setStudentGrade("AA");

        controller.updateView();
    }
}
