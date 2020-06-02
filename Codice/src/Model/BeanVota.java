package Model;

public class BeanVota
{
	private boolean utile;
	private String nome_store_recensione;
	private String utente_username_recensione;
	
	public BeanVota()
	{
		
	}

	public boolean isUtile()
	{
		return utile;
	}

	public String getNome_store_recensione()
	{
		return nome_store_recensione;
	}

	public String getUtente_username_recensione()
	{
		return utente_username_recensione;
	}

	public void setUtile(boolean utile)
	{
		this.utile = utile;
	}

	public void setNome_store_recensione(String nome_store_recensione)
	{
		this.nome_store_recensione = nome_store_recensione;
	}

	public void setUtente_username_recensione(String utente_username_recensione)
	{
		this.utente_username_recensione = utente_username_recensione;
	}
	
}
