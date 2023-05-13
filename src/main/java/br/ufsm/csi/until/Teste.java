package br.ufsm.csi.until;

import br.ufsm.csi.dao.ConectaDB;
import br.ufsm.csi.dao.UsuarioDAO;
import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

public class Teste {
    public static void main(String[] args) {
        testaDeletar();
    }

    public static void testaDeletar() {
        Usuario usuario = new UsuarioDAO().getUsuario(2);
        if(usuario != null){
            String status = new UsuarioDAO().deletar(usuario);
            System.out.println(status);
        }
    }

    public static void testaAtualizar() {
        Usuario usuario = new UsuarioDAO().getUsuario(1);
        if(usuario != null){
            usuario.setEmail("teste@123");
            String status = new UsuarioDAO().atualizar(usuario);
            System.out.println(status);
        }
    }

    public static void testaGetUsuario() {
        new UsuarioDAO().getUsuario("joao@ufsm.br");
    }

    public static void testaCadastroUsuario() {
        Permissao p = new Permissao(1, "MEDICO");
        Usuario u = new Usuario("Paola", "paola@ufsm.br", "1234", true, p);
        u.setPermissao(p);
        new UsuarioDAO().cadastrar(u);
    }

    public static void testaGetUsuarios() {
        for(Usuario u : new UsuarioDAO().getUsuarios()){
            System.out.println("nome: " + u.getNome());
            System.out.println("permissao: " + u.getPermissao().getNome());
        }
    }
}
