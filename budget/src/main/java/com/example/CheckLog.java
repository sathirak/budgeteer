package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckLog {

    public static void main(String[] args) {

        String filePath = "C:/Users/SathiraK/Desktop/SathiraK/Github/budget-tracker/budget/data.json";

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonArray = objectMapper.readTree(new File(filePath));

            // Check if the root node is an array
            if (jsonArray.isArray()) {

                Main.clear_screen();
                System.out.print("\n\u001b[31m[ > ]\u001B[0m log id to check  >   ");
                int targetId = scanner.nextInt();
                scanner.nextLine();
                // Flag to check if the target ID was found
                boolean idFound = false;

                // Iterate through each element in the array
                for (JsonNode jsonNode : jsonArray) {
                    // Accessing values based on the "id" field
                    int id = jsonNode.get("id").asInt();

                    // Check if the current entry has the target ID
                    if (id == targetId) {
                        String date = jsonNode.get("date").asText();
                        String income = jsonNode.get("income").asText();
                        String category = jsonNode.get("category").asText();
                        double amount = jsonNode.get("amount").asDouble();
                        String description = jsonNode.get("description").asText();

                        Main.clear_screen();

                        System.out.println("\n ");
                        System.out.println("\u001b[30m     [ ###  details for log id " + id + "  ### ]   \u001B[0m");
                        System.out.println("\n ");
                        System.out.println("\u001b[36m[ > ]\u001B[0m date      > " + date);
                        System.out.println("\u001b[36m[ > ]\u001B[0m income    > " + income);
                        System.out.println("\u001b[36m[ > ]\u001B[0m category  > " + category);
                        System.out.println("\u001b[36m[ > ]\u001B[0m amount    > " + amount);
                        System.out.println("\u001b[36m[ > ]\u001B[0m summary   > " + description);
                        System.out.println();

                        // Set the flag to true, indicating the target ID was found
                        idFound = true;

                        // Break out of the loop once the target ID is found
                        break;
                    }
                }

                // If the target ID was not found, display a message
                if (!idFound) {
                    System.out.println("ID " + targetId + " not found in the JSON file.");
                }

            } else {
                System.out.println("The JSON file does not contain an array.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
