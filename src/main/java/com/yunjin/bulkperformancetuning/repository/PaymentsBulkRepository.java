package com.yunjin.bulkperformancetuning.repository;

import com.yunjin.bulkperformancetuning.entity.Payments;
import java.util.List;

public interface PaymentsBulkRepository {

    void saveAll(List<Payments> paymentsList);

}
