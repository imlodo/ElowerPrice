package Model;

public class BeanCategoria
{
	private String nomeCategoria;

	public BeanCategoria()
	{
		
	}
	
	public String getNomeCategoria()
	{
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria)
	{
		this.nomeCategoria = nomeCategoria;
	}
	
	public String toString()
	{
		return 
		getClass().getSimpleName() +
		"[NomeCategoria = " +
		nomeCategoria +
		"]";
	}
}
