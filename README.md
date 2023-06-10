## Getting Started

- [ ] Substituir concatenação de Strings pelo StringBuilder;
- [ ] Substituir informações de data por LocalDate;
- [ ] Implementar os métodos toString(), equals() e hashCode() onde for necessário;
- [ ] Criar pacote dao com classes PartidaDAO e IngressoDAO ( operações de armazenamento e controle dos dados);
- [ ] Refatorar pacote cli para pacote programa;
- [ ] Refatorar classe Cli.java para Gestor.java;
- [ ] Criar pacote util (manipulação de arquivo e leitura de dados do usuário);
- [ ] Criar um subpacote ingresso dentro do pacote entidades, para armazenar as classes de ingressos;
- [ ] Mover da classe Gestor.java códigos de leitura de dados de usuário;
- [ ] Operações de partida:
    * Criar, excluir, editar partidas;
    * Listar todas as partidas;
    * Exibir informações sobre uma partida específica;
- [ ] Operações de ingressos:
    * Realiza a venda de um ingresso;
    * Exibe o número de ingressos restantes para todas as partidas;
    * Exibe o número de ingressos restantes para uma partida;
    * Lista todos os ingressos vendiddos de uma partida;
    * Exibe os dados do último ingresso vendido;
- [ ] O programa deve salvar automaticamente os dados em arquivos de texto

Gestor só se comunicao com os DAOs (não com entidades)
Tudo que manipula informações de texto (adicionar, editar...) -> leitoradados
Stringbuilder nos toString
Transformar para String no lerNova...
Ter um Arraylist para Ingresso
todos ingressos de uma partida: arraylist de todos ingressos ou cada partida tem um arraylist de ingressos
txt: salvar ingressos em um arquivo só por partida ou um arquivo para cada partida com seus ingressos