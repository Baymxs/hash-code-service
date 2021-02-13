package ru.bayramov.hashcodeservice.service.generator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component("String")
@RequiredArgsConstructor
@Slf4j
public class StringHashCodeGenerator implements HashCodeGenerator {

    @Override
    @Cacheable(value = "stringHashCode", key = "#data", sync = true)
    public int generate(String type, Object data) throws InterruptedException {
        log.info("Hash calculating");

        String s = (String) data;
        Thread.sleep(20000);

        return s.hashCode();
    }
}
