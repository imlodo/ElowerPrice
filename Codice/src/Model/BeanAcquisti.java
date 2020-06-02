package Model;

import Model.BeanProdottoFornito.opzioniAcquisto;

public class BeanAcquisti
{
	private String username_utente;
	private String prodotto_name;
	private double prezzo_acquisto;
	private opzioniAcquisto opzione;
	
	public BeanAcquisti()
	{
		
	}

	public String getUsername_utente()
	{
		return username_utente;
	}

	public String getProdotto_name()
	{
		return prodotto_name;
	}

	public double getPrezzo_acquisto()
	{
		return prezzo_acquisto;
	}

	public opzioniAcquisto getOpzione()
	{
		return opzione;
	}

	public void setUsername_utente(String username_utente)
	{
		this.username_utente = username_utente;
	}

	public void setProdotto_name(String prodotto_name)
	{
		this.prodotto_name = prodotto_name;
	}

	public void setPrezzo_acquisto(double prezzo_acquisto)
	{
		this.prezzo_acquisto = prezzo_acquisto;
	}

	public void setOpzione(opzioniAcquisto opzione)
	{
		this.opzione = opzione;
	}
	
	
}
