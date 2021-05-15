package org.acme.auth;

import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.security.identity.request.BaseAuthenticationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DummyCredentials
        extends BaseAuthenticationRequest
        implements AuthenticationRequest
{
    private final String username;
}
