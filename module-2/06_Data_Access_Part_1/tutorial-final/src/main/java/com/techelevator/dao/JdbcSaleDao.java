package com.techelevator.dao;

import com.techelevator.model.Sale;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;

public class JdbcSaleDao implements SaleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSaleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BigDecimal getTotalSales() {
        BigDecimal total = null;

        // Step Two: Add SQL for retrieving total sales
        String sql = "SELECT SUM(total) AS total FROM sale;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        if (results.next()) {
            total = results.getBigDecimal("total");
        }

        return total;
    }

    @Override
    public Sale getSaleById(int saleId) {
        Sale sale = null;
        String sql = "SELECT sale_id, total, is_delivery, customer_id " +
                     "FROM sale " +
                     "WHERE sale_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        if (results.next()) {
            sale = mapRowToSale(results);
        }
        return sale;
    }

    private Sale mapRowToSale(SqlRowSet rowSet) {
        Sale sale = new Sale();
        // Step Three: Copy returned values into an object
        sale.setSaleId(rowSet.getInt("sale_id"));
        sale.setTotal(rowSet.getBigDecimal("total"));
        sale.setDelivery(rowSet.getBoolean("is_delivery"));
        sale.setCustomerId(rowSet.getInt("customer_id"));
        if (rowSet.wasNull()) {
            sale.setCustomerId(null);
        }
        return sale;
    }
}
