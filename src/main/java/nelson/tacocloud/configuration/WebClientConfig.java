// package nelson.tacocloud.configuration;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
// import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
// import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
// import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

// @Configuration
// public class WebClientConfig {

//     @Bean
//     public OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(
//             ClientRegistrationRepository clientRegistrationRepository,
//             OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository) {

//         OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
//                 .authorizationCode()
//                 .refreshToken()
//                 .build();

//         DefaultOAuth2AuthorizedClientManager auth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
//                 clientRegistrationRepository, auth2AuthorizedClientRepository);
//         auth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
//         return auth2AuthorizedClientManager;
//     }
// }
