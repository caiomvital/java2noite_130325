package controller;

public class Menu {

    private static final String MENU_PRINCIPAL = """
            Bem-vindo à Agenda
            1. Contatos
            2. Compromissos
            3. Encerrar
            """;
    
    private static final String MENU_CONTATOS = """
            1. Adicionar Contato
            2. Listar Contatos
            3. Localizar Contato por ID
            4. Atualizar Contato
            5. Remover Contato
            6. Voltar
            """;
    
    private static final String MENU_COMPROMISSOS = """
            1. Adicionar Compromisso
            2. Listar Compromissos
            3. Localizar Compromisso por ID
            4. Listar Compromissos por ID do Contato
            5. Atualizar Compromisso
            6. Remover Compromisso
            7. Voltar
            """;

    public static void exibirMenuPrincipal() {
        System.out.println(MENU_PRINCIPAL);
    }
    
    public static void exibirMenuContatos() {
        System.out.println(MENU_CONTATOS);
    }
    
    public static void exibirMenuCompromissos() {
        System.out.println(MENU_COMPROMISSOS);
    }
}
