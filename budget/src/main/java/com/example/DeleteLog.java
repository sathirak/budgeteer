package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class DeleteLog {

    public void deleteLog(String data_path) {

        Scanner scanner = new Scanner(System.in);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            ArrayNode jsonArray = (ArrayNode) objectMapper.readTree(new File(data_path));

            if (jsonArray.isArray()) {

                Main.clear_screen();
                System.out.print("\n\u001b[31m[ > ]\u001B[0m Log ID to delete > ");
                int targetId = scanner.nextInt();

                boolean idFound = false;

                Iterator<JsonNode> iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JsonNode jsonNode = iterator.next();

                    int id = jsonNode.get("id").asInt();

                    if (id == targetId) {
                        Main.clear_screen();

                        System.out.println("\n ");
                        System.out.println("\u001b[30m     [ ### Deleting log with ID " + id + " ### ]   \u001B[0m");
                        System.out.println("\n ");
                        System.out.println("\u001b[36m[ > ]\u001B[0m Deleting log with ID: " + id);
                        System.out.println();

                        // Remove the node from the array using the iterator
                        iterator.remove();

                        // Write the updated array back to the file
                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(data_path), jsonArray);

                        System.out.println("Log with ID " + id + " deleted successfully.");

                        idFound = true;

                        break;
                    }
                }

                if (!idFound) {
                    System.out.println("ID " + targetId + " not found in the JSON file.");
                }

            } else {
                System.out.println("The JSON file does not contain an array.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
