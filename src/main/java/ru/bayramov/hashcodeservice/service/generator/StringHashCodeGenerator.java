package ru.bayramov.hashcodeservice.service.generator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.bayramov.hashcodeservice.entity.StringHashCode;
import ru.bayramov.hashcodeservice.repository.StringHashCodeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("String")
@RequiredArgsConstructor
@Slf4j
public class StringHashCodeGenerator implements HashCodeGenerator {
    private final StringHashCodeRepository stringHashCodeRepository;
    private final Map<String, Lock> stringLockMap = new HashMap<>();
    private final Map<String, Integer> stringAliveWaitersCountMap = new HashMap<>();

    @Override
    public int generate(String type, Object data) throws InterruptedException {
        int hashCode;

        String s = (String) data;
        Lock lock;
        boolean master = false;

        synchronized (this) {
            Optional<StringHashCode> hashCodeFromDb = stringHashCodeRepository.findById(s);
            //если хэш в БД есть - возвращаем его
            if (hashCodeFromDb.isPresent()) {
                return hashCodeFromDb.get().getHashCode();
            }
            //если хэш отсутсвует в БД
            if (stringLockMap.get(s) == null) {
                stringLockMap.put(s, new ReentrantLock());
                lock = stringLockMap.get(s);
                stringAliveWaitersCountMap.put(s, 1);
                lock.lock();
                master = true;
            } else {
                stringAliveWaitersCountMap.put(s, stringAliveWaitersCountMap.get(s) + 1);
                lock = stringLockMap.get(s);
            }
        }

        if (master) {
            Thread.sleep(20000);
            hashCode = s.hashCode();
            stringHashCodeRepository.save(new StringHashCode(s, hashCode));
        } else {
            lock.lock();
        }
        stringAliveWaitersCountMap.put(s, stringAliveWaitersCountMap.get(s) - 1);
        if (stringAliveWaitersCountMap.get(s) == 0) {
            stringAliveWaitersCountMap.remove(s);
        } else {
            lock.unlock();
        }

        return stringHashCodeRepository.findById(s).get().getHashCode();
    }
}
