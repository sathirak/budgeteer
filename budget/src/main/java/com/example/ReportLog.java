package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportLog {

    public void reportLog(String data_path) {
        try {
            System.out.println(data_path);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(new File(data_path));

            if (jsonArray.isArray()) {
                Main.clear_screen();

                int max_graph_income = 0;

                // Iterate over the last 12 months
                for (int i = 0; i < 12; i++) {

                    YearMonth targetMonth = YearMonth.now().minusMonths(i);

                    double netIncome = calculateNetIncome(jsonArray, targetMonth);
                    int graph_income = (int)netIncome;

                    if (graph_income < 0) {
                        graph_income = graph_income*(-1);
                    }

                    if (graph_income > max_graph_income) {
                        max_graph_income = graph_income;
                    }

                }


                for (int i = 0; i < 12; i++) {

                    YearMonth targetMonth = YearMonth.now().minusMonths(i);
                    String monthAbbreviation = targetMonth.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

                    double netIncome = calculateNetIncome(jsonArray, targetMonth);
                    
                    int graph_income = (int)netIncome;

                    

                    String graph_bar = "";

                    graph_income = graph_income*100;

                    graph_income = graph_income/max_graph_income;

                    if (graph_income < 0) {

                        graph_bar = "\u001B[37m";
                        graph_income = graph_income*(-1);

                    } else {
                        
                        if (graph_income != 0 && graph_income < 1) {
                            graph_income = 1;
                        }


                        if (graph_income > 75) {
                            graph_bar = "\u001B[33m";
                            
                        } else {
                            graph_bar = "\u001B[31m";

                        }
                    }

                    for (int j = 0; j < graph_income; j++) {
                        graph_bar += "|";
                    }

                    graph_bar += "\u001B[0m";

                    System.out.printf("\n %d %s %s \n [ \u001b[32m%.2f\u001B[0m LKR ]\n", targetMonth.getYear(), monthAbbreviation,graph_bar, netIncome);

                }
            } else {
                System.out.println("The JSON file does not contain an array.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private double calculateNetIncome(JsonNode jsonArray, YearMonth targetMonth) throws ParseException {
        double netIncome = 0.0;

        for (JsonNode jsonNode : jsonArray) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(jsonNode.get("date").asText());
            YearMonth logMonth = YearMonth.from(date.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate());

            if (logMonth.equals(targetMonth)) {
                boolean income = jsonNode.get("income").asBoolean();
                double amount = jsonNode.get("amount").asDouble();

                if (income) {
                    netIncome += amount;
                } else {
                    netIncome -= amount;
                }
            }
        }

        return netIncome;
    }
}
