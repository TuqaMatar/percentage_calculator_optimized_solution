package com.company;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TransactionExtractor transactionExtractor =
                new TransactionExtractor("./res/files/trackFile2015-03-08.log");
        List<Transaction> transactions = transactionExtractor.getTransactionList();
        PercentageCalculator percentageCalculator = new PercentageCalculator(transactions);

        percentageCalculator.showTopPercentage(2); //gets the top two used operation and its percentage
        percentageCalculator.showTopPercentage(3); //gets the top three most used operation and their percentages
    }
}
