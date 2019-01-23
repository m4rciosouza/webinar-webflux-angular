package br.com.explorandoti.webflux.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PesquisaHandlerImpl implements PesquisaHandler {

    private static final Random random = new Random();

    @Override
    public Mono<ServerResponse> pesquisar(ServerRequest serverRequest) {
        Flux<String> resultados = Flux.merge(
            fornecedor("Fornecedor 1"),
            fornecedor("Fornecedor 2"),
            fornecedor("Fornecedor 3"),
            fornecedor("Fornecedor 4")
        );
        return ServerResponse.ok()
                .header("Access-Control-Allow-Origin", "*")
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(resultados, String.class);
    }

    private Flux<String> fornecedor(String nomeFornecedor) {
        List<String> resultados = new ArrayList<>();
        for (int i=0; i<5; i++) {
            double valorAleatorio = 10 + (99-10) * random.nextDouble();
            BigDecimal valor = BigDecimal.valueOf(valorAleatorio)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            resultados.add(valor + " - " + nomeFornecedor);
        }

        return Flux.interval(Duration.ofMillis(random.nextInt(3000)))
                .zipWithIterable(resultados)
                .map(Tuple2::getT2);
    }

}
