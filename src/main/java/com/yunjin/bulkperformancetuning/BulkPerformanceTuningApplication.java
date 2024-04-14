package com.yunjin.bulkperformancetuning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BulkPerformanceTuningApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkPerformanceTuningApplication.class, args);
	}

}
