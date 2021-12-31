# atualizacao-spring-angular

- nao vai precisar mais utilizar os cookies, pois a seguranca do PKCE ja garante


## Dependencias adicionadas no pom

<!-- seguranca -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-oauth2-authorization-server</artifactId>
	<version>0.2.0</version>
</dependency>

## Dependencias retiradas do pom

<!-- com a nova versao é necessario adicionar a versao -->
<dependency>
	<groupId>org.springframework.security.oauth</groupId>
	<artifactId>spring-security-oauth2</artifactId>
	<version>${spring-security-oauth2.version}</version>
</dependency>

<!-- Apos nova versao do springboot é necessario deixar explicito as duas configuracoes abaixo -->
<spring-security-oauth2.version>2.2.1.RELEASE</spring-security-oauth2.version>

