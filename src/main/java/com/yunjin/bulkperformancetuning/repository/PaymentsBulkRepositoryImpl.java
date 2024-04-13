package com.yunjin.bulkperformancetuning.repository;

import com.yunjin.bulkperformancetuning.entity.Payments;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentsBulkRepositoryImpl implements PaymentsBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAll(List<Payments> paymentsList) {
        String sql = "INSERT INTO payment "
            + "(order_id, amount, created_at, updated_at) "
            + "VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
            paymentsList,
            paymentsList.size(),
            (PreparedStatement ps, Payments payments) -> {
                ps.setString(1, String.valueOf(payments.getOrderId()));
                ps.setString(2, String.valueOf(payments.getAmount()));
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            });
    }
}
