import entity.Employee;
import java.util.Optional;

public class EmployeeService extends AbstractService<Employee> {

    @Override
    public void create(Employee employee) {
        super.create(employee);
        System.out.println("Employee created: " + employee);
    }

    public Optional<Employee> findByName(String name) {
        for (Employee employee : getAll()) {
            if (employee.getName().equalsIgnoreCase(name)) {
                System.out.println("Employee created: " + employee);
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }
}
