package com.yunjin.bulkperformancetuning.repository;

import com.yunjin.bulkperformancetuning.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

}
