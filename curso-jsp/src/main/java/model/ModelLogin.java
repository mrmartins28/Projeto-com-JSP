package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	private String email;
	private String nome;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "ModelLogin [login=" + login + ", senha=" + senha + ", email=" + email + ", nome=" + nome + "]";
	}
	
	
	



}
