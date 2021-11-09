package entities;

public class Viaje {
		
				private int idCamion;
				private int idViaje;
    private String usuarioAlta;
    private String fechaAlta;
    private String usuarioModificacion;
    private String fechaModificacion;
    private String usuarioBaja;
    private String fechaBaja;
    private String claveEstado;    

		public Viaje(int idCamion, int idViaje, String usuarioAlta, String fechaAlta, String usuarioModificacion, String fechaModificacion, String usuarioBaja, String fechaBaja, String claveEstado) {
				this.idCamion = idCamion;
				this.idViaje = idViaje;
				this.usuarioAlta = usuarioAlta;
				this.fechaAlta = fechaAlta;
				this.usuarioModificacion = usuarioModificacion;
				this.fechaModificacion = fechaModificacion;
				this.usuarioBaja = usuarioBaja;
				this.fechaBaja = fechaBaja;
				this.claveEstado = claveEstado;
		}

		public Viaje() {
		}

		public int getIdCamion() {
				return idCamion;
		}

		public void setIdCamion(int idCamion) {
				this.idCamion = idCamion;
		}

		public int getIdViaje() {
				return idViaje;
		}

		public void setIdViaje(int idViaje) {
				this.idViaje = idViaje;
		}

		public String getUsuarioAlta() {
				return usuarioAlta;
		}

		public void setUsuarioAlta(String usuarioAlta) {
				this.usuarioAlta = usuarioAlta;
		}

		public String getFechaAlta() {
				return fechaAlta;
		}

		public void setFechaAlta(String fechaAlta) {
				this.fechaAlta = fechaAlta;
		}

		public String getUsuarioModificacion() {
				return usuarioModificacion;
		}

		public void setUsuarioModificacion(String usuarioModificacion) {
				this.usuarioModificacion = usuarioModificacion;
		}

		public String getFechaModificacion() {
				return fechaModificacion;
		}

		public void setFechaModificacion(String fechaModificacion) {
				this.fechaModificacion = fechaModificacion;
		}

		public String getUsuarioBaja() {
				return usuarioBaja;
		}

		public void setUsuarioBaja(String usuarioBaja) {
				this.usuarioBaja = usuarioBaja;
		}

		public String getFechaBaja() {
				return fechaBaja;
		}

		public void setFechaBaja(String fechaBaja) {
				this.fechaBaja = fechaBaja;
		}

		public String getClaveEstado() {
				return claveEstado;
		}

		public void setClaveEstado(String claveEstado) {
				this.claveEstado = claveEstado;
		}

		
				
		
}
