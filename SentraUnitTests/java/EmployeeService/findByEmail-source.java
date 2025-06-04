import entity.Employee;import java.util.ArrayList;import java.util.List;import java.util.Optional;

public class EmployeeService {
    public Optional<Employee> findByEmail(String email) {
        for (Employee employee : getAll()) {
            if (employee.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }
}