package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Utility {

    private Map<Integer, List<Project>> employees;

    public Utility() {
        this.employees = new HashMap<>();
    }

    public Map<Integer, List<Project>> getEmployees() {
        return employees;
    }

    public void addRecord(int employeeId, int projectId, LocalDate dateFrom, LocalDate dateTo){
        Project project = new Project(projectId, dateFrom, dateTo);
        if(employees.containsKey(employeeId)){
            employees.get(employeeId).add(project);
        } else {
            List<Project> projects = new ArrayList<>();
            projects.add(project);
            employees.put(employeeId, projects);
        }
    }

    public void mostTimeSpentOnProjects (){

        Integer[] employeeKeys = employees.keySet().toArray(new Integer[employees.keySet().size()]);
        int firstID = 0;
        int secondID = 0;
        long maxTime = 0;

        for(int i=0; i<employeeKeys.length; i++){
            for(int j=i+1; j<employeeKeys.length; j++){

                long result = checkForOverlappingProjects(employees.get(employeeKeys[i]), employees.get(employeeKeys[j]));
                if(result > maxTime){
                    maxTime = result;
                    firstID = employeeKeys[i];
                    secondID = employeeKeys[j];
                }
            }
        }

        if(maxTime == 0){
            System.out.println("Employees have no shared projects ");
        } else {
            System.out.format("Employee with id: %d\nand\nEmployee with id: %d\nHave worked a total of %d days on shared projects", firstID, secondID, maxTime);
        }
    }

    private long checkForOverlappingProjects(List<Project> emp1Projects, List<Project> emp2Projects){

        if(emp1Projects.isEmpty() || emp2Projects.isEmpty()){
            return 0;
        }

        long totalDaysShared = 0;
        for(Project project1 : emp1Projects){
            for(Project project2 : emp2Projects){
                if(project1.getProjectId() == project2.getProjectId()){
                    totalDaysShared += getTimeSpentAsPair(project1, project2);
                }
            }
        }
        return totalDaysShared;
    }

    private long getTimeSpentAsPair(Project project1, Project project2){

        if(project1.getDateTo().isBefore(project2.getDateFrom()) || project1.getDateFrom().isAfter(project2.getDateTo())){
            return 0;
        }

        long period;
        if(project1.getDateFrom().isBefore(project2.getDateFrom())){
            if(project1.getDateTo().isBefore(project2.getDateTo())){
                period = ChronoUnit.DAYS.between(project2.getDateFrom(), project1.getDateTo());
            } else {
                period = ChronoUnit.DAYS.between(project2.getDateFrom(), project2.getDateTo());
            }
        } else {
            if(project2.getDateTo().isBefore(project1.getDateTo())){
                period = ChronoUnit.DAYS.between(project1.getDateFrom(), project2.getDateTo());
            } else {
                period = ChronoUnit.DAYS.between(project1.getDateFrom(), project1.getDateTo());
            }
        }
        return period;
    }
}
