import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private List<Employee> employees;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
        employees = new ArrayList<>();
        employees.add(new Employee("John Doe", "john.doe@example.com"));
        employees.add(new Employee("Jane Smith", "jane.smith@example.com"));
    }

    @Test
    public void testFindByEmailThrowsNullPointerExceptionWhenEmailIsNull() {
        assertThrows(NullPointerException.class, () -> {
            employeeService.findByEmail(null);
        });
    }

    @Test
    public void testFindByEmailThrowsIllegalArgumentExceptionWhenEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.findByEmail("");
        });
    }

    @Test
    public void testFindByEmailThrowsIllegalArgumentExceptionWhenEmailIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.findByEmail("invalid-email");
        });
    }
}