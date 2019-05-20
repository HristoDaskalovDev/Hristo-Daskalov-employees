package com.company;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter text file path: "); // Sample path : C:\\Users\\Hristo\\Desktop\\JavaProjects\\Test.txt
        String path = scanner.nextLine();

        FileReader reader = new FileReader(path);
        reader.readEmployees();
        reader.getUtility().mostTimeSpentOnProjects();
    }
}


