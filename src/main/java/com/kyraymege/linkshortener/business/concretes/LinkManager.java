package com.kyraymege.linkshortener.business.concretes;

import com.kyraymege.linkshortener.business.abstracts.LinkService;
import com.kyraymege.linkshortener.exceptions.LinkAPIException;
import com.kyraymege.linkshortener.models.Link;
import com.kyraymege.linkshortener.repositories.LinkRepository;
import com.kyraymege.linkshortener.util.CodeGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LinkManager implements LinkService {

    private final LinkRepository linkRepository;
    private final CodeGenerator codeGenerator;

    public LinkManager(LinkRepository linkRepository, CodeGenerator codeGenerator) {
        this.linkRepository = linkRepository;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public Link getUrlByCode(String code) {
        Link url = linkRepository.findByCode(code);
        url.setClick_count(url.getClick_count()+1);
        linkRepository.save(url);
        return url;
    }

    @Override
    public Link createCode(Link link, Optional<String> linkCode) {
        if (linkCode.isPresent()) {
            if (linkRepository.existLinkByCode(linkCode.get().toUpperCase())) {
                throw new LinkAPIException(linkCode.get(), HttpStatus.BAD_REQUEST, "Code already exists!");
            } else {
                link.setCreatedAt(LocalDateTime.now());
                link.setCode(linkCode.get().toUpperCase());
                link.setClick_count(Long.valueOf(0));
                linkRepository.save(link);
            }
        } else {
            link.setCode(generateCode());
            link.setCreatedAt(LocalDateTime.now());
            link.setClick_count(Long.valueOf(0));
            linkRepository.save(link);
        }
        return link;
    }

    private String generateCode() {
        String code;
        do {
            code = codeGenerator.generateRandomString();
        } while (linkRepository.existLinkByCode(code));

        return code;
    }
}
