package Model;

public class BeanSconto
{
	private String utente_username;
	private String nome_sconto;
	private String descr_sconto;
	private int perc_sconto;
	public BeanSconto()
	{
		
	}
	public String getUtente_username()
	{
		return utente_username;
	}
	public String getNome_sconto()
	{
		return nome_sconto;
	}
	public String getDescr_sconto()
	{
		return descr_sconto;
	}
	public int getPerc_sconto()
	{
		return perc_sconto;
	}
	public void setUtente_username(String utente_username)
	{
		this.utente_username = utente_username;
	}
	public void setNome_sconto(String nome_sconto)
	{
		this.nome_sconto = nome_sconto;
	}
	public void setDescr_sconto(String descr_sconto)
	{
		this.descr_sconto = descr_sconto;
	}
	public void setPerc_sconto(int perc_sconto)
	{
		this.perc_sconto = perc_sconto;
	}
}
