package spring.boot.webflu.ms.cliente.app.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "TipoCliente")
public class TypeClient {

	private String idTipo;
	@NotEmpty
	private String descripcion;
	
	public TypeClient(String idTipo,String descripcion) {
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}

	public TypeClient() {
		
	}
	
}
