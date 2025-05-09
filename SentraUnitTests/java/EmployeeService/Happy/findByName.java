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
    public void testFindByName_ExistingName() {
        // Arrange
        String nameToFind = "John Doe";

        // Act
        Optional<Employee> result = employeeService.findByName(nameToFind);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }

    @Test
    public void testFindByName_NonExistentName() {
        // Arrange
        String nameToFind = "Alice Johnson";

        // Act
        Optional<Employee> result = employeeService.findByName(nameToFind);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_CaseInsensitive() {
        // Arrange
        String nameToFind = "jAnE sMiTh";

        // Act
        Optional<Employee> result = employeeService.findByName(nameToFind);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Jane Smith", result.get().getName());
        assertEquals("jane.smith@example.com", result.get().getEmail());
    }
}