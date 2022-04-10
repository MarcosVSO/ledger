
# Ledger

O sistema Ledger foi desenvolvido como projeto da disciplina Residência Técnica em Sistemas de Informação promovida pelo Instituto de Informática da Universidade Federal de Goiás.
O sistema foi construindo para auxiliar a Defesa Civil no registro de ocorrências de desastres, facilitando e padronizando o registro dos danos ocorridos e agilizando o preenchimento do FIDE (Formulário de Informações de Desastres).

## Autores

- [Icaro Aguiar Perez](https://www.github.com/icaro1508)
- [Marcos Vinícius de Souza Oliveira](https://www.github.com/MarcosVSO)
- [Pedro Henrique Souza Arcanjo](https://www.github.com/pedrohsa1)
- [Vitor Abreu Bitencurt](https://github.com/vitorbiten)


## Configurações

Para executar o projeto você vai precisar instalar: 

- Jdk 11.0.13+
- Spring-boot 2.6.4+
- Maven 3.8.1+
- 
  Faça o clone do repositório e utilize os seguintes comandos:
- 
```shell
$ mvn install
```

Isso vai instalar os pacotes necessários para que o código seja executado.

O Ledger API necessita de uma instância do postgres rodando para funcionar, para tal foi fornecido
um docker-compose simples que auxilia no desenvolvimento, basta executar o seguinte comando na raiz
do repositório:

```shell
$ docker-compose -f docker/docker-compose.yml up
```

>Obs: Você também pode subir uma instância real do postgres, mas a nossa recomendação é fazer o uso
> do docker para manter um ambiente de desenvolvimento isolado

Com uma instância do postgres disponível basta executar o comando:

```shell
$ mvn spring-boot:run
```
Isso inicializará o sistema e irá popular o banco com os dados necessários para que ele funcione de maneira correta

## Documentação da API
- A API foi documentada utilizando Swagger, para visualizar os Enpoints da aplicação acesse:

```bash
http://localhost:9000/swagger-ui/index.html#/
```
