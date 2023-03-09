package com.kyraymege.linkshortener.controllers;

import com.kyraymege.linkshortener.business.concretes.LinkManager;
import com.kyraymege.linkshortener.models.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/link")
public class LinkController {
    private final LinkManager linkManager;

    public LinkController(LinkManager linkManager) {
        this.linkManager = linkManager;
    }

    @PostMapping(value = {"/create","/create/{code}"})
    public ResponseEntity<Link> createLink(@RequestBody Link link, @PathVariable(value = "code",required = false) Optional<String> linkCode){
        return  new ResponseEntity<>(linkManager.createCode(link,linkCode), HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Link> redirectLink(@PathVariable(value = "code") String code) throws URISyntaxException {
        Link link = linkManager.getUrlByCode(code);
        URI uri = new URI(link.getUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<>(headers,HttpStatus.SEE_OTHER);
    }

    @GetMapping("/show/{code}")
    public ResponseEntity<Link> showLink(@PathVariable(value = "code") String code) throws URISyntaxException {
        Link link = linkManager.getUrlByCode(code);
        return new ResponseEntity<>(link,HttpStatus.OK);
    }
}
