```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private List<Employee> employees;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
        employees = Arrays.asList(
            new Employee("John Doe", "john.doe@example.com"),
            new Employee("Jane Smith", "jane.smith@example.com")
        );
    }

    @Test
    public void testFindByEmailLeadingTrailingSpaces() {
        // Arrange
        String email = "   john.doe@example.com   ";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        assertEquals("john.doe@example.com", result.get().getEmail());
    }

    @Test
    public void testFindByEmailEmptyDomain() {
        // Arrange
        String email = "john.doe@";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmailMissingAtSymbol() {
        // Arrange
        String email = "johndoeexample.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmailOnlyAtSymbol() {
        // Arrange
        String email = "@example.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmailOnlyDotBeforeAtSymbol() {
        // Arrange
        String email = ".john@example.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmailOnlyDotAfterAtSymbol() {
        // Arrange
        String email = "john.@example.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmailConsecutiveDotsInLocalPart() {
        // Arrange
        String email = "john..doe@example.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmailConsecutiveDotsInDomainPart() {
        // Arrange
        String email = "john.doe@example..com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmailSingleCharacterLocalPart() {
        // Arrange
        String email = "a@example.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("a", result.get().getName());
        assertEquals("a@example.com", result.get().getEmail());
    }

    @Test
    public void testFindByEmailSingleCharacterDomainPart() {
        // Arrange
        String email = "john.doe@example.c";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        assertEquals("john.doe@example.c", result.get().getEmail());
    }

    @Test
    public void testFindByEmailAllDigitsLocalPart() {
        // Arrange
        String email = "1234567890@example.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("1234567890", result.get().getName());
        assertEquals("1234567890@example.com", result.get().getEmail());
    }

    @Test
    public void testFindByEmailAllDigitsDomainPart() {
        // Arrange
        String email = "john.doe@example1234567890";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        assertEquals("john.doe@example1234567890", result.get().getEmail());
    }

    @Test
    public void testFindByEmailAllLettersLocalPart() {
        // Arrange
        String email = "abcdefg@example.com";

        // Act
        Optional<Employee> result = employeeService.findByEmail(email);

        // Assert
        assertTrue(result