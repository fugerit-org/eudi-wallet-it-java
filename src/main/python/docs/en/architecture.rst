.. include:: ../common/common_definitions.rst

.. _architecture.rst:

Demo Project Architecture
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

.. mermaid::

    graph TD
      subgraph A["eudi-wallet-it-java"]
        ISSUER["issuer-service"]
        VERIFIER["verifier-service"]
        WALLET["wallet-webapp"]
        SOURCE["authentic-source-academic-qualifications"]
        METADATA["trust-metadata"]
        DOCS["documentation"]
      end

      %% Logical interactions
      WALLET -->|requests credential| ISSUER
      ISSUER -->|queries| SOURCE
      ISSUER -->|exposes JWKS + metadata| METADATA
      VERIFIER -->|resolves issuer metadata| METADATA
      VERIFIER -->|verifies presentation| WALLET

Authentic Source
----------------

Right now only a simple authentic source is provided in module :

- `authentic-source-academic-qualifications`_  which is an implementation of `IFS03-titoli`_ OpenAPI specification based on `Quarkus`_.

Requirements : Java 21+, Maven 3.9+

.. code-block:: bash
   :caption: to start quarkus project on port 8081

   mvn quarkus:dev

A swagger UI will be available at http://localhost:8081/q/swagger-ui/.

Issuer Service
--------------

Module `issuer-service`_ is also based on `Quarkus`_ and provide a basic Issuer implementation.

Requirements : Java 21+, Maven 3.9+

.. code-block:: bash
   :caption: to start quarkus project on port 8082

   mvn quarkus:dev

A swagger UI will be available at http://localhost:8082/q/swagger-ui/.

And here the `Isser Service OpenAPI <https://github.com/fugerit-org/eudi-wallet-it-java/blob/main/issuer-service/src/main/docs/issuer-service-openapi.yaml>`_ specification are available.