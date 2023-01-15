import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Modelo {
    public static String user;
    public static String pass;
    public static boolean login = false;
    public static int id;
    public static String titulo;
    public static String autor;
    public static int anyoNacimiento;
    public static int anyoPublicacion;
    public static String editorial;
    public static int numeroPaginas;
    public static String thumbnail;
    public static int idActualizar;
    public static String tituloActualizar;
    public static String autorActualizar;
    public static int anyoNacimientoActualizar;
    public static int anyoPublicacionActualizar;
    public static String editorialActualizar;
    public static int numeroPaginasActualizar;
    public static String thumbnailActualizar;
    public static int Min;
    public static int Max;

    /**
     * Metodo para autentificar el usuario
     * @throws NoSuchAlgorithmException
     */
    public static void LoginBD() throws NoSuchAlgorithmException {

        JSONObject jsonObject = LeerJSON("file.json"); //paso al metodo LeerJson el nombre del fichero json

        MongoClient mongoClient = new MongoClient(jsonObject.getString("ip"), jsonObject.getInt("puerto")); //procedo a obtener el valor de los campos del json
        MongoDatabase database = mongoClient.getDatabase(jsonObject.getString("baseDatos"));
        MongoCollection < Document > coleccion = database.getCollection(jsonObject.getString("login"));

        MongoCursor < Document > cursor1 = coleccion.find().iterator();
        MongoCursor < Document > cursor2 = coleccion.find().iterator();

        Bson query1 = eq("user", user);
        Bson query2 = eq("pass", CodificarSHA_256(pass)); //Codifico la contraseña introducida

        cursor1 = coleccion.find(query1).iterator();
        cursor2 = coleccion.find(query2).iterator();

        if (cursor1.hasNext() && cursor2.hasNext()) { //Si hay registros devuelve true(significa que usuario y ontrsenya estan bien introducidos)
            System.out.println("Login con éxito");
            login = true;

        } else {
            System.out.println("Login sin éxito");
            login = false;
        }
    }

    /**
     * Metodo para codificar en SHA-256 la contrasenya introducida
     * @param str Contrasenya introducida por el usuario en la interfaz
     * @return	la contrasenya codificada
     * @throws NoSuchAlgorithmException
     */
    public static String CodificarSHA_256(String str) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8)); 

        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    /**
     * Metodo para conectarse a la bd
     * @return la coleccion de la bd
     */
    public static MongoCollection < Document > ConexionBD() {
        //He realizado la puesta a punto de MongoDB Atlas pero no consigo conectarme, me aparece un problema de "bad auth : authentication failed" tengo la ip abierta a 0.0.0.0/0 y todo
        //El string es este: "mongodb+srv://alpoor:<ikXSj5uPAXfwuNL8>@cluster0.jdiizhd.mongodb.net/?retryWrites=true&w=majority"
        //Usuario y contrasenya: "alpoor" / "ikXSj5uPAXfwuNL8" (generada automaticamente por MongoDB Atlas)
        //en el json esta puesto por defecto la conexion a localhost puesto que no me funciona MongoDB Atlas

        JSONObject jsonObject = LeerJSON("file.json"); //paso al metodo LeerJson el nombre del fichero json

        MongoClient mongoClient = new MongoClient(jsonObject.getString("ip"), jsonObject.getInt("puerto")); //procedo a obtener el valor de los campos del json
        MongoDatabase database = mongoClient.getDatabase(jsonObject.getString("baseDatos"));
        MongoCollection < Document > coleccion = database.getCollection(jsonObject.getString("coleccion"));
        System.out.println("Connection con éxito");

        return coleccion;
    }

    /**
     * Metodo para leer el fiel json mediante un reader
     * @param fileName obtiene el fichero file.json
     * @return	los campos el fichero json
     */
    public static JSONObject LeerJSON(String fileName) { 
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName)); 
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONObject(stringBuilder.toString());
    }

    /**
     * Metodo para mostrar cierta informacion especifica de la bd books
     * @return el conjunto de datos (tamaño,id y titulo) de la coleccion books
     */
    public static String MostrarResumenColeccion() {
        MongoCollection < Document > coleccion = ConexionBD();
        MongoCursor < Document > cursor = ConexionBD().find().iterator();
        String titulo = "";

        titulo = "Tamaño coleccion: " + coleccion.count() + "\n" + "\n"; //Obtener el tamaño de la coleccion

        //Obtener el titulo de cada uno de los registros

        Bson query = gte("Id", 1); //Muestro todos los id igual y mayores a 1
        cursor = coleccion.find(query).iterator();
        while (cursor.hasNext()) {
            JSONObject obj = new JSONObject(cursor.next().toJson());

            titulo = titulo + obj.getInt("Id") + " - " + obj.getString("Titulo") + "\n"; //Meto toda la informacion concatenada en la variable titulo
        }
        return titulo;
    }

    /**
     *  Merodo para crear un nuevo documento en la bd
     */
    public static void CrearDocumento() {
        MongoCollection < Document > coleccion = ConexionBD();
        ArrayList < Document > listaDocs = new ArrayList < Document > ();
        Document doc = new Document();

        doc.append("Id", id); //Añado cada elemento en un nuevo documento doc
        doc.append("Titulo", titulo);
        doc.append("Autor", autor);
        doc.append("Anyo_nacimiento", anyoNacimiento);
        doc.append("Anyo_publicacion", anyoPublicacion);
        doc.append("Editorial", editorial);
        doc.append("Numero_paginas", numeroPaginas);
        doc.append("Thumbnail", thumbnail);

        listaDocs.add(doc);
        coleccion.insertMany(listaDocs); //Los inserta como una nuevo documento a la coleccion
    }

    /**
     *  Metodo para borrar el documento del id introducido en el txt
     */
    public static void BorrarDocumento() {
        MongoCollection < Document > coleccion = ConexionBD();

        coleccion.deleteOne(eq("Id", id)); //Identifica al documento por su id y lo elimina
    }

    /**
     * Metodo para realizar consultas estableciendo filtros
     * @param num obtiene un valor int, dependiendo de este valor ejecutara un distinto tipo de codigo
     * @return el contenido
     */
    public static String Consulta(int num) {

        MongoCollection < Document > coleccion = ConexionBD();
        MongoCursor < Document > cursor1 = ConexionBD().find().iterator();
        MongoCursor < Document > cursor2 = ConexionBD().find().iterator();
        MongoCursor < Document > cursor3 = ConexionBD().find().iterator(); //Declaro los cursores
        MongoCursor < Document > cursor4 = ConexionBD().find().iterator();

        String contenido = "";

        Bson idFiltro = Filters.and(Filters.gte("Id", Min), Filters.lte("Id", Max));
        Bson anyoNFiltro = Filters.and(Filters.gte("Anyo_nacimiento", Min), Filters.lte("Anyo_nacimiento", Max));
        Bson anyoPFiltro = Filters.and(Filters.gte("Anyo_publicacion", Min), Filters.lte("Anyo_publicacion", Max)); //Establezco los filtros de busqueda
        Bson paginasFiltro = Filters.and(Filters.gte("Numero_paginas", Min), Filters.lte("Numero_paginas", Max));

        cursor1 = coleccion.find(idFiltro).iterator();
        cursor2 = coleccion.find(anyoNFiltro).iterator();
        cursor3 = coleccion.find(anyoPFiltro).iterator(); //Asocio cada filtro a su cursor
        cursor4 = coleccion.find(paginasFiltro).iterator();

        //Dependiendo del valor obtenido por el parametro "num" realizara un diferente tipo de consulta. (En caso de haber seleccionado id en la interfaz devolvera num=1)

        if (num == 1) {
            while (cursor1.hasNext()) {
                JSONObject obj = new JSONObject(cursor1.next().toJson());

                contenido = contenido + "Id: " + obj.getInt("Id") + "\n" +
                    "Titulo: " + obj.getString("Titulo") + "\n" +
                    "Autor: " + obj.getString("Autor") + "\n"
                    // +"Anyo_nacimiento:"+obj.getInt("Anyo_nacimiento")+"\n"
                    +
                    "Anyo_publicacion: " + obj.getInt("Anyo_publicacion") + "\n" +
                    "Editorial: " + obj.getString("Editorial") + "\n" +
                    "Numero_paginas: " + obj.getInt("Numero_paginas") + "\n" +
                    "Thumbnail: " + obj.getString("Thumbnail") + "\n" + "\n";
            }
        }
        if (num == 2) {
            while (cursor2.hasNext()) {
                JSONObject obj = new JSONObject(cursor2.next().toJson());

                contenido = contenido + "Id: " + obj.getInt("Id") + "\n" +
                    "Titulo: " + obj.getString("Titulo") + "\n" +
                    "Autor: " + obj.getString("Autor") + "\n"
                    // +"Anyo_nacimiento:"+obj.getInt("Anyo_nacimiento")+"\n"
                    +
                    "Anyo_publicacion: " + obj.getInt("Anyo_publicacion") + "\n" +
                    "Editorial: " + obj.getString("Editorial") + "\n" +
                    "Numero_paginas: " + obj.getInt("Numero_paginas") + "\n" +
                    "Thumbnail: " + obj.getString("Thumbnail") + "\n" + "\n";
            }
        }
        if (num == 3) {
            while (cursor3.hasNext()) {
                JSONObject obj = new JSONObject(cursor3.next().toJson());

                contenido = contenido + "Id: " + obj.getInt("Id") + "\n" +
                    "Titulo: " + obj.getString("Titulo") + "\n" +
                    "Autor: " + obj.getString("Autor") + "\n"
                    // +"Anyo_nacimiento:"+obj.getInt("Anyo_nacimiento")+"\n"
                    +
                    "Anyo_publicacion: " + obj.getInt("Anyo_publicacion") + "\n" +
                    "Editorial: " + obj.getString("Editorial") + "\n" +
                    "Numero_paginas: " + obj.getInt("Numero_paginas") + "\n" +
                    "Thumbnail: " + obj.getString("Thumbnail") + "\n" + "\n";
            }
        }
        if (num == 4) {
            while (cursor4.hasNext()) {
                JSONObject obj = new JSONObject(cursor4.next().toJson());

                contenido = contenido + "Id: " + obj.getInt("Id") + "\n" +
                    "Titulo: " + obj.getString("Titulo") + "\n" +
                    "Autor: " + obj.getString("Autor") + "\n"
                    //+"Anyo_nacimiento:"+obj.getInt("Anyo_nacimiento")+"\n"
                    +
                    "Anyo_publicacion: " + obj.getInt("Anyo_publicacion") + "\n" +
                    "Editorial: " + obj.getString("Editorial") + "\n" +
                    "Numero_paginas: " + obj.getInt("Numero_paginas") + "\n" +
                    "Thumbnail: " + obj.getString("Thumbnail") + "\n" + "\n";
            }
        }
        return contenido;
    }

    /**
     * Metodo para mostrar cualquier documento introduciendo su id
     * @param ide es el valor del id introducido en el txt
     */
    public static void MostrarDocumento(int ide) {

        MongoCollection < Document > coleccion = ConexionBD();
        MongoCursor < Document > cursor = ConexionBD().find().iterator();

        Bson query = eq("Id", ide);
        cursor = coleccion.find(query).iterator();
        while (cursor.hasNext()) { //Obtiene todos los valores que sean iguales al id introducido
            JSONObject obj = new JSONObject(cursor.next().toJson());

            id = Integer.valueOf(obj.getInt("Id")); //Se recoje el valor de cada campo y se almacena en una variable para ser mostrado mas tarde
            titulo = String.valueOf(obj.getString("Titulo"));
            autor = String.valueOf(obj.getString("Autor"));
            anyoNacimiento = Integer.valueOf(obj.getInt("Anyo_nacimiento"));
            anyoPublicacion = Integer.valueOf(obj.getInt("Anyo_publicacion"));
            editorial = String.valueOf(obj.getString("Editorial"));
            numeroPaginas = Integer.valueOf(obj.getInt("Numero_paginas"));
            thumbnail = String.valueOf(obj.getString("Thumbnail"));
        }
    }

    /**
     * Metodo para acualizar valores del documento seleccioando
     */
    public static void ActualizarDocumento() {
        MongoCollection < Document > coleccion = ConexionBD();

        coleccion.updateOne(eq("Id", id), new Document("$set", new Document("Id", idActualizar))); //Obtiene el antiguo id almacenado tras ejecutar el metodo MostrarDocumento() y establece los nuevos que son tomados en el controlador tras presionar el boton actualizar
        coleccion.updateOne(eq("Titulo", titulo), new Document("$set", new Document("Titulo", tituloActualizar)));
        coleccion.updateOne(eq("Autor", autor), new Document("$set", new Document("Autor", autorActualizar)));
        coleccion.updateOne(eq("Anyo_nacimiento", anyoNacimiento), new Document("$set", new Document("Anyo_nacimiento", anyoNacimientoActualizar)));
        coleccion.updateOne(eq("Anyo_publicacion", anyoPublicacion), new Document("$set", new Document("Anyo_publicacion", anyoPublicacionActualizar)));
        coleccion.updateOne(eq("Editorial", editorial), new Document("$set", new Document("Editorial", editorialActualizar)));
        coleccion.updateOne(eq("Numero_paginas", numeroPaginas), new Document("$set", new Document("Numero_paginas", numeroPaginasActualizar)));
        coleccion.updateOne(eq("Thumbnail", thumbnail), new Document("$set", new Document("Thumbnail", thumbnailActualizar)));

    }
}