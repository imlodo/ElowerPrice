package Model;

public class BeanIndirizzoUtente
{
	private String cap_utente;
	private String via_utente;
	private int numero_civico_utente;
	private BeanCity city;
	public BeanIndirizzoUtente()
	{
		
	}
	
	public BeanCity getCity()
	{
		return city;
	}

	public void setCity(BeanCity city)
	{
		this.city = city;
	}

	public String getCap_utente()
	{
		return cap_utente;
	}
	public String getVia_utente()
	{
		return via_utente;
	}
	public int getNumero_civico_utente()
	{
		return numero_civico_utente;
	}
	public void setCap_utente(String cap_utente)
	{
		this.cap_utente = cap_utente;
	}
	public void setVia_utente(String via_utente)
	{
		this.via_utente = via_utente;
	}
	public void setNumero_civico_utente(int numero_civico_utente)
	{
		this.numero_civico_utente = numero_civico_utente;
	}
}
