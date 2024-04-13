package com.yunjin.bulkperformancetuning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "payment2")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Payments2 extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(precision = 19, scale = 4)
    private BigDecimal amount;

    @Builder
    public Payments2(Long orderId, BigDecimal amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}
