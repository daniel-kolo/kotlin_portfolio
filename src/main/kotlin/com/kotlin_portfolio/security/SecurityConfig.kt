package com.kotlin_portfolio.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import java.util.*
import java.util.function.Predicate
import java.util.stream.Collectors


@Configuration
//@PropertySource("application-oauth2.properties")
open class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/oauth_login", "/loginFailure", "/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .loginPage("/oauth_login")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize-client")
                .authorizationRequestRepository(authorizationRequestRepository())
                .and()
                .tokenEndpoint()
                .accessTokenResponseClient(accessTokenResponseClient())
                .and()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
    }


    @Bean
    open fun authorizationRequestRepository(): AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
        return HttpSessionOAuth2AuthorizationRequestRepository()
    }

    @Bean
    open fun accessTokenResponseClient(): OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
        return DefaultAuthorizationCodeTokenResponseClient()
    }

    /*
    //@Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        val registrations: List<ClientRegistration> = clients.stream()
                .map { c: String -> getRegistration(c) }
                .filter(Predicate { registration: ClientRegistration? -> registration != null })
                .collect(Collectors.toList())
        return InMemoryClientRegistrationRepository(registrations)
    }*/

    @Autowired
    private val env: Environment? = null
    private fun getRegistration(client: String): ClientRegistration? {
        val clientId = env!!.getProperty("$CLIENT_PROPERTY_KEY$client.client-id") ?: return null
        val clientSecret = env.getProperty("$CLIENT_PROPERTY_KEY$client.client-secret")
        if (client == "google") {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build()
        }
        return if (client == "facebook") {
            CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build()
        } else null
    }

    companion object {
        // additional configuration for non-Spring Boot projects
        private val clients: List<String> = Arrays.asList("google", "facebook")
        private const val CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration."
    }
}