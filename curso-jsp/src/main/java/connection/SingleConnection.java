package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Esse código garante que a conexão com o banco de dados seja estabelecida uma vez e 
 * reutilizada durante toda a execução do aplicativo, seguindo o padrão de projeto Singleton
 */

public class SingleConnection {

	/*
	 * Declara variáveis estáticas para a URL de conexão, senha, usuário e um objeto
	 * Connection. Essas variáveis são usadas para configurar e armazenar a conexão
	 * com o banco de dados PostgreSQL.
	 */
	private static String url = "jdbc:postgresql://localhost:5432/cursojsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;

	/*
	 * Um bloco estático que é executado uma vez quando a classe é carregada pela
	 * primeira vez. Chama o método conectar() para inicializar a conexão com o
	 * banco de dados.
	 */
	static {

		conectar();

	}

	/*
	 * O construtor da classe SingleConnection, que também chama o método
	 * conectar(). Isso garante que a conexão seja estabelecida quando uma instância
	 * da classe for criada.
	 */
	public SingleConnection() {

		conectar();
	}

	/*
	 * Define um método privado e estático chamado conectar() que estabelece a
	 * conexão com o banco de dados. O método faz o seguinte:
	 * 
	 * Verifica se a conexão é null. Carrega o driver JDBC do PostgreSQL
	 * (Class.forName("org.postgresql.Driver")). Cria uma conexão com o banco de
	 * dados usando DriverManager.getConnection(url, user, password). Define
	 * auto-commit como false para gerenciar transações manualmente. Exibe uma
	 * mensagem de sucesso na conexão. Em caso de exceção, imprime o stack trace da
	 * exceção.
	 */
	private static void conectar() {

		try {

			if (connection == null) {

				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectou com sucesso");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * Define um método público e estático chamado getConnection() que retorna o objeto connection. 
	 * Esse método permite que outras partes do código obtenham a conexão com o banco de dados.
	 */

	public static Connection getConnection() {

		return connection;
	}

}
