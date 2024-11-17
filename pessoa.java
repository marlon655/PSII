package application;

public class Pessoa {

		private int id;
		private String nome;
		private String email;
		private String telefone;
		
		public Pessoa(int id, String nome, String email, String telefone) {
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.telefone = telefone;
		}
		//Getters
		public int getId() {
			return this.id;
		}
		public String getNome() {
			return this.nome;
		}
		public String getEmail() {
			return this.email;
		}
		public String getTelefone() {
			return this.telefone;
		}
		//Setters
		public void setNome(String nome) {
			this.nome = nome;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		
}
