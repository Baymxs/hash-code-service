package ru.bayramov.hashcodeservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class HashCodeRes {
    private final int hashCode;
}
