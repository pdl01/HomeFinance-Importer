/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfw.homefinance.importer;

import java.util.List;

/**
 *
 * @author pldorrell
 */
public class FileAccount {
    private String accountName;
    private String financialInstitution;
    private String accountBalance;
    private List<FileTransaction> transactions;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFinancialInstitution() {
        return financialInstitution;
    }

    public void setFinancialInstitution(String financialInstitution) {
        this.financialInstitution = financialInstitution;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<FileTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<FileTransaction> transactions) {
        this.transactions = transactions;
    }
    
    
}
