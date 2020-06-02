package Model;

public class BeanRecensioneProdotto
{
	private String prodotto_name;
	private String utente_username;
	private String commento;
	private int num_stelle;
	
	public BeanRecensioneProdotto()
	{
		
	}

	public String getProdotto_name()
	{
		return prodotto_name;
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

	public void setProdotto_name(String prodotto_name)
	{
		this.prodotto_name = prodotto_name;
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
