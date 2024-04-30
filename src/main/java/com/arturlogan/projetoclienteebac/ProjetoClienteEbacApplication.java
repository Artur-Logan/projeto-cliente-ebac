package com.arturlogan.projetoclienteebac;

import com.arturlogan.projetoclienteebac.entities.Cliente;
import com.arturlogan.projetoclienteebac.entities.interfaces.IClienteDAO;
import com.arturlogan.projetoclienteebac.services.ClienteMapDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class ProjetoClienteEbacApplication {

	private static IClienteDAO iClienteDAO;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoClienteEbacApplication.class, args);

		iClienteDAO = new ClienteMapDAO();

		String opcao = JOptionPane.showInputDialog(null,
				"Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
				"Cadastro", JOptionPane.INFORMATION_MESSAGE);

		while (!isOpcaoValida(opcao)) {
			if ("".equals(opcao)) {
				sair();
			}
			opcao = JOptionPane.showInputDialog(null,
					"Opção inválida digite 1 para cadastro, 2 para consulta, 3 para cadastro, 4 para alteração ou 5 para sair",
					"Green dinner", JOptionPane.INFORMATION_MESSAGE);
		}

		while (isOpcaoValida(opcao)) {
			if (isOpcaoSair(opcao)) {
				sair();
			} else if (isCadastro(opcao)) {
				String dados = JOptionPane.showInputDialog(null,
						"Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
						"Cadastro", JOptionPane.INFORMATION_MESSAGE);
				cadastrar(dados);
			} else if(isConsultar(opcao)) {
				String dados = JOptionPane.showInputDialog(null,
						"Digite o cpf",
						"Consultar", JOptionPane.INFORMATION_MESSAGE);

				consultar(dados);
			}

			opcao = JOptionPane.showInputDialog(null,
					"Digite 1 para cadastro, 2 para consulta, 3 para excluir, 4 para alteração ou 5 para sair",
					"Green dinner", JOptionPane.INFORMATION_MESSAGE);
		}
	}



	private static void consultar(String dados) {
		//Validar se foi passado somente o cpf
		Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
		if (cliente != null) {
			JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString(), "Sucesso",JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado: ", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
		}
	}



	private static void cadastrar(String dados) {
		String[] dadosSeparados = dados.split(",");
		//Tentar validar se todos os campos estão preenchidos.
		//Se não tiver, passr null no construtor onde o valor é nulo
		//Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],null,dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6])
		Cliente cliente = new Cliente(dadosSeparados[0], Long.valueOf(dadosSeparados[1]), Long.valueOf(dadosSeparados[2]), dadosSeparados[3], Integer.valueOf(dadosSeparados[4]), dadosSeparados[5], dadosSeparados[6]);
		Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
		if (isCadastrado) {
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso ", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static void excluir(String dados) {
		if (dados.length() == 11) {
			iClienteDAO.excluir(Long.parseLong(dados));
			JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso: ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static boolean isCadastro(String opcao) {
		if ("1".equals(opcao)) {
			return true;
		}
		return false;
	}
	private static boolean isConsultar(String opcao) {
		if ("2".equals(opcao)) {
			return true;
		}
		return false;
	}

	private static boolean isOpcaoExcluir(String opcao) {
		if ("3".equals(opcao)){
			return true;
		}
		return false;
	}

	private static boolean isOpcaoAlterar(String opcao) {
		if ("4".equals(opcao)){
			return true;
		}
		return false;
	}

	private static boolean isOpcaoSair(String opcao) {
		if ("5".equals(opcao)) {
			return true;
		}
		return false;
	}

	private static void sair() {
		JOptionPane.showMessageDialog(null, "Até logo: ", "Sair",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	private static boolean isOpcaoValida(String opcao) {
		if ("1".equals(opcao) || "2".equals(opcao)
				|| "5".equals(opcao)) {
			return true;
		}
		return false;
	}

	private static boolean isOpcaoCadastro(String opcao) {
		if ("1".equals(opcao)) {
			return true;
		}
		return false;
	}
}