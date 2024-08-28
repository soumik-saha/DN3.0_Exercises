package main.java.com.example.bookstoreapi.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CustomMetrics {

    @Autowired
    private MeterRegistry meterRegistry;

    @PostConstruct
    public void init() {
        meterRegistry.counter("custom.metric.book.count").increment();
    }

    public void incrementBookCount() {
        meterRegistry.counter("custom.metric.book.count").increment();
    }
}