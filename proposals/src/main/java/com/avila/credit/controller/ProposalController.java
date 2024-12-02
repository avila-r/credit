package com.avila.credit.controller;

import lombok.AllArgsConstructor;

import com.avila.credit.dto.ProposalRequest;
import com.avila.credit.dto.ProposalResponse;
import com.avila.credit.service.ProposalService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/proposals")
@RestController public class ProposalController {

    private final ProposalService service;

    @PostMapping
    ResponseEntity<ProposalResponse> post(@RequestBody ProposalRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProposalResponse.from(service.create(request)));
    }

    @GetMapping
    ResponseEntity<List<ProposalResponse>> all() {
        return ResponseEntity.ok (
                service.list().stream()
                        .map(ProposalResponse::from)
                        .toList()
        );
    }

}
