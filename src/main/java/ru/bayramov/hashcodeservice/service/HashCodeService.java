package ru.bayramov.hashcodeservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bayramov.hashcodeservice.service.generator.HashCodeGenerator;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class HashCodeService {
    private final Map<String, HashCodeGenerator> hashCodeGeneratorMap;

    public int generate(String type, Object data) throws InterruptedException {
        HashCodeGenerator hashCodeGenerator = hashCodeGeneratorMap.get(type);

        if (hashCodeGenerator == null) {
            throw new UnsupportedOperationException(type + " is not supported");
        }

        return hashCodeGenerator.generate(type, data);
    }
}
