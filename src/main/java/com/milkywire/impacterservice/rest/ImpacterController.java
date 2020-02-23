package com.milkywire.impacterservice.rest;

import com.milkywire.impacterservice.dto.ImpacterDto;
import com.milkywire.impacterservice.service.ImpacterService;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/impacters")
public class ImpacterController {

    @Inject
    private ImpacterService impacterService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ImpacterDto getMessage() {
        ImpacterDto impacterDto = new ImpacterDto();
        impacterDto.setMessage(impacterService.getProperMessage());

        return impacterDto;
    }
}

