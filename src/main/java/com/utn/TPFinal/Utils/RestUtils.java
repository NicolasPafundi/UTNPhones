package com.utn.TPFinal.Utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtils {
    public static URI GetLocation(Integer id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+id)
                .buildAndExpand()
                .toUri();
    }
}
