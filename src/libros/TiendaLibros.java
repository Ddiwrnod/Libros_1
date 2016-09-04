/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto
 * Ejercicio: Carro de Compras Libros
 * Autor inicial: Nelson Díaz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package mundo;

import java.util.*;

/**
 * Tienda de venta de libros
 */
public class TiendaLibros
{
        // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /** Lista de los �tem de compra que se van a�adiendo al carro */
    private ArrayList itemsCompra;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un carro de compra vac�o (sin libros solicitados).
     */
    public CarroCompras( )
    {
        itemsCompra = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna si existe un �tem de compra donde est� el libro con el ISBN dado.
     * @param isbnBuscado ISBN del libro buscado.
     * @return �tem de compra del libro o null si no lo encuentra.
     */
    public ItemCompra buscarItemCompraLibro( String isbnBuscado )
    {
        boolean encontrado = false;
        ItemCompra item = null;

        // �ndice para el recorrido del arreglo
        int i = 0;
        int totalItems = itemsCompra.size( );
        // Mientras no encuentre el libro en un �tem
        while( i < totalItems && !encontrado )
        {
            item = ( ItemCompra )itemsCompra.get( i );
            if( item.darLibro( ).igualALibro( isbnBuscado ) )
                encontrado = true;
            i++;
        }
        if( encontrado )
            return item;
        else
            return null;
    }

    /**
     * Adiciona un nuevo �tem al carro de la compras si el libro ya no estaba en �l, o adiciona la cantidad si el libro ya est� incluido en otra compra. <br>
     * <b>post: </b> Si el libro no est� en el carro de compras, se adiciona el libro y la cantidad de ejemplares o si ya existe se incrementa la cantidad de ejemplares a
     * comprar. <br>
     * @param libro Libro a comprar. libro != null.
     * @param cantidad Cantidad de libros a comprar. cantidad >= 0.
     */
    public void adicionarCompra( Libro libro, int cantidad )
    {
        // Busca un �tem de compra donde exista el libro
        ItemCompra unItem = buscarItemCompraLibro( libro.darISBN( ) );
        // Si no existe ese libro en el carrito, crea el �tem y lo agrega
        if( unItem == null )
        {
            ItemCompra nuevoItem = new ItemCompra( libro, cantidad );
            itemsCompra.add( nuevoItem );
        }
        else
        {
            // Si ya existe, s�lo aumenta la cantidad del pedido
            unItem.cambiarCantidadSolicitada( unItem.darCantidadSolicitada( ) + cantidad );
        }
    }

    /**
     * Borra un �tem del carro de la compra. <br>
     * <b>post: </b> Se elimina el �tem de compra dado. <br>
     * @param unItem �tem a buscar y eliminar. unItem != null.
     */
    public void borrarItemCompra( ItemCompra unItem )
    {
        ItemCompra item = null;
        boolean encontrado = false;

        // El �ndice para recorrer los elementos
        int i = 0;
        int indice = itemsCompra.size( );

        // Avanza mientras no encuentre el libro en un �tem
        while( i < indice && !encontrado )
        {
            item = ( ItemCompra )itemsCompra.get( i );
            if( item.igualAItem( unItem ) )
                encontrado = true;
            i++;
        }
        if( encontrado )
            itemsCompra.remove( item );
    }

    /**
     * Retorna la lista de los �tem de compra.
     * @return Lista de los �tem de compra.
     */
    public ArrayList darListaCompra( )
    {
        return itemsCompra;
    }

    /**
     * Calcula el total de la compra que lleva el carrito.
     * @return Total de la compra.
     */
    public int calcularValorTotalCompra( )
    {
        int total = 0;
        int indice = 0;
        int totalItems = itemsCompra.size( );
        while( indice < totalItems )
        {
            ItemCompra itemActual = ( ItemCompra )itemsCompra.get( indice );
            // Va acumulando los subtotales de los �tem de compra en el total
            total += itemActual.calcularSubtotalItem( );
            indice++;
        }
        return total;
    }
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Libro que es el �tem de la compra
     */
    private Libro libro;

    /**
     * Cantidad del libro a comprar
     */
    private int cantidad;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un �tem de compra con el libro a comprar y la cantidad deseada.
     * @param unLibro Libro que se va a comprar. unLibro != null.
     * @param unaCantidad Cantidad de libros a comprar. unaCantidad != null.
     */
    public ItemCompra( Libro unLibro, int unaCantidad )
    {
        libro = unLibro;
        cantidad = unaCantidad;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el libro solicitado como �tem de compra.
     * @return libro Libro a comprar. libro != null.
     */
    public Libro darLibro( )
    {
        return libro;
    }

    /**
     * Retorna el ISBN del libro que es el �tem de compra.
     * @return ISBN del libro del �tem de compra.
     */
    public String darIsbnItem( )
    {
        return libro.darISBN( );
    }

    /**
     * Retorna la cantidad solicitada de libros.
     * @return cantidad solicitada.
     */
    public int darCantidadSolicitada( )
    {
        return cantidad;
    }

    /**
     * Cambia la cantidad de libros solicitados en la compra. <br>
     * <b>post: </b> Se actualiza el �tem de compra con la nueva cantidad. <br>
     * @param nuevaCantidad Nueva cantidad de libros a comprar. nuevaCantidad > 0.
     */
    public void cambiarCantidadSolicitada( int nuevaCantidad )
    {
        cantidad = nuevaCantidad;
    }

    /**
     * Indica si este �tem de compra es igual a otro.
     * @param otroItem El otro �tem con el �ste que se va a comparar. otroItem != null.
     * @return true si son el mismo �tem de compra, false en caso contrario.
     */
    public boolean igualAItem( ItemCompra otroItem )
    {
        boolean iguales = libro.igualALibro( otroItem.darIsbnItem( ) );
        return iguales;
    }

    /**
     * Retorna el subtotal o valor de compra del �tem. Depende del precio y la cantidad de libros.
     * @return subtotal Cantidad calculada del valor del �tem de compra. subtotal > 0.
     */
    public int calcularSubtotalItem( )
    {
        int subtotal = libro.darPrecio( ) * cantidad;
        return subtotal;
    }
    
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /** T�tulo del libro */
    private String titulo;

    /** ISBN del libro */
    private String isbn;

    /** Precio del libro */
    private int precio;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Crea el libro con su informaci�n b�sica: t�tulo, ISBN y precio.
     * @param unTitulo T�tulo del libro. unTitulo!= null.
     * @param unISBN ISBN del libro. unISBN != null.
     * @param unPrecio Precio del libro. unPrecio >= 0.
     */
    public Libro( String unTitulo, String unISBN, int unPrecio )
    {
        titulo = unTitulo;
        isbn = unISBN;
        precio = unPrecio;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el t�tulo del libro.
     * @return T�tulo del libro.
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Retorna el c�digo ISBN del libro.
     * @return ISBN del libro.
     */
    public String darISBN( )
    {
        return isbn;
    }

    /**
     * Retorna el precio del libro.
     * @return Precio del libro.
     */
    public int darPrecio( )
    {
        return precio;
    }

    /**
     * Indica si este libro es igual a otro.
     * @param otroIsbn ISBN del otro libro con el que se compara este. otroISBN != null.
     * @return true si son dos libros iguales, false en caso contrario.
     */
    public boolean igualALibro( String otroIsbn )
    {
        boolean iguales = isbn.equals( otroIsbn );
        return iguales;
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cat�logo o lista de libros
     */
    private ArrayList catalogo;

    /**
     * Carro de compras de los libros
     */
    private CarroCompras carrito;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Crea la tienda de libros con el cat�logo de libros vac�o
     */
    public TiendaLibros( )
    {
        catalogo = new ArrayList( );
        carrito = new CarroCompras( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Adiciona un nuevo libro al cat�logo de la tienda. <br>
     * <b>post: </b> Se agreg� un libro al cat�logo.
     * @param nuevoLibro Nuevo libro del cat�logo. nuevoLibro != null.
     */
    public void adicionarLibroCatalogo( Libro nuevoLibro )
    {
        // Verifica que el libro no se haya ingresado antes
        Libro libro = buscarLibro( nuevoLibro.darISBN( ) );
        // S�lo a�ade el libro si no existe ya
        if( libro == null )
            catalogo.add( nuevoLibro );
    }

    /**
     * Crea una nueva compra. <br>
     * <b>post: </b> Se cre� un nuevo carrito de compras sin �tem de compra.
     */
    public void crearNuevaCompra( )
    {
        carrito = new CarroCompras( );
    }

    /**
     * Retorna el cat�logo de la tienda. <br>
     * @return El cat�logo de la tienda.
     */
    public ArrayList darCatalogo( )
    {
        return catalogo;
    }

    /**
     * Retorna el carro de compras.
     * @return El carro de compras.
     */
    public CarroCompras darCarritoCompras( )
    {
        return carrito;
    }

    /**
     * Retorna si existe un libro del cat�logo de la tienda con el ISBN dado.
     * @param isbn ISBN del libro que se quiere buscar en el cat�logo.
     * @return libro Libro encontrado en el catalogo o null si no existe.
     */
    public Libro buscarLibro( String isbn )
    {
        int indice = 0;
        int totalLibros = catalogo.size( );
        Libro libroEncontrado = null;
        boolean encontrado = false;
        while( indice < totalLibros && !encontrado )
        {
            libroEncontrado = ( Libro )catalogo.get( indice );
            if( libroEncontrado.igualALibro( isbn ) )
                encontrado = true;
            indice++;
        }
        if( encontrado )
            return libroEncontrado;
        else
            return null;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo 1 de extensi�n al ejemplo
     * @return respuesta
     */

    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo 2 de extensi�n al ejemplo
     * @return respuesta
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}