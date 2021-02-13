package ru.bayramov.hashcodeservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HashCodeReq {
    private final String type;
    private final Object data;
}
