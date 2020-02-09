package co.crisi.translator.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistence {
	public final static String TRANSLATOR_PATH = "resources\\Translator.dat";

	public static void serializeObject(Object object) throws IOException {
		FileOutputStream file = new FileOutputStream(TRANSLATOR_PATH);
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(object);
		out.flush();
		out.close();
	}

	public static Object deserializeObject() throws ClassNotFoundException, IOException {
		FileInputStream file = new FileInputStream(TRANSLATOR_PATH);
		ObjectInputStream in = new ObjectInputStream(file);
		Object object = in.readObject();
		in.close();
		return object;
	}
}
