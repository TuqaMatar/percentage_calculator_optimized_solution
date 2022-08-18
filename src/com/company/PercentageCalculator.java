package com.company;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PercentageCalculator {
    final DecimalFormat df = new DecimalFormat("0.00");
    Map<String, Double> occurrence = new HashMap<>();
    Map<String, Double> percentage = new HashMap<>();
    List<Transaction> transactions = new ArrayList<>();
    Set<Transaction> noDuplicateTransactionList;

    public PercentageCalculator(List<Transaction> transactions) {

        this.transactions = transactions;
        //initilize map with occurence value of 0
        for (Transaction transaction : transactions) {
            occurrence.put(transaction.getOperation(), 0.0);
        }

        //initilize map with percentage value of 0
        for (Transaction transaction : transactions) {
            percentage.put(transaction.getOperation(), 0.0);
        }

        //---> Improvement
        noDuplicateTransactionList = new HashSet<Transaction>();
        noDuplicateTransactionList.addAll(transactions);

    }

    //--> This Code is Now not needed Since we removed duplicated by using Sets

//    public static boolean allreadyLogged(List<Transaction> transactions, Transaction transaction) {
//        return transactions.stream().anyMatch(o -> (transaction.getUserName().
//                equals(o.getUserName()) && transaction.getOperation().equals(o.getOperation())));
//    }

    public void calculateOccurrence() {
        //traverse the list and check if it was viewed then increment the occurrence
        for (Transaction transaction : noDuplicateTransactionList) {
            occurrence.put(transaction.getOperation(), occurrence.get(transaction.getOperation()) + 1);
        }
    }

    public void calculatePercentage() {
        ExecutorService service = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 1);
        calculateOccurrence();

        //calculate percentage of each occurrence and add to percentage map
        for (Transaction transaction : noDuplicateTransactionList) {
            final Double unformatted = occurrence.get(transaction.getOperation()) / noDuplicateTransactionList.size() * 100;
            percentage.put(transaction.getOperation(), Double.valueOf(df.format(unformatted)));
        }
    }

    public void showTopPercentage() {
        calculatePercentage();
        System.out.println("top operation and its percentage");

        percentage.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(1)
                .forEach(System.out::println);
    }

    public void showTopPercentage(int top) {
        calculatePercentage();

        System.out.println("top " + top + " operations and their percentages");
        percentage.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(top)
                .forEach(System.out::println);
    }
}
