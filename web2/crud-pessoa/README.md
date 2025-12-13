# CRUD Cadastro de Pessoas (Front-End)
Implementação de exercíco prático para criação de um CRUD de dados de pessoa(s), estado(s), cidade(s) e endereço.
O objetivo dessa atividade é a implementação de conceitos de desenvolvimento web com foco principal no front-end com a utilização do framework de aplicação [Angular](https://angular.dev/) juntamente com o framework de interface [Bootstrap](https://getbootstrap.com/).

O projeto adota uma arquitetura modular baseada em funcionalidades, conforme recomendado pela documentação oficial do Angular. As funcionalidades são organizadas por domínios, promovendo separação de responsabilidades, alta coesão e baixo acoplamento, alinhando-se a princípios do Domain-Driven Design (DDD) e a boas práticas de arquiteturas modernas de aplicações SPA (Single Page Applications).

O desenvolvimento foi realizado em múltiplas etapas, seguindo principalmente a abordagem API-first. Dessa forma, o uso de recursos como Local Storage e json-server é demonstrado como estratégia para viabilizar o desenvolvimento desacoplado entre front-end e back-end, estando essas abordagens devidamente identificadas em commits específicos ao longo do histórico do projeto.

Ao final do desenvolvimento, a aplicação front-end foi integrada a uma API REST denominada [crud-spring](https://github.com/igorjuancc/das-ufpr/tree/main/web2/crud-spring/crud), disponibilizada em um projeto independente, substituindo as simulações por consumo real de dados, sem necessidade de alterações estruturais na aplicação.

## Pré-requisitos
- **Node.js:** 22.14.0
- **npm:** 10.9.2
- **Angular CLI:** 19.2.6
- **json-server:** 1.0.0-beta.3
- **API integrada:** [**crud-spring**](https://github.com/igorjuancc/das-ufpr/tree/main/web2/crud-spring/crud)

## Execução

```
# clonar o repositório (ou realizar o download)
git clone https://github.com/igorjuancc/das-ufpr/tree/main/web2/crud-pessoa

# acessar a pasta raiz do projeto
cd /das-ufpr/web2/crud-pessoa

# instalar as dependências do projeto
npm install

# executar a aplicação
ng serve
```

Após o servidor estar em execução, abra seu navegador e acesse `http://localhost:4200/`. O aplicativo será recarregado automaticamente sempre que você modificar qualquer um dos arquivos de origem.

## Etapas de Implementação
- Commit `84569ae`: Integração total da aplicação com a API
- Commit `8947c77`: Finalização de implementação de web service com json-server
- Commit `ee98346`: Finalização de implementação de sistema de login Local Storage
- Commit `5efe287`: Implementação de combobox em formulário
- Commit `84f00da`: Implementação de radio buttom em formulário
- Commit `236a54f`: Implementação de checkbox em formulário
- Commit `1ed5a31`: Implementação de menu
- Commit `876824d`: Implementação de modal
- Commit `b070126`: Organização do projeto com barrel files
- Commit `5917543`: Implementação de PIPES para texto caixa alta
- Commit `4fdf77c`: Implementação de mascaras para datas   
- Commit `5b42ec9`: Implementação de diretiva para valores númericos  
- Commit `ee74f72`: Implementação de CRUD completo utilizando Local Storage

## Sobre
O projeto CRUD Cadastro de Pessoas foi desenvolvido como prática da disciplina WEB2 - Desenvolvimento WEB II, do curso de Pós-graduação em Desenvolvimento Ágil de Software, do Setor de Educação Profissional e Tecnológica, da Universidade Federal do Paraná, sob orientação do Prof. Dr. Razer Anthom Nizer Rojas Montaño.

# Autor
<a href="https://br.linkedin.com/in/igor-juan-cordeiro-da-costa-2b4a77101">
<img src="https://avatars.githubusercontent.com/u/50890812?s=400&u=566e615dd1691c75eabd1dcb4ba749be82d1e86c&v=4" width="100px;" alt="Igor Juan" />
</a>
<br />
<a href="https://br.linkedin.com/in/igor-juan-cordeiro-da-costa-2b4a77101" target="_blank"> > Igor Juan < </a><br /><br />
Desenvolvido por Igor Juan 🤙<br />
Em caso de dúvidas, sugestões e informações, entre em contato: <br /> 
<a href="https://br.linkedin.com/in/igor-juan-cordeiro-da-costa-2b4a77101" target="_blank"> <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"> </a>
<a href="https://www.facebook.com/igorjuan.cordeirodacosta" target="_blank"> <img src="https://img.shields.io/badge/Facebook-1877F2?style=for-the-badge&logo=facebook&logoColor=white" target="_blank"> </a>
