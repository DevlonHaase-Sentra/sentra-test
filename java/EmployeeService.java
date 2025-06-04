import entity.Employee;
import java.util.Optional;

public class EmployeeService extends AbstractService<Employee> {

    @Override
    public void create(Employee employee) {
        super.create(employee);
        System.out.println("Employee created: " + employee);
    }

    public Optional<Employee> findByName(String name,String email) {
        for (Employee employee : getAll()) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }
    public Optional<Employee> findByEmail(String email) {return Optional.empty();}
}
