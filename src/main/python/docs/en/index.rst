.. include:: ../common/common_definitions.rst

=====================================
Italian EUDI Java Demo implementation
=====================================

Introduction
------------

This project is a demo for the `The Italian EUDI Wallet Implementation Profile <https://italia.github.io/eid-wallet-it-docs/versione-corrente/en/>`_. specification, mainly based on java language.

Here is a diagram showing IT Wallet architecture :

.. mermaid::

    graph TD
        AS["Authentic Sources<br/>(Civil Registry, Tax Agency)"]
        PDND["PDND / API Gateway"]
        ISS["Credential Issuer<br/>(Issues SD-JWT / mDL)"]
        WAL["Wallet (Holder App)<br/>Stores Credentials<br/>Selective Disclosure"]
        VER["Verifier<br/>Requests and Validates VCs"]
        TRUST["Trust Infrastructure<br/>Public Keys & Metadata"]

        %% Authentic Source Flow
        AS -->|Data Access| PDND
        PDND -->|Verified Attributes| ISS

        %% Issuance Flow
        ISS -->|OpenID4VCI<br/>VC Issuance| WAL

        %% Presentation Flow
        WAL -->|OpenID4VP<br/>VC Presentation| VER

        %% Trust Lookup Flow
        VER -->|Resolve Metadata<br/>& Public Keys| TRUST
        ISS -->|Publishes Metadata<br/>& Keys| TRUST

Index of content
----------------

.. toctree:: 
   :maxdepth: 3
   
   architecture.rst

