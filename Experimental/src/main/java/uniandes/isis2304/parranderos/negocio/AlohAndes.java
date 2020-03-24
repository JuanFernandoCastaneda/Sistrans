package uniandes.isis2304.parranderos.negocio;

public class AlohAndes implements VOAlohAndes {

	private int tiempoMinimoVC;
	
	private int tiempoMinimoVE;
	
	private int maximoDiasVE;
	
	public AlohAndes() {
		super();
		this.tiempoMinimoVC = 0;
		this.tiempoMinimoVE = 0;
		this.maximoDiasVE = 0;
	}
	
	public AlohAndes(int tiempoMinimoVC, int tiempoMinimoVE, int maximoDiasVE) {
		super();
		this.tiempoMinimoVC = tiempoMinimoVC;
		this.tiempoMinimoVE = tiempoMinimoVE;
		this.maximoDiasVE = maximoDiasVE;
	}

	public void setTiempoMinimoVC(int tiempoMinimoVC) {
		this.tiempoMinimoVC = tiempoMinimoVC;
	}

	public void setTiempoMinimoVE(int tiempoMinimoVE) {
		this.tiempoMinimoVE = tiempoMinimoVE;
	}

	public void setMaximoDiasVE(int maximoDiasVE) {
		this.maximoDiasVE = maximoDiasVE;
	}

	public int getTiempoMinimoVC() {
		return tiempoMinimoVC;
	}

	public int getTiempoMinimoVE() {
		return tiempoMinimoVE;
	}

	public int getMaximoDiasVE() {
		return maximoDiasVE;
	}
}
