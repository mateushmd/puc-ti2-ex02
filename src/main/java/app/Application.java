package app;

import java.util.ArrayList;
import java.util.Scanner;

import dao.UserDAO;
import model.User;

public class Application 
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		UserDAO dao = new UserDAO();
		
		prompt();
		
		char choice = in.next().charAt(0);
		
		while(choice != 'q')
		{
			int id;
			String login;
			String password;
			char sex;
			User user;
			
			try
			{
				switch(choice)
				{
				case 'i':
					System.out.print("Digite o login: ");
					login = in.next();
					
					System.out.print("Digite a senha: ");
					password = in.next();
					
					System.out.print("Digite o sexo: ");
					sex = in.next().charAt(0);
					
					user = new User(login, password, sex);
					
					if(dao.insert(user))
						System.out.println("Usuário cadastrado com sucesso.");
					break;
				case 'u':
					System.out.println("Digite o id: ");
					id = in.nextInt();
					
					System.out.print("Digite o login: ");
					login = in.next();
					
					System.out.print("Digite a senha: ");
					password = in.next();
					
					System.out.print("Digite o sexo: ");
					sex = in.next().charAt(0);
					
					user = new User(id, login, password, sex);
					
					if(dao.update(user))
						System.out.println("Usuário atualizado com sucesso.");
					else
						System.out.println("Não foi encontrado um usuário com o id fornecido.");
					break;
				case 'd':
					System.out.println("Digite o id: ");
					id = in.nextInt();
					
					if(dao.delete(id))
						System.out.println("Usuário excluído com sucesso.");
					else
						System.out.println("Não foi encontrado um usuário com o id fornecido.");
					break;
				case 'l':
					ArrayList<User> users = (ArrayList<User>) dao.get();
					
					if(users.size() == 0)
						System.out.println("Não há usuários.");
					
					for(User u : users)
					{
						System.out.println(u.toString());
					}
					break;
				case 'h':
					prompt();
					break;
				default:
					System.out.println("Comando desconhecido. Digite 'h' para ver a lista de comandos.");
				}
			}
			catch(RuntimeException ex)
			{
				System.err.println(ex.getMessage());
			}
			finally
			{
				choice = in.next().charAt(0);
			}
		}
	}
	
	public static void prompt()
	{
		System.out.println("Lista de comandos:");
		System.out.println("i - inserir");
		System.out.println("u - atualizar");
		System.out.println("d - excluir");
		System.out.println("l - listar");
		System.out.println("h - ajuda");
		System.out.println("q - sair");
	}
}
