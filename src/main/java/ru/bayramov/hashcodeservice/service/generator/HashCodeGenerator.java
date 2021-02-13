package ru.bayramov.hashcodeservice.service.generator;

public interface HashCodeGenerator {
    int generate(String type, Object data) throws InterruptedException;
}
