package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class InvoiceDAO {
    private final JdbcTemplate jdbc;

    public InvoiceDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Invoice> RM = (rs, i) -> {
        Invoice inv = new Invoice();
        inv.setId(rs.getInt("id"));
        inv.setDescription(rs.getString("description"));
        inv.setPacking(rs.getString("packing"));
        inv.setQty(rs.getInt("qty"));
        inv.setRate(rs.getDouble("rate"));
        inv.setGst(rs.getDouble("gst"));
        inv.setDiscount(rs.getDouble("discount"));
        Date d = rs.getDate("expiry_date");
        if (d != null) inv.setExpiryDate(d.toLocalDate());
        inv.setTotal(rs.getDouble("total"));
        return inv;
    };

    public void save(Invoice invoice) {
        String sql = "INSERT INTO medical_invoice(description, packing, qty, rate, gst, discount, expiry_date, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
            invoice.getDescription(),
            invoice.getPacking(),
            invoice.getQty(),
            invoice.getRate(),
            invoice.getGst(),
            invoice.getDiscount(),
            invoice.getExpiryDate() == null ? null : Date.valueOf(invoice.getExpiryDate()),
            invoice.getTotal()
        );
    }

    public List<Invoice> findAll() {
        return jdbc.query("SELECT * FROM medical_invoice ORDER BY created_at DESC", RM);
    }

    public void deleteById(Integer id) {
        jdbc.update("DELETE FROM medical_invoice WHERE id = ?", id);
    }
}
