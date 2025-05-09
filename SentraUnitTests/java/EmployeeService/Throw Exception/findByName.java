import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private List<Employee> database;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
        database = new ArrayList<>();
        database.add(new Employee("John Doe"));
        database.add(new Employee("Jane Smith"));
    }

    @Test
    public void testFindByName_ThrowException_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            employeeService.findByName(null);
        });
    }

    @Test
    public void testFindByName_ThrowException_EmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.findByName("");
        });
    }

    @Test
    public void testFindByName_ThrowException_MissingCase() {
        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.findByName("john");
        });
    }
}