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
    public void testFindByName_SpecialCharacters() {
        String specialCharsName = "!@#$%^&*()";
        Optional<Employee> result = employeeService.findByName(specialCharsName);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_MaxLengthName() {
        String maxLengthName = "abcdefghijklmnopqrstuvwxyz";
        Optional<Employee> result = employeeService.findByName(maxLengthName);
        assertTrue(result.isPresent());
        assertEquals("Jane Smith", result.get().getName());
    }

    @Test
    public void testFindByName_BlankSpaces() {
        String blankSpacesName = "   ";
        Optional<Employee> result = employeeService.findByName(blankSpacesName);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_CaseSensitive() {
        String caseSensitiveName = "johndoe";
        Optional<Employee> result = employeeService.findByName(caseSensitiveName);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testFindByName_CaseInsensitive() {
        String caseInsensitiveName = "JOHN DOE";
        Optional<Employee> result = employeeService.findByName(caseInsensitiveName);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testFindByName_MissingCase() {
        String missingCaseName = "john dOe";
        Optional<Employee> result = employeeService.findByName(missingCaseName);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testFindByName_NullInput() {
        Optional<Employee> result = employeeService.findByName(null);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_EmptyString() {
        Optional<Employee> result = employeeService.findByName("");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_NonExistentName() {
        String nonExistentName = "Alice Johnson";
        Optional<Employee> result = employeeService.findByName(nonExistentName);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByName_ExistingName() {
        String existingName = "John Doe";
        Optional<Employee> result = employeeService.findByName(existingName);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }
}