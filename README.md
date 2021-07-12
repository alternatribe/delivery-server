# DELIVERY-SERVER

EGF Delivery Módulo Servidor

https://github.com/alternatribe/delivery-server

## Baixar o projeto

Clone o projeto no git:

    git clone https://github.com/alternatribe/delivery-server.git

ou faça o **download** do projeto clicando no botão **Clone** e depois em **Download ZIP**

## Importar no Eclipse

Em **File/Import.../Maven/Existing Maven Projects** entre em **Browse**, selecione o diretório do projeto e clique em **Finish** e espere terminar de importar.

> (necessário ter o Lombok instalado no eclipse - https://projectlombok.org/setup/eclipse)

## Executar no Eclipse

Entre em **src/main/java** e depois no pacote **net.endrigo.delivery.server**

Clique com o botão direito no arquivo **ServerApplication**, depois em **Run As/Java Application**

## Executar em linha de comando

> (necessário ter o maven instalado)

    ./mvnw spring-boot:run

> ou
>
> `mvn spring-boot:run`

Alternativamente, você pode gerar um arquivo JAR com o comando:

    ./mvnw clean package

> ou
>
> `mvn clean package`

e depois executar esse arquivo JAR:

> (sendo X.X.X a versão atual gerada no diretório target)

    java -jar target/server-X.X.X-SNAPSHOT.jar

> Caso dê algum erro ou precise atualizar as dependências, dê o comando:
>
> `mvn clean dependency:purge-local-repository dependency:resolve`
>
> e execute novamente

## Como verificar se o servidor está funcionando

Acesse `http://localhost:8080/api-docs/index.html`

Deve aparecer a página de documentação da API REST do servidor.

