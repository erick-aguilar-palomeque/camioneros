package entities;

public class Envio {
				
		private int idEnvio;
		private int idAlmacen;
		private int idTienda;
		private int idViaje;
		private float volumen;
		private float pesoMaximo;
		private String usuarioAlta;
		private String fechaAlta;
		private String usuarioModificacion;
		private String fechaModificacion;
		private String usuarioBaja;
		private String fechaBaja;
		private String claveEstado;  

		public Envio() {
		}

		public Envio(int idEnvio, int idAlmacen, int idTienda, int idViaje, float volumen, float pesoMaximo, String usuarioAlta, String fechaAlta, String usuarioModificacion, String fechaModificacion, String usuarioBaja, String fechaBaja, String claveEstado) {
				this.idEnvio = idEnvio;
				this.idAlmacen = idAlmacen;
				this.idTienda = idTienda;
				this.idViaje = idViaje;
				this.volumen = volumen;
				this.pesoMaximo = pesoMaximo;
				this.usuarioAlta = usuarioAlta;
				this.fechaAlta = fechaAlta;
				this.usuarioModificacion = usuarioModificacion;
				this.fechaModificacion = fechaModificacion;
				this.usuarioBaja = usuarioBaja;
				this.fechaBaja = fechaBaja;
				this.claveEstado = claveEstado;
		}

		public int getIdEnvio() {
				return idEnvio;
		}

		public void setIdEnvio(int idEnvio) {
				this.idEnvio = idEnvio;
		}

		public int getIdAlmacen() {
				return idAlmacen;
		}

		public void setIdAlmacen(int idAlmacen) {
				this.idAlmacen = idAlmacen;
		}

		public int getIdTienda() {
				return idTienda;
		}

		public void setIdTienda(int idTienda) {
				this.idTienda = idTienda;
		}

		public int getIdViaje() {
				return idViaje;
		}

		public void setIdViaje(int idViaje) {
				this.idViaje = idViaje;
		}

		public float getVolumen() {
				return volumen;
		}

		public void setVolumen(float volumen) {
				this.volumen = volumen;
		}

		public float getPesoMaximo() {
				return pesoMaximo;
		}

		public void setPesoMaximo(float pesoMaximo) {
				this.pesoMaximo = pesoMaximo;
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
