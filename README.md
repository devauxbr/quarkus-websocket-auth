# Quarkus WebSocket Auth

Minimalistic bug reproducer for [#16602](https://github.com/quarkusio/quarkus/issues/16602)

This Quarkus app relies on a custom `HttpAuthenticationMechanism` that simply reads `username` from URL query parameter
and create the `SecurityIdentity` and its `Principal` from it.

Launch the app by running :
```bash
./mvnw clean compile quarkus:dev
```

Then open http://localhost:8080/?username=bob in your browser.

You can then try to get the `name` from the Java `Principal` from the WebSocket context and from the REST context by
clicking on each related button.

The WebSocket will have a **null** `Principal`, while the REST endpoint will have the correct one populated.

You can then switch to the `quarkus-1.12` git branch (`git checkout quarkus-1.12`) that relies on 
Quarkus **1.12.2-Final**, stop and relaunch the app. The WebSocket will have the `Principal` correctly populated !
