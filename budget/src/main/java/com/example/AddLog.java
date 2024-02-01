package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AddLog {

    public static void main(String[] args) {

        String filePath = "C:/Users/SathiraK/Desktop/SathiraK/Github/budget-tracker/budget/data.json";

        Scanner scanner = new Scanner(System.in);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonArray = objectMapper.readTree(new File(filePath));

            if (jsonArray.isArray()) {

                ObjectNode newEntry = JsonNodeFactory.instance.objectNode();

                System.out.print("Enter new ID: ");
                int id = scanner.nextInt();

                if (idExists(jsonArray, id)) {
                    System.out.println("ID " + id + " already exists in the JSON file.");
                    return;
                }

                newEntry.put("id", id);

                System.out.print("Enter date (YYYY-MM-DD): ");
                newEntry.put("date", scanner.next());

                System.out.print("Enter type (income/expense): ");
                newEntry.put("type", scanner.next());

                System.out.print("Enter category: ");
                newEntry.put("category", scanner.next());

                System.out.print("Enter amount: ");
                newEntry.put("amount", scanner.nextDouble());

                System.out.print("Enter description: ");
                newEntry.put("description", scanner.next());
                scanner.nextLine();

                ((ArrayNode) jsonArray).add(newEntry);

                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), jsonArray);

                System.out.println("New entry added successfully.");

            } else {
                System.out.println("The JSON file does not contain an array.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            scanner.close();
        }
    }

    private static boolean idExists(JsonNode jsonArray, int targetId) {
        for (JsonNode jsonNode : jsonArray) {
            int id = jsonNode.get("id").asInt();
            if (id == targetId) {
                return true;
            }
        }
        return false;
    }
    
}

