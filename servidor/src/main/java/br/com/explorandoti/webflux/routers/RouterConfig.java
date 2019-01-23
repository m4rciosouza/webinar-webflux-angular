package br.com.explorandoti.webflux.routers;

import br.com.explorandoti.webflux.handlers.PesquisaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RouterConfig {

    private final PesquisaHandler pesquisaHandler;

    public RouterConfig(PesquisaHandler pesquisaHandler) {
        this.pesquisaHandler = pesquisaHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/num"),
                serverRequest -> ServerResponse.ok().body(Mono.just(100), Integer.class))
                .andRoute(RequestPredicates.GET("/pesquisar"), pesquisaHandler::pesquisar);
    }

}
