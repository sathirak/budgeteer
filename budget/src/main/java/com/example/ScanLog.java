package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;
import java.util.Scanner;

public class ScanLog {

    public void scanLog() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\u001b[32m[ + ] Choose an option:\u001B[0m\n");
        System.out.println("\t\u001b[33m  [ 1 ]\u001B[0m Scan logs by month");
        System.out.println("\t\u001b[33m  [ 2 ]\u001B[0m Back to main menu");

        System.out.print("\n\u001b[31m[ > ]\u001B[0m Enter your choice > ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                scanLogsByMonthAndYear();
                break;
            case 2:
                // You can add any other options or actions here
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void scanLogsByMonthAndYear() {

        Scanner scanner = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.print("\n\u001b[31m[ > ]\u001B[0m Enter year (press Enter for current year) > ");
        String yearInput = scanner.nextLine();

        int year;
        if (yearInput.isEmpty()) {
            year = Year.now().getValue();
        } else {
            year = Integer.parseInt(yearInput);
        }

        System.out.print("\u001b[31m[ > ]\u001B[0m Enter month (press Enter for current month) > ");
        String monthInput = scanner.nextLine();

        int month;
        if (monthInput.isEmpty()) {
            month = YearMonth.now().getMonthValue();
        } else {
            month = Integer.parseInt(monthInput);
        }

        String filePath = "data.json";

        try {
            JsonNode jsonArray = objectMapper.readTree(new File(filePath));

            if (jsonArray.isArray()) {
                Main.clear_screen();

                System.out.println("\n\u001b[32m[ + ] Logs for " + month + "/" + year + ":\u001B[0m\n\n");

                double totalIncome = 0.0;
                double totalExpense = 0.0;

                for (JsonNode jsonNode : jsonArray) {
                    String dateString = jsonNode.get("date").asText();
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);


                    
                    // Extracting year and month from the parsed date
                    int jsonYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
                    int jsonMonth = Integer.parseInt(new SimpleDateFormat("MM").format(date));

                    if (jsonYear == year && jsonMonth == month) {

                        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        boolean income = jsonNode.get("income").asBoolean();
                        String category = jsonNode.get("category").asText();
                        double amount = jsonNode.get("amount").asDouble();


                        if (income) {
                            System.out.println("\u001b[32m[ + ]\u001B[0m " + category);
                            totalIncome += amount;
                        } else {
                            System.out.println("\u001b[31m[ - ]\u001B[0m " + category);
                            totalExpense += amount;
                        }

                        System.out.println("\t\u001b[36m[ > ]\u001B[0m " + amount);
                        System.out.println("\t\u001b[36m[ > ]\u001B[0m Date: " + formattedDate);

                        System.out.println();

                    }

                    

                }

                System.out.println("\u001b[33m[ ! ]\u001B[0m gross amount " + (totalIncome - totalExpense) + " LKR");
            } else {
                System.out.println("The JSON file does not contain an array.");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
