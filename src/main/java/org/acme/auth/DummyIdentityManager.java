package org.acme.auth;

import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusPrincipal;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyIdentityManager implements IdentityProvider<DummyCredentials> {

    @Override
    public Uni<SecurityIdentity> authenticate(DummyCredentials request, AuthenticationRequestContext context) {
        return Uni.createFrom().item(QuarkusSecurityIdentity.builder()
                .setPrincipal(new QuarkusPrincipal(request.getUsername()))
                .build());
    }

    @Override
    public Class<DummyCredentials> getRequestType() {
        return DummyCredentials.class;
    }
}
