package com.kyraymege.linkshortener.business.abstracts;

import com.kyraymege.linkshortener.models.Link;

import java.util.Optional;

public interface LinkService {
    Link getUrlByCode(String code);
    Link createCode(Link link, Optional<String> linkCode);

}
