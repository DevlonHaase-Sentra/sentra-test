```java
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
    public void testFindByEmailNullInput() {
        assertThrows(NullPointerException.class, () -> employeeService.findByEmail(null));
    }

    @Test
    public void testFindByEmailEmptyInput() {
        assertEquals(Optional.empty(), employeeService.findByEmail(""));
    }

    @Test
    public void testFindByEmailNonExistentEmail() {
        assertEquals(Optional.empty(), employeeService.findByEmail("nonexistent@example.com"));
    }

    @Test
    public void testFindByEmailMultipleMatches() {
        employees.add(new Employee("John Doe", "another.john.doe@example.com"));
        assertEquals(Optional.of(employees.get(0)), employeeService.findByEmail("john.doe@example.com"));
    }

    @Test
    public void testFindByEmailNoEmployees() {
        employees.clear();
        assertEquals(Optional.empty(), employeeService.findByEmail("any.email@example.com"));
    }

    @Test
    public void testFindByEmailIgnoreCaseMismatch() {
        assertEquals(Optional.empty(), employeeService.findByEmail("john.doe@example.net"));
    }

    @Test
    public void testFindByEmailSpecialCharactersInEmail() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doe@example..com"));
    }

    @Test
    public void testFindByEmailLeadingTrailingSpaces() {
        assertEquals(Optional.of(employees.get(0)), employeeService.findByEmail("   john.doe@example.com   "));
    }

    @Test
    public void testFindByEmailVeryLongEmail() {
        StringBuilder longEmail = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            longEmail.append('a');
        }
        longEmail.append("@example.com");
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail(longEmail.toString()));
    }

    @Test
    public void testFindByEmailEmptyDomain() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doe@.com"));
    }

    @Test
    public void testFindByEmailMissingAtSymbol() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doeexample.com"));
    }

    @Test
    public void testFindByEmailOnlyAtSymbol() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("@example.com"));
    }

    @Test
    public void testFindByEmailOnlyDotBeforeAtSymbol() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail(".@example.com"));
    }

    @Test
    public void testFindByEmailOnlyDotAfterAtSymbol() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doe.@com"));
    }

    @Test
    public void testFindByEmailConsecutiveDotsInLocalPart() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john..doe@example.com"));
    }

    @Test
    public void testFindByEmailConsecutiveDotsInDomainPart() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doe@example..com"));
    }

    @Test
    public void testFindByEmailSingleCharacterLocalPart() {
        assertEquals(Optional.of(employees.get(0)), employeeService.findByEmail("j@example.com"));
    }

    @Test
    public void testFindByEmailSingleCharacterDomainPart() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doe@x.com"));
    }

    @Test
    public void testFindByEmailAllDigitsLocalPart() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("12345@example.com"));
    }

    @Test
    public void testFindByEmailAllDigitsDomainPart() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doe@12345.com"));
    }

    @Test
    public void testFindByEmailAllLettersLocalPart() {
        assertEquals(Optional.of(employees.get(0)), employeeService.findByEmail("abcdefg@example.com"));
    }

    @Test
    public void testFindByEmailAllLettersDomainPart() {
        assertThrows(IllegalArgumentException.class, () -> employeeService.findByEmail("john.doe@abcdefghi.com"));
    }

    @Test
    public void testFindByEmailMixedCaseLocalPart() {
        assertEquals(Optional.of(employees.get(0)), employeeService.findBy