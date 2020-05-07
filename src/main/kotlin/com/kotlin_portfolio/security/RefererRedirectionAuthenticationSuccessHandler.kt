package com.kotlin_portfolio.security

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
open class RefererRedirectionAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler {
    constructor():super(){
        setUseReferer(true)
    }
}