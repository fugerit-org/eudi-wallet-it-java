#!/bin/bash

PROJECT_ROOT="eudi-wallet-it-java"
MODULES=("issuer-service" "authentic-source-api" "wallet-webapp" "verifier-service" "trust-metadata" "shared-libs")

echo "Creating EUDI Wallet PoC structure in ./${PROJECT_ROOT}"

# Create base folder
mkdir -p "${PROJECT_ROOT}"
cd "${PROJECT_ROOT}" || exit 1

# Initialize modules
for module in "${MODULES[@]}"; do
  echo "Creating module: ${module}"
  mkdir -p "${module}"

  case "$module" in
    "issuer-service"|"authentic-source-api"|"verifier-service")
      mkdir -p "${module}/src/main/java/it/eudi/${module//-//}"
      mkdir -p "${module}/src/main/resources"
      cat > "${module}/pom.xml" <<EOF
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>it.eudi</groupId>
    <artifactId>${module}</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</project>
EOF
      ;;
    "wallet-webapp")
      mkdir -p "${module}/src"
      echo "{ \"name\": \"wallet-webapp\", \"version\": \"0.0.1\" }" > "${module}/package.json"
      ;;
    "trust-metadata")
      mkdir -p "${module}/.well-known"
      cat > "${module}/.well-known/openid-configuration" <<EOF
{
  "issuer": "https://localhost:8081",
  "jwks_uri": "https://localhost:8081/.well-known/jwks.json"
}
EOF
      cat > "${module}/.well-known/jwks.json" <<EOF
{
  "keys": []
}
EOF
      ;;
    "shared-libs")
      mkdir -p "${module}/java"
      mkdir -p "${module}/js"
      ;;
  esac
done

# Create root README
cat > README.md <<EOF
# EUDI Wallet IT Java – Proof of Concept

This project demonstrates a simplified architecture for the Italian EUDI Wallet ecosystem using SD-JWT(-VC), OpenID4VCI, and OpenID4VP protocols.

## Modules
- \`issuer-service\`: Issues credentials using SD-JWT
- \`authentic-source-api\`: Mocked data source
- \`wallet-webapp\`: Frontend wallet (React)
- \`verifier-service\`: Validates verifiable presentations
- \`trust-metadata\`: Static trust configuration (JWKs, metadata)
- \`shared-libs\`: Reusable code and helpers

## License
MIT
EOF

echo "✅ Project scaffolding complete in ./${PROJECT_ROOT}"
