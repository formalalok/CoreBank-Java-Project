package com.corebank.dao;

import com.corebank.model.Transaction;
import com.corebank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public boolean saveTransaction(Transaction transaction) {

        String sql = "INSERT INTO transactions " +
                "(from_account, to_account, amount, type, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (transaction.getFromAccount() == null)
                ps.setNull(1, java.sql.Types.BIGINT);
            else
                ps.setLong(1, transaction.getFromAccount());

            if (transaction.getToAccount() == null)
                ps.setNull(2, java.sql.Types.BIGINT);
            else
                ps.setLong(2, transaction.getToAccount());

            ps.setBigDecimal(3, transaction.getAmount());
            ps.setString(4, transaction.getType());
            ps.setString(5, transaction.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Transaction> getTransactions(long accountNumber) {

        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transactions " +
                "WHERE from_account = ? OR to_account = ? " +
                "ORDER BY timestamp DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, accountNumber);
            ps.setLong(2, accountNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Long fromAccount = rs.getObject("from_account", Long.class);
                Long toAccount = rs.getObject("to_account", Long.class);

                Transaction transaction = new Transaction(
                    rs.getInt("txn_id"),
                    fromAccount,
                    toAccount,
                    rs.getBigDecimal("amount"),
                    rs.getString("type"),
                    rs.getTimestamp("timestamp"),
                    rs.getString("status")
                );

                transactions.add(transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
public boolean saveTransaction(Connection con,
                               Transaction transaction) {

    String sql = "INSERT INTO transactions " +
            "(from_account, to_account, amount, type, status) " +
            "VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement ps = con.prepareStatement(sql)) {

        if (transaction.getFromAccount() == null)
            ps.setNull(1, java.sql.Types.BIGINT);
        else
            ps.setLong(1, transaction.getFromAccount());

        if (transaction.getToAccount() == null)
            ps.setNull(2, java.sql.Types.BIGINT);
        else
            ps.setLong(2, transaction.getToAccount());

        ps.setBigDecimal(3, transaction.getAmount());
        ps.setString(4, transaction.getType());
        ps.setString(5, transaction.getStatus());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
}