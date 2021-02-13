package ru.bayramov.hashcodeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bayramov.hashcodeservice.dto.HashCodeReq;
import ru.bayramov.hashcodeservice.dto.HashCodeRes;
import ru.bayramov.hashcodeservice.service.HashCodeService;

@RestController
@RequestMapping("/api/v1/hash-code/")
@RequiredArgsConstructor
public class HashCodeController {
    private final HashCodeService hashCodeService;

    @PostMapping
    public HashCodeRes generate(@RequestBody HashCodeReq hashCodeReq) throws InterruptedException {
        return new HashCodeRes(hashCodeService.generate(hashCodeReq.getType(), hashCodeReq.getData()));
    }
}
