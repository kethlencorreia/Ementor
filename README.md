# eMentor-Plus

## Descrição do Projeto

Este projeto consiste no desenvolvimento de um programa em Java denominado **eMentor-Plus**. O programa tem como objetivo principal gerenciar usuários, alunos e professores, implementando conceitos de orientação a objetos, herança, polimorfismo, interfaces gráficas e integração com banco de dados MySQL.

## Estrutura do Projeto

### Classes

- **Pessoa** (Classe Mãe)
  - Atributos:
    - `Nome`
    - `Data de Nascimento`
    - `CPF`
    - `Telefone`
  - Métodos:
    - Construtor padrão
    - Construtor para inicializar todos os atributos

- **Usuário** (Subclasse de Pessoa)
  - Atributos:
    - `Nome do Usuário`
    - `Senha`
    - `Nível de Acesso` (de 1 a 3 – para futuras implementações)
  - Métodos:
    - Construtor padrão
    - `setDados`
    - `getNomeUsuario`
    - `getSenha`

- **Aluno** (Subclasse de Pessoa)
  - Atributos:
    - `Matrícula`
    - `Período`
  - Métodos:
    - Construtor padrão
    - Construtor para inicializar todos os atributos
    - `setDados`
    - `getMatricula`
    - `getPeriodo`
    - `imprimirDados` (preferencialmente utilizando mensagens gráficas de diálogo)

- **Professor** (Subclasse de Pessoa)
  - Atributos:
    - `Data de Admissão`
    - `Salário Bruto`
  - Métodos:
    - Construtor padrão
    - Construtor para inicializar todos os atributos
    - `setDados`
    - `getDataAdmissao`
    - `getSalarioBruto`
    - `imprimirDados`
    - `calcularSalarioLiquido` (considerando desconto de 14% de INSS para todas as faixas de salário e 22,5% de IRPF para salários maiores ou iguais a R$ 5.000,00; salários menores devem ter apenas desconto de INSS)

### Requisitos Gerais

1. **Modificadores de Acesso**:
   - Atributos da classe mãe `Pessoa` devem ser protegidos.
   - Atributos das subclasses devem ser privados.

2. **Conceitos Aplicados**:
   - Herança e Polimorfismo

3. **Interfaces Gráficas**:
   - Implementar interfaces gráficas para:
     - Cadastrar Alunos
     - Cadastrar Professores
     - Listar e Alterar Dados dos Alunos e Professores
   - Construir um Menu de opções utilizando botões maiores dispostos na interface gráfica

4. **Tabelas Gráficas**:
   - Utilizar `JTable` para exibir dados de alunos e professores cadastrados

5. **Banco de Dados**:
   - Utilizar MySQL
   - Implementar uma classe para gerenciar a conexão com o banco de dados, com métodos para conectar, desconectar, gravar, recuperar e alterar dados, utilizando Lista de Objetos
   - Criar uma base de dados MySQL denominada `ementorPlus` com as tabelas:
     - `Pessoa`
     - `Usuario`
     - `Aluno`
     - `Professor`
     - Cada tabela deve refletir os atributos das classes implementadas em Java

6. **Comandos SQL**:
   - Inserções devem ser aplicadas pelo comando `INSERT INTO`
   - Alterações de dados pelo comando `UPDATE`
   - Exemplo: `UPDATE ementorPlus.pessoa SET Nome='Fulano de Tal' WHERE CPF='12345678910'`

7. **Interfaces de Edição**:
   - Implementar uma interface solicitando a chave primária (e.g., CPF ou Matrícula) para recuperação e edição dos dados, trazendo os campos para um formulário para edição

8. **Liberdade de Implementação**:
   - Desenvolvedores podem organizar interfaces gráficas e métodos conforme suas preferências e conhecimentos
   - Implementar métodos adicionais conforme necessário pela equipe de trabalho

## Como Contribuir

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Requisitos Técnicos

- Java JDK 8 ou superior
- IDE de sua preferência (Eclipse, IntelliJ IDEA, etc.)
- MySQL Server
- Conector JDBC para MySQL

## Execução

1. Clone o repositório
2. Configure o banco de dados MySQL conforme descrito na seção "Banco de Dados"
3. Compile e execute o programa a partir da sua IDE

---

Este projeto é uma excelente oportunidade para praticar habilidades em programação orientada a objetos, desenvolvimento de interfaces gráficas e integração com banco de dados.
