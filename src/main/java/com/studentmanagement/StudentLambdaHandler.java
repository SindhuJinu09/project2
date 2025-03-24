import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.sql.SQLException;

public class StudentLambdaHandler implements RequestHandler<Student, String> {
    private final studentDAO = new StudentDAO();

    @Override
    public String handleRequest(Student student, Context context) {
        try {
            studentDAO.createStudent(student);
            return "Student added successfully!";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }
}
