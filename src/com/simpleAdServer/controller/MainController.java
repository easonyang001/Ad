package com.simpleAdServer.controller;


import com.simpleAdServer.DTO.ServerCampaigh;
import com.simpleAdServer.DTO.ServerCampaighRequest;
import com.simpleAdServer.error.ServerCampaighError;
import com.simpleAdServer.error.ServerCampaighException;
import com.simpleAdServer.service.ServerCampaighRepo;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MainController {
    private final static Logger LOGGER = Logger.getLogger(MainController.class);

    @RequestMapping(value = "/ad", method = RequestMethod.POST)
    public ServerCampaighRequest createNewAd(@Valid  @RequestBody ServerCampaighRequest request) {
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("POST JSON Request got : "+request);
        }

        ServerCampaighRepo.getInstance().add(request);

        return request;
    }

    @RequestMapping(value = "/ad/{partner_id}", method = RequestMethod.GET)
    public ServerCampaigh getAd(@PathVariable("partner_id") String partnerId) {
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("GET Request got : "+partnerId);
        }
        return ServerCampaighRepo.getInstance().getRepo().get(partnerId);
    }

    @ExceptionHandler(ServerCampaighException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ServerCampaighError handleAdTimeConflictException(MethodArgumentNotValidException e){
        LOGGER.error(e);
        return new ServerCampaighError("Ad Already Exists " + HttpStatus.BAD_REQUEST,"The Partner already have an existing AdCampaigh.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ServerCampaighError handleValidationException(MethodArgumentNotValidException e){
        LOGGER.error(e);
        return new ServerCampaighError("Parameter Error " + HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ServerCampaighError handleException(Exception e){
        LOGGER.error(e);
        return new ServerCampaighError("Something Unexpected Error " + HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
    }
}
