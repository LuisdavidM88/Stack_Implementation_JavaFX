package Ej1;

// clase que representa cada elemento de la pila
public class Publicacion {
    // atributos de la publicacion
    private int codigo;       // identificador unico
    private String titulo;    // titulo de la publicacion
    private String mensaje;   // mensaje de la publicacion

    // constructor que crea una publicacion con todos los datos
    public Publicacion(int codigo, String titulo, String mensaje) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    // getters y setters para acceder y modificar los atributos
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    // devuelve la publicacion en formato de texto
    @Override
    public String toString() {
        return "Publicacion { codigo:" + codigo + ", titulo:" + titulo + ", mensaje:" + mensaje + " }";
    }
}
