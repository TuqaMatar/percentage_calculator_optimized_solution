package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TransactionExtractor {
    List<Transaction> transactionList ;
    String filePath ;

    public TransactionExtractor(String filePath){
        this.filePath =filePath;
    }

    public static List<String> getTransactionsFormat(String filePath) {
        List<String> transactions = new ArrayList<String>();
        try {
            FileInputStream fstream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            String[] strSplit;

            //strLine is null when it reaches the end of the file
            while ((strLine = bufferedReader.readLine()) != null) {
                strSplit = strLine.split("\s\s\s\s\s");
                transactions.add(strSplit[1]);
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return transactions;
    }

    public static List<Transaction> getTransactions(String filePath) {
        List<String> transactions = getTransactionsFormat(filePath);
        List<Transaction> alluserTransactions = new ArrayList<Transaction>();
        String[] strSplit;
        for (String transaction : transactions) {
            strSplit = transaction.split("/");
            Transaction newTransaction = new Transaction(strSplit[1], strSplit[2]);
            alluserTransactions.add(newTransaction);
        }

        return alluserTransactions;

    }

    public List<Transaction> getTransactionList() {
        transactionList = getTransactions(filePath);
        return transactionList;
    }
}
