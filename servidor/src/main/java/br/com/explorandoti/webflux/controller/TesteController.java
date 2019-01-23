package br.com.explorandoti.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TesteController {

    @GetMapping("/numero")
    public Integer numero() {
        return 10;
    }

    @GetMapping("/numero-reativo")
    public Mono<Integer> numeroReativo() {
        return Mono.just(20);
    }

    @GetMapping("/numeros")
    public Integer[] numeros() throws InterruptedException {
        Integer[] numeros = new Integer[10];
        for (int i=0; i<10; i++) {
            numeros[i] = i + 1;
            Thread.sleep(500);
        }
        return numeros;
    }

    @GetMapping(value = "/numeros-reativo", produces = { MediaType.TEXT_EVENT_STREAM_VALUE })
    public Flux<Integer> numerosReativo() {
        return Flux.create(fluxSink -> {
            for (int i=1; i<=10; i++) {
                fluxSink.next(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fluxSink.complete();
        });
    }

}
