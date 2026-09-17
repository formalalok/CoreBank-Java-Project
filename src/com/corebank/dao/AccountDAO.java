package com.corebank.dao;

import com.corebank.model.Account;
import com.corebank.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    public Account getAccount(long accountNumber) {

        String sql = "SELECT * FROM accounts WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getLong("account_number"),
                    rs.getInt("customer_id"),
                    rs.getString("account_type"),
                    rs.getBigDecimal("balance"),
                    rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBalance(long accountNumber, BigDecimal balance) {

        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, balance);
            ps.setLong(2, accountNumber);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateStatus(long accountNumber, String status) {

        String sql = "UPDATE accounts SET status = ? WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setLong(2, accountNumber);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
public Account getAccount(Connection con, long accountNumber) {

    String sql = "SELECT * FROM accounts " +
                 "WHERE account_number = ? FOR UPDATE";

    try (PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setLong(1, accountNumber);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Account(
                rs.getLong("account_number"),
                rs.getInt("customer_id"),
                rs.getString("account_type"),
                rs.getBigDecimal("balance"),
                rs.getString("status")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

public boolean updateBalance(Connection con,
                             long accountNumber,
                             BigDecimal balance) {

    String sql = "UPDATE accounts SET balance = ? " +
                 "WHERE account_number = ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setBigDecimal(1, balance);
        ps.setLong(2, accountNumber);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
public boolean belongsToCustomer(long accountNumber,
                                 int customerId) {

    String sql =
            "SELECT * FROM accounts " +
            "WHERE account_number = ? AND customer_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setLong(1, accountNumber);
        ps.setInt(2, customerId);

        ResultSet rs = ps.executeQuery();

        return rs.next();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
}
