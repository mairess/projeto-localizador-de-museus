# <p align="center">Projeto Localizador de Museus</p>

## Contexto

Esse projeto utiliza o Spring para criar uma API que trabalha sobre dados de localização de museus
brasileiros. Com a possibilidade de, a partir de uma determinada localização, encontrar o museu mais
próximo.Os dados foram
retirados [desta](http://dados.cultura.gov.br/dataset/series-historicas-cadastro-nacional-de-museus)
série histórica.

<strong>Rode o projeto localmente</strong><br>

> ⚠️ É preciso ter [Java](https://www.oracle.com/java/) instalado em sua máquina.

1. Clone o repositório:

```BASH
git clone git@github.com:mairess/projeto-localizador-de-museus.git
```

2. Rode a API:

```BASH
mvn spring-boot:run
```

3. Veja as rotas disponíveis em:

```BASH
http://localhost:8080/swagger-ui/index.html
```

3. Rode os testes:

```BASH
mvn test 
```