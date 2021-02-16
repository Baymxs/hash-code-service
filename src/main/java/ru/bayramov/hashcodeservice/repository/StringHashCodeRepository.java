package ru.bayramov.hashcodeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bayramov.hashcodeservice.entity.StringHashCode;

public interface StringHashCodeRepository extends JpaRepository<StringHashCode, String> {
}
