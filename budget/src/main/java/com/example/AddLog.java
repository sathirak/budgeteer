package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddLog {

    public void addLog(String data_path, String meta_path) {

        Scanner scanner = new Scanner(System.in);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonArray = objectMapper.readTree(new File(data_path));

            if (jsonArray.isArray()) {

                Main.clear_screen();

                ObjectNode newEntry = JsonNodeFactory.instance.objectNode();

                int newId = generateNewId(jsonArray);
                newEntry.put("id", newId);

                System.out.println("\u001b[32m[ > ]\u001B[0m id  > " + newId);

                System.out.print("\u001b[36m[ i ]  enter date or <enter> for today");
                String inputDate = scanner.nextLine().trim();

                if (inputDate.isEmpty()) {
                    LocalDate today = LocalDate.now();
                    inputDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
                }

                System.out.println("\n\u001b[32m[ > ]\u001B[0m date  > " + inputDate);
                newEntry.put("date", inputDate);

                System.out.print("\n\u001b[32m[ > ]\u001B[0m amount  > ");
                double amount = scanner.nextDouble();

                newEntry.put("income", amount >= 0 ? true : false);

                newEntry.put("amount", Math.abs(amount));

                System.out.print("\n\u001b[32m[ > ]\u001B[0m category  > ");

                getCategory(meta_path);

                String categoryShortcut;
        
                do {
                    categoryShortcut = scanner.next();
                    String fullCategory = getCategoryFromShortcut(categoryShortcut);
        
                    if (fullCategory != null) {
                        newEntry.put("category", fullCategory);
                        break;
                    } else {
                        System.out.print("\u001b[31m[ ! ]\u001B[0m  Please enter a valid category shortcut: ");
                    }
                } while (true);

                System.out.print("\n\u001b[32m[ > ]\u001B[0m remarks  > ");
                String remarks = scanner.nextLine().trim();

                newEntry.put("description", remarks);

                scanner.nextLine();

                ((ArrayNode) jsonArray).add(newEntry);

                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(data_path), jsonArray);

                System.out.println("\n\u001b[31m[ ! ]\u001B[0m  New entry added successfully.");

            } else {
                System.out.println("\u001b[31m[ ! ]\u001B[0m  The JSON file does not contain an array.");
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static int generateNewId(JsonNode jsonArray) {
        int maxId = 0;

        for (JsonNode jsonNode : jsonArray) {
            int id = jsonNode.get("id").asInt();
            maxId = Math.max(maxId, id);
        }

        return maxId + 1;
    }

    private void getCategory(String meta_path) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode categoriesJson = objectMapper.readTree(new File(meta_path));

            if (categoriesJson.has("categories")) {

                System.out.println("\n");

                categoriesJson.get("categories").fields().forEachRemaining(entry -> {
                    System.out.println(entry.getKey() + " => " + entry.getValue().asText());
                });

                System.out.println("\n");

            } else {
                System.out.println("\u001b[31m[ ! ]\u001B[0m  Invalid meta.json format.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCategoryFromShortcut(String shortcut) {
        String meta_path = "meta.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode categoriesJson = objectMapper.readTree(new File(meta_path));

            if (categoriesJson.has("categories")) {
                JsonNode categoryNode = categoriesJson.get("categories").get(shortcut);

                if (categoryNode != null) {
                    return categoryNode.asText();
                } else {
                    System.out.println("\u001b[31m[ ! ]\u001B[0m  Category shortcut not found.");
                }
            } else {
                System.out.println("\u001b[31m[ ! ]\u001B[0m  Invalid meta.json format.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
