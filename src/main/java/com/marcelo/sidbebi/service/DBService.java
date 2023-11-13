package com.marcelo.sidbebi.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.Cliente;
import com.marcelo.sidbebi.domain.Fornecedor;
import com.marcelo.sidbebi.domain.ItensProduto;
import com.marcelo.sidbebi.domain.Produto;
import com.marcelo.sidbebi.domain.Usuario;
import com.marcelo.sidbebi.domain.enums.NivelEstoque;
import com.marcelo.sidbebi.domain.enums.Perfil;
import com.marcelo.sidbebi.domain.enums.Tipo;
import com.marcelo.sidbebi.repositories.ClienteRepository;
import com.marcelo.sidbebi.repositories.FornecedorRepository;
import com.marcelo.sidbebi.repositories.ItensProdutoRepository;
import com.marcelo.sidbebi.repositories.ProdutoRepository;
import com.marcelo.sidbebi.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItensProdutoRepository itensProdutoRepository;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	protected Set<Integer> perfil = new HashSet<>();
	
	//istancia e persiste os objetos no Banco de Dados
	public void instanciaDB() { 
		perfil.add(0);
		Usuario usuario = new Usuario(null, "Administrador", "sigevva@email.com", encoder.encode("varejo"));
		usuario.addPerfil(Perfil.ADMIN);
		usuarioRepository.save(usuario);
		
		Cliente cli1 = new Cliente(null, "Marcelo Machado", "98765432168", "65992822316", "Rua Fernando de Noronha", "marcelo@mail.com");
		Cliente cli2 = new Cliente(null, "Albert Eisntein", "12345678910", "65998655632", "Rua Alemanha", "einstein@mail.com");
		Cliente cli3 = new Cliente(null, "Carl Sagan", "10987654321", "65992345432", "Rua Cosmos", "sagan@mail.com");
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));	
		
		Fornecedor f1 = new Fornecedor(null, "Ambev", "76867587000174", "Rua das Acácias", "65936136743", "ambev@mail.com", null);
		Fornecedor f2 = new Fornecedor(null, "Coca Cola", "75656431000188", "Av. Mario Andreaza", "65936826545", "coke@mail.com", null);
		Fornecedor f3 = new Fornecedor(null, "ElmaChips", "98987653000199", "Rua Trinta e dois", "65967857898", "elmachips@mail.com", null);
		fornecedorRepository.saveAll(Arrays.asList(f1, f2, f3));
				
		Produto prod1 = new Produto(null, Tipo.ALCOOLICA, "Cerveja", "300 ml", 1, 3.00, 3.00, NivelEstoque.NORMAL, null);
		Produto prod2 = new Produto(null, Tipo.NAOALCOOLICA, "Coca Cola", "1 L", 1, 4.50, 4.50, NivelEstoque.ALTO, null);
		Produto prod3 = new Produto(null, Tipo.COMIDA, "Salgadinho", "300 g", 1, 3.50, 3.50, NivelEstoque.BAIXO, null);
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		ItensProduto itensProd1 = new ItensProduto(null, prod1, f1, "123456789");
		ItensProduto itensProd2 = new ItensProduto(null, prod2, f2, "987654321");
		ItensProduto itensProd3 = new ItensProduto(null, prod3, f3, "234567891");
		itensProdutoRepository.saveAll(Arrays.asList(itensProd1, itensProd2, itensProd3));
			
	}
	
}
