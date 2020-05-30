package ru.itis.webfluxrabbitmqstudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.webfluxrabbitmqstudy.entity.dto.BlacklistPersonDto;
import ru.itis.webfluxrabbitmqstudy.service.BlacklistService;

@RestController
public class BlacklistController {
    private BlacklistService service;

    public BlacklistController(BlacklistService service) {
        this.service = service;
    }

    @PostMapping("/blacklist")
    @ResponseBody
    public ResponseEntity addBlacklistPerson(BlacklistPersonDto dto) {
        service.addBlacklistPerson(dto);
        return ResponseEntity.ok().build();
    }
}
