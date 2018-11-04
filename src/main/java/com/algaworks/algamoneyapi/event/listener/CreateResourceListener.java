package com.algaworks.algamoneyapi.event.listener;

import com.algaworks.algamoneyapi.event.CreateResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class CreateResourceListener implements ApplicationListener<CreateResourceEvent> {

    @Override
    public void onApplicationEvent(CreateResourceEvent resourceEvent) {
        HttpServletResponse response = resourceEvent.getResponse();
        Long id = resourceEvent.getId();

        addHeaderLocation(response, id);
    }

    private void addHeaderLocation(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(id).toUri()
        ;

        response.setHeader("Location", uri.toASCIIString());
    }
}
