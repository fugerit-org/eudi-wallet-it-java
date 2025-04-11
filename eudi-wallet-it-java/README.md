# EUDI Wallet IT Java – Proof of Concept

This project demonstrates a simplified architecture for the Italian EUDI Wallet ecosystem using SD-JWT(-VC), OpenID4VCI, and OpenID4VP protocols.

## Modules
- `issuer-service`: Issues credentials using SD-JWT
- `authentic-source-api`: Mocked data source
- `wallet-webapp`: Frontend wallet (React)
- `verifier-service`: Validates verifiable presentations
- `trust-metadata`: Static trust configuration (JWKs, metadata)
- `shared-libs`: Reusable code and helpers

## License
MIT
