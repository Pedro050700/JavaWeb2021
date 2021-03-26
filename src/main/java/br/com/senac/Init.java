package br.com.senac;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.senac.dominio.Aluno;
import br.com.senac.dominio.Role;
import br.com.senac.dominio.Usuario;
import br.com.senac.repositorio.AlunoRepositorio;
import br.com.senac.repositorio.RoleRepository;
import br.com.senac.repositorio.UsuarioRepository;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoRepositorio alunoRepo;
	@Autowired
	UsuarioRepository usuRepo;
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		Aluno aluno = new Aluno("pedro.henrique@hotmail.com", "Pedro");
				
			Role roleAdmin = new Role();
			roleAdmin.setNomeRole("ROLE_ADMIN");
			roleRepo.save(roleAdmin);
		
			Role roleUser = new Role();
			roleUser.setNomeRole("ROLE_USER");
			roleRepo.save(roleUser);
		
		
		alunoRepo.save(aluno);
		
		Usuario usu = new Usuario();
		usu.setLogin("Pedro");
		usu.setSenha(new BCryptPasswordEncoder().encode("123456"));
		usu.setRoles(Arrays.asList(roleAdmin));
		usuRepo.save(usu);
		
		Usuario usu1 = new Usuario();
		usu.setLogin("Lucas");
		usu.setSenha(new BCryptPasswordEncoder().encode("123456"));
		usu.setRoles(Arrays.asList(roleAdmin));
		usuRepo.save(usu1);
	}

}
