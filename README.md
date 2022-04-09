Passo a passo para execução do projeto.

1- Executar scripts no banco MySql seguindo ordem da pasta breakfast/Scripts
2- Configurar conexão ao banco no arquivo application.properties
3- Executar a aplicação

Endpoints:
1- Cadastro de contribuições (POST)
url: localhost:8080/contribuicao
{
"nome": "João",
"cpf": "123.456.789-01",
"idAlimento": 1
}

2- Lista de Alimentos (GET)
url: localhost:8080/alimento

3- Lista de Contribuições (GET)
url: localhost:8080/colaboradorAlimento
