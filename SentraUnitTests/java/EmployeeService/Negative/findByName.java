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
        database.add(new Employee("John Doe", "john.doe@example.com"));
        database.add(new Employee("Jane Smith", "jane.smith@example.com"));
    }

    @Test
    public void testFindByName_NonExistentName() {
        Optional<Employee> result = employeeService.findByName("Alice Johnson");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_CaseSensitive() {
        Optional<Employee> result = employeeService.findByName("JOHN DOE");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_SpecialCharacters() {
        Optional<Employee> result = employeeService.findByName("John!Doe");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_MaxLengthName() {
        String longName = "A".repeat(1000);
        Optional<Employee> result = employeeService.findByName(longName);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_BlankSpaces() {
        Optional<Employee> result = employeeService.findByName("   ");
        assertFalse(result.isPresent());
    }
}