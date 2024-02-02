package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowLog {

    public void showLog(String data_path) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(new File(data_path));

            if (jsonArray.isArray()) {

                Main.clear_screen();

                System.out.println();

                double balance = 0.00;

                for (JsonNode jsonNode : jsonArray) {
                    int id = jsonNode.get("id").asInt();
                    String dateString = jsonNode.get("date").asText();
                    boolean income = jsonNode.get("income").asBoolean();
                    String category = jsonNode.get("category").asText();
                    double amount = jsonNode.get("amount").asDouble();
                    String description = jsonNode.get("description").asText();

                    String incomeText = income ? "\u001B[32m[ + ]\u001B[0m" : "\u001B[31m[ - ]\u001B[0m";
                    balance += income ? amount : -amount;

                    // Format the date
                    String formattedDate = formatDate(dateString);

                    System.out.printf(" %-9s %-4d %-15s  %-8.2f  %-15s  %-35s\n",
                            incomeText, id, category, amount, formattedDate, description);
                }

                System.out.printf("\n\n\u001b[33m [ ! ]\u001B[0m  Current Balance: %.2f\u001B[0m\n", balance);
            } else {
                System.out.println("The JSON file does not contain an array.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private String formatDate(String dateString) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        return new SimpleDateFormat("dd-MMM-yyyy").format(date);
    }
}
