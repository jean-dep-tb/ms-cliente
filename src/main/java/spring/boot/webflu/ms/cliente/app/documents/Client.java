package spring.boot.webflu.ms.cliente.app.documents;


import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

//clase de la collection CLIENTES y su tipo de cliente
@Getter
@Setter
@Document(collection ="Clientes")
public class Client {
	
	@Id
	@NotEmpty
	private String id;
	@NotEmpty
	private String dni; //numero de documento
	@NotEmpty
	private String nombres; //razon social
	@NotEmpty
	private String apellidos;
	@NotEmpty
	private String sexo;
	@NotEmpty
	private String telefono;
	@NotEmpty
	private String edad;
	@NotEmpty
	private String correo;
	@NotEmpty
	private TypeClient tipoCliente;
	
	public Client(String dni,String nombres,
			TypeClient tipoCliente) {
		this.dni = dni;
		this.nombres = nombres;
		this.tipoCliente = tipoCliente;
	}

	public Client() {
	
	}
	
}










