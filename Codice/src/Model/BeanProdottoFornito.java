package Model;

import java.util.Comparator;

public class BeanProdottoFornito extends Prodotto implements Comparable<BeanProdottoFornito>
{
	public enum opzioniAcquisto{RITIRO_IN_SEDE,CONSEGNA_DOMICILIO};
	private boolean disponibile;
	private String descrizione;
	private String nameStore;
	private opzioniAcquisto opzioneDAcquisto;
	private double prezzoAttuale;
	private double prezzoIeri;
	private double prezzoInizioGiorno;
	private double prezzoScorsoMese;
	private int quantity;
	private String cod_ean;
	private double costi_spedizione;
	private String link_prodotto_store;
	
	public BeanProdottoFornito()
	{
		super();
	}
	
	
	public String getCod_ean()
	{
		return cod_ean;
	}


	public void setCod_ean(String cod_ean)
	{
		this.cod_ean = cod_ean;
	}


	public boolean isDisponibile()
	{
		return disponibile;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public String getNameStore()
	{
		return nameStore;
	}

	public opzioniAcquisto getOpzioneDAcquisto()
	{
		return opzioneDAcquisto;
	}

	public double getPrezzoAttuale()
	{
		return prezzoAttuale;
	}

	public double getPrezzoIeri()
	{
		return prezzoIeri;
	}

	public double getPrezzoInizioGiorno()
	{
		return prezzoInizioGiorno;
	}

	public double getPrezzoScorsoMese()
	{
		return prezzoScorsoMese;
	}

	public int getQuantity()
	{
		return quantity;
	}

	
	public double getCosti_spedizione()
	{
		return costi_spedizione;
	}

	public String getLink_prodotto_store()
	{
		return link_prodotto_store;
	}

	public void setDisponibile(boolean disponibile)
	{
		this.disponibile = disponibile;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public void setNameStore(String nameStrore)
	{
		this.nameStore = nameStrore;
	}

	public void setOpzioneDAcquisto(opzioniAcquisto opzioneDAcquisto)
	{
		this.opzioneDAcquisto = opzioneDAcquisto;
	}

	public void setPrezzoAttuale(double prezzoAttuale)
	{
		this.prezzoAttuale = prezzoAttuale;
	}

	public void setPrezzoIeri(double prezzoIeri)
	{
		this.prezzoIeri = prezzoIeri;
	}

	public void setPrezzoInizioGiorno(double prezzoInizioGiorno)
	{
		this.prezzoInizioGiorno = prezzoInizioGiorno;
	}

	public void setPrezzoScorsoMese(double prezzoScorsoMese)
	{
		this.prezzoScorsoMese = prezzoScorsoMese;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public void setCosti_spedizione(double costi_spedizione)
	{
		this.costi_spedizione = costi_spedizione;
	}

	public void setLink_prodotto_store(String link_prodotto_store)
	{
		this.link_prodotto_store = link_prodotto_store;
	}

	@Override
	public int compareTo(BeanProdottoFornito arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	 public static class Comparators 
	 {

	        public static Comparator <BeanProdottoFornito> prezzoAttuale = new Comparator<BeanProdottoFornito>()
	        {
	           	@Override
				public int compare(BeanProdottoFornito b1 , BeanProdottoFornito b2) 
	           	{
	           		float prezzoBean1 = (float) b1.getPrezzoAttuale();
	           		float prezzoBean2 = (float) b2.getPrezzoAttuale();
	           		if(prezzoBean1 <= prezzoBean2)
	           			return -1; // il bean1 è più piccolo del bean2 
	           		else
	           			return 1;
				}
	           	
	        };
	        
	        
	        public static Comparator <BeanProdottoFornito> piuCercati = new Comparator<BeanProdottoFornito>()
	        {
	           	@Override
				public int compare(BeanProdottoFornito b1 , BeanProdottoFornito b2) 
	           	{
	           		int ricercheBean1 = (int) b1.getCount_ricerche();
	           		int ricercheBean2 = (int) b2.getCount_ricerche();
	           		if(ricercheBean1 <= ricercheBean2)
	           			return 1; // il bean1 è più piccolo del bean2 
	           		else
	           			return -1;
				}
	           	
	        };
	
	 }
	
	
	
	
	
	
}
