package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Sale;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleDao implements SaleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSaleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Sale getSaleById(int saleId) {
        Sale sale = null;
        String sql = "SELECT sale_id, total, is_delivery, customer_id " +
                     "FROM sale " +
                     "WHERE sale_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
            if (results.next()) {
                sale = mapRowToSale(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sale;
    }

    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT sale_id, total, is_delivery, customer_id " +
                     "FROM sale " +
                     "WHERE customer_id = ? " +
                     "ORDER BY sale_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
            while (results.next()) {
                Sale sale = mapRowToSale(results);
                sales.add(sale);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sales;
    }

    @Override
    public Sale createSale(Sale sale) {
        Sale newSale = null;
        String sql = "INSERT INTO sale (total, is_delivery, customer_id) " +
                     "VALUES (?, ?, ?) RETURNING sale_id;";
        try {
            int saleId =
                    jdbcTemplate.queryForObject(sql, int.class, sale.getTotal(),
                            sale.isDelivery(), sale.getCustomerId());
            // Set the saleId attribute of the Sale object to the saleId returned.
            newSale = getSaleById(saleId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newSale;
    }

    @Override
    public Sale updateSale(Sale sale) {
        Sale updatedSale = null;
        String sql = "UPDATE sale SET total = ?, is_delivery = ?, customer_id = ? " +
                     "WHERE sale_id = ?;";
        try {
            int numberOfRows =
                    jdbcTemplate.update(sql, sale.getTotal(), sale.isDelivery(), sale.getCustomerId(),
                            sale.getSaleId());
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedSale = getSaleById(sale.getSaleId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedSale;
    }

    @Override
    public int deleteSaleById(int saleId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM sale WHERE sale_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, saleId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Sale mapRowToSale(SqlRowSet rowSet) {
        Sale sale = new Sale();
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
