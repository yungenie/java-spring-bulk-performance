package com.yunjin.bulkperformancetuning.entity;

import com.yunjin.bulkperformancetuning.repository.Payments2Repository;
import com.yunjin.bulkperformancetuning.repository.PaymentsBulkRepository;
import com.yunjin.bulkperformancetuning.repository.PaymentsRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@Rollback(value = false)
class PaymentsTest {

    private static final int COUNT = 10_000;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private Payments2Repository payments2Repository;

    @Autowired
    private PaymentsBulkRepository paymentsBulkRepository;

    //@Test
    @DisplayName("jpa identity 전략 save 단건 성공")
    void jpa_identity_save_single() {
        Payments payments = Payments.builder()
            .orderId((long) COUNT)
            .amount(BigDecimal.valueOf(COUNT))
            .build();

        paymentsRepository.save(payments);
    }

    @Test
    @DisplayName("jpa identity 전략 save 다건 성공")
    void jpa_identity_save_multiple() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            Payments payments = Payments.builder()
                .orderId((long) i)
                .amount(BigDecimal.valueOf(i))
                .build();
            paymentsRepository.save(payments);
        }
        printElapsedTime(startTime);
    }

    @Test
    @DisplayName("jpa identity 전략 saveAll 성공, batch insert 비활성화")
    void jpa_identity_saveAll() {
        long startTime = System.currentTimeMillis();

        List<Payments> paymentsList = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            Payments payments = Payments.builder()
                .orderId((long) COUNT)
                .amount(BigDecimal.valueOf(COUNT))
                .build();
            paymentsList.add(payments);
        }
        paymentsRepository.saveAll(paymentsList);

        printElapsedTime(startTime);
    }

    @Test
    @DisplayName("jpa uuid 전략 saveAll 성공, batch insert 활성화")
    void jpa_uuid_saveAll() {
        long startTime = System.currentTimeMillis();

        List<Payments2> payments2List = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            Payments2 payments2 = Payments2.builder()
                .orderId((long) COUNT)
                .amount(BigDecimal.valueOf(COUNT))
                .build();
            payments2List.add(payments2);
        }
        payments2Repository.saveAll(payments2List);

        printElapsedTime(startTime);
    }

    @Test
    @DisplayName("jdbcTemplate identity 전략 saveAll 성공, batch insert 활성화")
    void jdbcTemplate_identity_saveAll() {
        long startTime = System.currentTimeMillis();

        List<Payments> paymentsList = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            Payments payments = Payments.builder()
                .orderId((long) COUNT)
                .amount(BigDecimal.valueOf(COUNT))
                .build();
            paymentsList.add(payments);
        }
        paymentsBulkRepository.saveAll(paymentsList);

        printElapsedTime(startTime);
    }

    private void printElapsedTime(long startTime) {
        long endTime = System.currentTimeMillis();
        double elapsedTime = (double) (endTime - startTime) / 1000.0;
        System.out.println("---------------------------------");
        System.out.printf("수행시간 : %.3f %s\n", elapsedTime, "(초)");
        System.out.println("---------------------------------");
    }
}