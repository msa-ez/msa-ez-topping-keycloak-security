#### Open Source: Keycloak is open source, which makes it highly customizable and free to use without any licensing costs.

#### Comprehensive Authentication and Authorization: It offers out-of-the-box support for standard authentication and authorization protocols, such as OpenID Connect, OAuth 2.0, and SAML 2.0.

#### Identity Brokering: Keycloak can federate user identities from external sources like Google, Facebook, and Twitter, as well as corporate user directories, such as Active Directory and LDAP.

#### User Federation: With Keycloak, organizations can link with external user databases and not have to migrate users to a new system.

#### Single Sign-On (SSO): Keycloak provides single sign-on capabilities, ensuring users only need to authenticate once to access multiple applications.

#### Adaptable Theming: The platform offers flexibility for theming, allowing organizations to adapt the login pages and emails to their corporate branding.

#### Centralized User Management: Keycloak provides a unified admin console for user and role management, making it easier for administrators to manage identities.

#### Extensibility: Thanks to its event listener and SPIs, Keycloak can be extended to accommodate unique requirements.

#### Security: Keycloak comes with features such as password policies, brute force protection, and built-in support for Two-Factor Authentication (2FA).

#### Fine-Grained Authorization: Through its centralized policy management, Keycloak enables the definition of sophisticated authorization policies.

#### Cross-Domain SSO: Keycloak can be configured for cross-domain single sign-on, allowing users to authenticate across different domains.

#### Multitenancy: Keycloak supports multitenancy which makes it adaptable for scenarios where different realms or configurations are necessary for different sets of users.

### How to run
#### 1. Enter the following commands:
```
cd keycloak
docker-compose up
```
#### 2. Access Keycloak on port 9090 and configure it.

#### 3. Modify the Keycloak settings in application.yml of the gateway.

#### 4. Run the microservice and gateway with the following commands:
```
cd <microservice>
mvn spring-boot:run

cd gateway
mvn spring-boot:run
```
