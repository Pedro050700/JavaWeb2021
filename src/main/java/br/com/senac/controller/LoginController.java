package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.dominio.Aluno;
import br.com.senac.service.AlunoService;

@Controller
public class LoginController {
	
	@Autowired
	private AlunoService alunoService;
	
	// 01 - Método para chamar a tela de login
	@GetMapping("/login")
	public ModelAndView telaLogin() {
		ModelAndView mv = new ModelAndView("/paginaLogin");
		mv.addObject("aluno", new Aluno("nome", "email"));
		return mv;
	}
	
	//02 - Método para verificar se o login existe e joga para uma tela de menu
	@PostMapping("/validar")
	public String login(Aluno aluno) {
		boolean flag = alunoService.login(aluno);
		if(flag)
			return "redirect:/menu";
		return "404.html";
	}

}
