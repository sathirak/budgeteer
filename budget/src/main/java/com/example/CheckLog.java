package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckLog {

    public void checkLog(String data_path) {
        
        Scanner scanner = new Scanner(System.in);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonArray = objectMapper.readTree(new File(data_path));

            
            if (jsonArray.isArray()) {

                Main.clear_screen();
                System.out.print("\n\u001b[31m[ > ]\u001B[0m log id to check  >   ");
                int targetId = scanner.nextInt();

                
                boolean idFound = false;

                
                for (JsonNode jsonNode : jsonArray) {
                    
                    int id = jsonNode.get("id").asInt();

                    
                    if (id == targetId) {
                        String date = jsonNode.get("date").asText();
                        String income = jsonNode.get("income").asText();
                        String category = jsonNode.get("category").asText();
                        double amount = jsonNode.get("amount").asDouble();
                        String description = jsonNode.get("description").asText();

                        Main.clear_screen();

                        System.out.println("\n ");
                        System.out.println("     [ ###  details for log id " + id + "  ### ]   ");
                        System.out.println("\n ");
                        System.out.println("\u001b[36m[ > ]\u001B[0m date      > " + date);
                        System.out.println("\u001b[36m[ > ]\u001B[0m income    > " + income);
                        System.out.println("\u001b[36m[ > ]\u001B[0m category  > " + category);
                        System.out.println("\u001b[36m[ > ]\u001B[0m amount    > " + amount);
                        System.out.println("\u001b[36m[ > ]\u001B[0m summary   > " + description);
                        System.out.println();

                        
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
