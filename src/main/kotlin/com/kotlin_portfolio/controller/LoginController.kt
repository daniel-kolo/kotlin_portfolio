package com.kotlin_portfolio.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ResolvableType
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.client.RestTemplate
import java.util.*
import java.util.function.Consumer
import org.springframework.util.StringUtils;
import org.springframework.http.HttpHeaders;


@Controller
class LoginController {
    var oauth2AuthenticationUrls: MutableMap<String, String> = HashMap()

    @Autowired
    private val clientRegistrationRepository: ClientRegistrationRepository? = null

    @Autowired
    private val authorizedClientService: OAuth2AuthorizedClientService? = null

    @GetMapping("/oauth_login")
    fun getLoginPage(model: Model): String {
        var clientRegistrations: Iterable<ClientRegistration>? = null
        val type = ResolvableType.forInstance(clientRegistrationRepository!!)
                .`as`(Iterable::class.java)
        if (type !== ResolvableType.NONE && ClientRegistration::class.java.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = clientRegistrationRepository as Iterable<ClientRegistration>?
        }
        clientRegistrations!!.forEach(Consumer { registration: ClientRegistration -> oauth2AuthenticationUrls[registration.clientName] = authorizationRequestBaseUri + "/" + registration.registrationId })
        model.addAttribute("urls", oauth2AuthenticationUrls)
        return "oauth_login"
    }

    @GetMapping("/loginSuccess")
    fun getLoginInfo(model: Model, authentication: OAuth2AuthenticationToken): String {
        print("here")
        val client = authorizedClientService!!.loadAuthorizedClient<OAuth2AuthorizedClient>(authentication.authorizedClientRegistrationId, authentication.name)
        val userInfoEndpointUri = client.clientRegistration
                .providerDetails
                .userInfoEndpoint
                .uri
        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            val restTemplate = RestTemplate()
            val headers = HttpHeaders()
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.accessToken
                    .tokenValue)
            val entity = HttpEntity("", headers)
            print(entity)
            //val response: ResponseEntity<Map<*, *>> = restTemplate.exchange<Map<*, *>>(userInfoEndpointUri, HttpMethod.GET, entity, MutableMap::class.java)
            //val userAttributes = response.body!!
            //model.addAttribute("name", userAttributes["name"])
        }
        return "loginSuccess"
    }

    companion object {
        private const val authorizationRequestBaseUri = "oauth2/authorize-client"
    }
}