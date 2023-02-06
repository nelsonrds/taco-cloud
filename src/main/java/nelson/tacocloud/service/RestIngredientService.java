package nelson.tacocloud.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import nelson.tacocloud.model.Ingredient;

@Slf4j
public class RestIngredientService implements IngredientService {

    private RestTemplate restTemplate;

    public RestIngredientService(RestTemplate restTemplate, String accessToken) {
        this.restTemplate = restTemplate;
        if (accessToken != null) {
            this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
        }
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/api/ingredients", Ingredient[].class));
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return restTemplate.postForObject("http://localhost:8080/api/ingredients", ingredient, Ingredient.class);
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        log.info("client token interceptor");
        return new ClientHttpRequestInterceptor() {

            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                log.info("adding header: " + accessToken);
                request.getHeaders().add("Authorization", "Bearer " + accessToken);
                return execution.execute(request, body);
            }
        };

    }

}
