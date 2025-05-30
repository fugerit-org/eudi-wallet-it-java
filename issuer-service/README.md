# issuer-service

## Requisites

- Java 21+
- Maven 3.9.9+
- Quarkus 3 CLI (optional)

## Quickstart

run the project (in port 8082)

```shell script
mvn quarkus:dev
```

test the metadata endpoint : 

```shell script
curl http://localhost:8082/.well-known/openid-credential-issuer
```

test the JWKS endpoint :

```shell script
curl http://localhost:8082/.well-known/jwks.json
```

test the credential endpoint :

```shell script
curl 'http://localhost:8082/credential' \
-H 'Connection: keep-alive' \
-H 'Content-Type: application/json' \
-H 'accept: */*' \
--data-raw $'{\n                  "format": "sd-jwt",\n                  "credential_type": "UniversityDegreeCredential",\n                  "subject_syntax_type": "did",\n                  "subject_syntax": "did:example:123"\n                }'
```

## Project creation

create command :

```shell script
quarkus create app it.wallet.demo:issuer-service \
  --no-code \
  --extension='quarkus-rest-jackson,quarkus-smallrye-openapi,quarkus-swagger-ui,quarkus-info,quarkus-jacoco'
```

## Original quarkus README

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/issuer-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Jacoco - Code Coverage ([guide](https://quarkus.io/guides/tests-with-coverage)): Jacoco test coverage support
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- Swagger UI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Swagger UI
