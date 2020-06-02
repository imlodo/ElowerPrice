package Model;

import Model.BeanProdottoFornito.opzioniAcquisto;

public class BeanProdottoInterno extends Prodotto
{
	private double prezzo;
	private int quantity; 
	private boolean availability;
	private String descr;
	private opzioniAcquisto opzione_acquisto;
	private String cod_ean;
	
	public BeanProdottoInterno()
	{
		super();
	}

	public double getPrezzo()
	{
		return prezzo;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public boolean isAvailability()
	{
		return availability;
	}

	public String getDescr()
	{
		return descr;
	}

	public opzioniAcquisto getOpzione_acquisto()
	{
		return opzione_acquisto;
	}

	public String getCod_ean()
	{
		return cod_ean;
	}

	public void setPrezzo(double prezzo)
	{
		this.prezzo = prezzo;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public void setAvailability(boolean availability)
	{
		this.availability = availability;
	}

	public void setDescr(String descr)
	{
		this.descr = descr;
	}

	public void setOpzione_acquisto(opzioniAcquisto opzione_acquisto)
	{
		this.opzione_acquisto = opzione_acquisto;
	}

	public void setCod_ean(String cod_ean)
	{
		this.cod_ean = cod_ean;
	}

	public String toString()
	{
		return 
		super.toString()+ 
		", Prezzo = " +
		prezzo +
		", Quantita = "+
		quantity +
		", Disponibilita = "+
		availability +
		", Descrizione = " +
		descr +
		", Opzione D'Acquisto = " +
		opzione_acquisto.name() +
		", Codice Ean = " +
		cod_ean +
		"]";
		
	}
}
