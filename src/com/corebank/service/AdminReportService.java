package com.corebank.service;

import com.corebank.dao.AccountDAO;
import com.corebank.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminReportService {

    private AccountDAO accountDAO;

    public AdminReportService() {
        accountDAO = new AccountDAO();
    }

    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();

        String sql = "SELECT * FROM accounts ORDER BY account_number";

        try (Connection con =
                     com.corebank.util.DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Account account = new Account(
                        rs.getLong("account_number"),
                        rs.getInt("customer_id"),
                        rs.getString("account_type"),
                        rs.getBigDecimal("balance"),
                        rs.getString("status")
                );

                accounts.add(account);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return accounts;
    }

    public int getTotalAccounts() {
        return getAllAccounts().size();
    }

    public int getActiveAccounts() {

        int count = 0;

        for (Account account : getAllAccounts()) {

            if ("ACTIVE".equals(account.getStatus())) {
                count++;
            }
        }

        return count;
    }

    public int getFrozenAccounts() {

        int count = 0;

        for (Account account : getAllAccounts()) {

            if ("FROZEN".equals(account.getStatus())) {
                count++;
            }
        }

        return count;
    }
}
