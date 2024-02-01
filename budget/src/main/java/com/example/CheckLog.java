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

                // Prompt the user to enter the ID they want to retrieve
                System.out.print("Enter the ID to retrieve: ");
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
                        String type = jsonNode.get("type").asText();
                        String category = jsonNode.get("category").asText();
                        double amount = jsonNode.get("amount").asDouble();
                        String description = jsonNode.get("description").asText();

                        // Print details for the entry with the target ID
                        System.out.println("ID: " + id);
                        System.out.println("Date: " + date);
                        System.out.println("Type: " + type);
                        System.out.println("Category: " + category);
                        System.out.println("Amount: " + amount);
                        System.out.println("Description: " + description);
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
            // Close the Scanner to prevent resource leaks
            scanner.close();
        }
    }
}
