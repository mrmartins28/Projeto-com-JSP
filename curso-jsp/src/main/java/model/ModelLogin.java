package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private String senha;
	private String email;
	private String nome;
	private boolean useradmin;
	private String perfil;
	private String sexo;
	
	private String imagemUser;
	private String extensaoImagemUser;
	
	public boolean isUseradmin() {
		return useradmin;
	}
	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}
	public String getImagemUser() {
		return imagemUser;
	}
	public void setImagemUser(String imagemUser) {
		this.imagemUser = imagemUser;
	}
	public String getExtensaoImagemUser() {
		return extensaoImagemUser;
	}
	public void setExtensaoImagemUser(String extensaoImagemUser) {
		this.extensaoImagemUser = extensaoImagemUser;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getSexo() {
		return sexo;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public boolean getUserAdmin() {
		return useradmin;
	}

	public void setUserAdmin(boolean userAdmin) {
		this.useradmin = userAdmin;
	}

	public boolean isNovo() {
		if(this.id == null) {
			return true;
		
		} else if(this.id != null && this.id > 0) {
			return false;
		}
		return id == null;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
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
		return "ModelLogin [id=" + id + ", login=" + login + ", senha=" + senha + ", email=" + email + ", nome=" + nome
				+ "]";
	}
	
	
	



}
