package org.acme.auth;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.vertx.http.runtime.security.ChallengeData;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.quarkus.vertx.http.runtime.security.HttpCredentialTransport;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Dummy authentication mechanism that retrieves username from HTTP query param "username"
 *
 * This is obviously NOT secured, and is provided only for easy testing purpose
 */
@ApplicationScoped
public class DummyAuthenticationMechanism implements HttpAuthenticationMechanism {
    private static final String USERNAME = "username";

    @Override
    public Uni<SecurityIdentity> authenticate(RoutingContext context, IdentityProviderManager identityProviderManager) {
        List<String> usernames = context.queryParam(USERNAME);
        if (usernames.isEmpty()) {
            return Uni.createFrom().failure(new AuthenticationFailedException());
        }

        return identityProviderManager.authenticate(new DummyCredentials(usernames.get(0)));
    }

    @Override
    public Uni<ChallengeData> getChallenge(RoutingContext context) {
        ChallengeData challengeData = new ChallengeData(HttpResponseStatus.FORBIDDEN.code(), null, null);
        return Uni.createFrom().item(challengeData);
    }

    @Override
    public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
        return newHashSet(DummyCredentials.class);
    }

    @Override
    public HttpCredentialTransport getCredentialTransport() {
        return new HttpCredentialTransport(HttpCredentialTransport.Type.OTHER_HEADER, "DUMMY");
    }
}
