package testTask.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testTask.domain.LevelDegree;
import testTask.entity.Lector;
import testTask.repository.DepartmentRepository;
import testTask.repository.LectorRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActionService {

    private DepartmentRepository departmentRepository;
    private LectorRepository lectorRepository;

    public String invokeAction(int actionNumber, String value) {
        switch (actionNumber) {
            case 1:
                return getDepartmentHeadName(value);
            case 2:
                return getDepartmentStatistics(value);
            case 3:
                return getDepartmentAverageSalary(value);
            case 4:
                return getDepartmentEmployeeCount(value);
            case 5:
                return getLectorsByFirstOrLastName(value);
            default:
                return "Wrong action number!";
        }
    }

    private String getDepartmentHeadName(String departmentName) {
        String departmentHeadName = departmentRepository.findDepartmentHead(departmentName).getFirstName();
        return "Head of " + departmentName + " department is " + departmentHeadName;
    }

    private String getDepartmentStatistics(String departmentName) {
        List<Lector> departmentLectors = departmentRepository.findAllDepartmentLectors(departmentName);
        Map<LevelDegree, Long> departmentStatistics = departmentLectors.stream()
                .collect(Collectors.groupingBy(Lector::getDegree, Collectors.counting()));

        return departmentStatistics.entrySet().stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

    private String getDepartmentAverageSalary(String departmentName) {
        double departmentAverageSalary = departmentRepository.findAllDepartmentLectors(departmentName).stream()
                .map(Lector::getSalary)
                .mapToDouble(Double::doubleValue)
                .summaryStatistics()
                .getAverage();

        return "The average salary of " + departmentName + " is " + departmentAverageSalary;
    }

    private String getDepartmentEmployeeCount(String departmentName) {
        return String.valueOf(departmentRepository.findAllDepartmentLectors(departmentName).size());
    }

    private String getLectorsByFirstOrLastName(String template) {
        return lectorRepository.findAllByTemplate(template).stream()
                .map(lector -> lector.getFirstName() + " " + lector.getLastName())
                .collect(Collectors.joining(", "));
    }
}
