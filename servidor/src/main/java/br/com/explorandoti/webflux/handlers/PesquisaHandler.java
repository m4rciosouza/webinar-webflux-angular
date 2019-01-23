package br.com.explorandoti.webflux.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface PesquisaHandler {

    Mono<ServerResponse> pesquisar(ServerRequest serverRequest);

}
