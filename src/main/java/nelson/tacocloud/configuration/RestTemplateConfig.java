package nelson.tacocloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // @Bean
    // @RequestScope
    // public IngredientService ingredientService(RestTemplate restTemplate,
    // OAuth2AuthorizedClientService auth2AuthorizedClientService) {
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // String accessToken = null;

    // if
    // (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class))
    // {
    // OAuth2AuthenticationToken oAuthToken = (OAuth2AuthenticationToken)
    // authentication;
    // String clientRegistrationId = oAuthToken.getAuthorizedClientRegistrationId();
    // if (clientRegistrationId.equals("taco-admin-client")) {
    // OAuth2AuthorizedClient client =
    // auth2AuthorizedClientService.loadAuthorizedClient(clientRegistrationId,
    // oAuthToken.getName());
    // accessToken = client.getAccessToken().getTokenValue();
    // }
    // }

    // return new RestIngredientService(restTemplate, accessToken);
    // }
}
