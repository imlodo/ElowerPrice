package Model;

public class BeanUtente
{
	private String username;
	private String email;
	private String cf;
	private String name;
	private String surname;
	private String password;
	private int type;
	
	public BeanUtente()
	{
		
	}
	public BeanUtente(String username, String email, String cf, String name, String surname, String password, int type)
	{
		this.username = username;
		this.email = email;
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.type = type;
	}
	
	//Getters
	public String getUsername()
	{
		return username;
	}
	public String getEmail()
	{
		return email;
	}
	public String getCf()
	{
		return cf;
	}
	public String getName()
	{
		return name;
	}
	public String getSurname()
	{
		return surname;
	}
	public String getPassword()
	{
		return password;
	}
	public int getType()
	{
		return type;
	}
	
	//Setters
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setCf(String cf)
	{
		this.cf = cf;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	
	
}
