package com.company;

public class Transaction {
    private String userName;
    private String operation;

    private int hashCode;

    public Transaction(String userName, String operation) {
        this.userName = userName;
        this.operation = operation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperation() {
        return operation;
    }

    public void setTransactions(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Transaction)
        {
            Transaction temp = (Transaction) obj;
            if(this.userName.equals(temp.userName) && this.operation.equals(temp.operation))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return (this.userName.hashCode() + this.operation.hashCode());
    }
}
