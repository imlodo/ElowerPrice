package Model;

public class BeanStore
{
	private String nome_store;
	private String p_iva;
	private int n_visite;
	private byte[] imageBytes;
	private BeanIndirizzoStore indirizzo_store;
	
	public BeanStore()
	{
		
	}

	public String getNome_store()
	{
		return nome_store;
	}

	public String getP_iva()
	{
		return p_iva;
	}

	public int getN_visite()
	{
		return n_visite;
	}

	public byte[] getImageBytes()
	{
		return imageBytes;
	}

	public BeanIndirizzoStore getIndirizzo_store()
	{
		return indirizzo_store;
	}

	public void setNome_store(String nome_store)
	{
		this.nome_store = nome_store;
	}

	public void setP_iva(String p_iva)
	{
		this.p_iva = p_iva;
	}

	public void setN_visite(int n_visite)
	{
		this.n_visite = n_visite;
	}

	public void setImageBytes(byte[] imageBytes)
	{
		this.imageBytes = imageBytes;
	}

	public void setIndirizzo_store(BeanIndirizzoStore indirizzo_store)
	{
		this.indirizzo_store = indirizzo_store;
	}
	
	
}
