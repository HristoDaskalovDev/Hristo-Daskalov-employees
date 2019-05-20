package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class FileReader {

    private File file;
    private Utility utility;

    public FileReader(String filename) {
        file = new File(filename);
        utility = new Utility();
    }

    public Utility getUtility() {
        return utility;
    }

    public void readEmployees(){
        try(Scanner scanner = new Scanner(this.file)){

            scanner.useDelimiter(", |\r\n");

            int employeeId;
            int projectId;
            LocalDate dateFrom;
            LocalDate dateTo;

            while(scanner.hasNext()){
                employeeId = scanner.nextInt();
                projectId = scanner.nextInt();
                String dateFromString = scanner.next();
                dateFrom = dateFromString.equals("NULL") ? LocalDate.now() : LocalDate.parse(dateFromString);
                String dateToString = scanner.next();
                dateTo = dateToString.equals("NULL") ? LocalDate.now() : LocalDate.parse(dateToString);

                utility.addRecord(employeeId, projectId, dateFrom, dateTo);
            }

        } catch (FileNotFoundException fnfe){
            System.out.println("Cannot find a file with such a name\n");
            fnfe.printStackTrace();
        }
    }
}
