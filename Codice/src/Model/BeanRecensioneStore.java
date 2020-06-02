package Model;

public class BeanRecensioneStore
{
	private String nome_store_r;
	private String utente_username;
	private String commento;
	private int num_stelle;
	
	public BeanRecensioneStore()
	{
		
	}

	public String getNome_store_r()
	{
		return nome_store_r;
	}

	public String getUtente_username()
	{
		return utente_username;
	}

	public String getCommento()
	{
		return commento;
	}

	public int getNum_stelle()
	{
		return num_stelle;
	}

	public void setNome_store_r(String nome_store_r)
	{
		this.nome_store_r = nome_store_r;
	}

	public void setUtente_username(String utente_username)
	{
		this.utente_username = utente_username;
	}

	public void setCommento(String commento)
	{
		this.commento = commento;
	}

	public void setNum_stelle(int num_stelle)
	{
		this.num_stelle = num_stelle;
	}
	
}
