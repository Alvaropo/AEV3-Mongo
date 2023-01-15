import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		VentanaCRUD ventana = new VentanaCRUD();
		
		Controlador controlador = new Controlador(vista,modelo,ventana);
		
		vista.setVisible(true);
		
	}

}
