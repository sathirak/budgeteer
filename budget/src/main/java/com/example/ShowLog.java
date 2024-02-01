package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ShowLog {

    public static void main(String[] args) {
        String filePath = "C:/Users/SathiraK/Desktop/SathiraK/Github/budget-tracker/budget/data.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(new File(filePath));

            if (jsonArray.isArray()) {
                // Clear the terminal
                System.out.print("\033[H\033[2J");

                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("| %-4s | %-10s | %-8s | %-15s | %-10s | %-30s |\n",
                        "ID", "Date", "Type", "Category", "Amount", "Description");
                System.out.println("-------------------------------------------------------------------------------------------------");

                double balance = 0.0;

                for (JsonNode jsonNode : jsonArray) {
                    int id = jsonNode.get("id").asInt();
                    String date = jsonNode.get("date").asText();
                    String type = jsonNode.get("type").asText();
                    String category = jsonNode.get("category").asText();
                    double amount = jsonNode.get("amount").asDouble();
                    String description = jsonNode.get("description").asText();

                    // Update the balance based on the type of entry (income or expense)
                    if ("income".equalsIgnoreCase(type)) {
                        balance += amount;
                    } else if ("expense".equalsIgnoreCase(type)) {
                        balance -= amount;
                    }

                    System.out.printf("| %-4d | %-10s | %-8s | %-15s | %-10.2f | %-30s |\n",
                            id, date, type, category, amount, description);
                }

                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("Current Balance: %.2f\n", balance);
            } else {
                System.out.println("The JSON file does not contain an array.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}