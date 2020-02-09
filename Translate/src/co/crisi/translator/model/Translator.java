package co.crisi.translator.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import co.crisi.translator.exceptions.NullWordException;
import co.crisi.translator.exceptions.RepeatedWordException;

public class Translator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> spanish_english;
	private HashMap<String, String> spanish_french;
	private HashMap<String, String> spanish_italian;

	public Translator() {
		spanish_english = new HashMap<String, String>();
		spanish_french = new HashMap<String, String>();
		spanish_italian = new HashMap<String, String>();
	}

	public HashMap<String, String> getSpanish_english() {
		return spanish_english;
	}

	public HashMap<String, String> getSpanish_french() {
		return spanish_french;
	}

	public HashMap<String, String> getSpanish_italian() {
		return spanish_italian;
	}

	private boolean isSpanishEnglishWord(String spanishWord) {
		return spanish_english.containsKey(spanishWord);
	}

	public void addSpanishEnglishWord(String spanishWord, String englishTranslate) throws RepeatedWordException {
		if (isSpanishEnglishWord(spanishWord)) {
			throw new RepeatedWordException(
					"La palabra: " + spanishWord + " ya se encuentra en el diccionario español-ingles.");
		}
		spanish_english.put(spanishWord, englishTranslate);
	}

	public String getTranslate(String word) throws NullWordException {
		if (isSpanishEnglishWord(word)) {
			return spanish_english.get(word);
		}
		if (isSpanishFrenchWord(word)) {
			return spanish_french.get(word);
		}
		if (isSpanishItalianWord(word)) {
			return spanish_italian.get(word);
		}
		if (spanish_english.containsValue(word)) {
			Iterator<String> iterator = spanish_english.keySet().iterator();
			boolean stopper = false;
			String translate = "";
			String auxiliar = "";
			while (iterator.hasNext() && !stopper) {
				auxiliar = iterator.next();
				if (spanish_english.get(auxiliar).equals(word))
					stopper = true;
			}
			translate = auxiliar;
			return translate;
		}
		if (spanish_french.containsValue(word)) {
			Iterator<String> iterator = spanish_french.keySet().iterator();
			boolean stopper = false;
			String translate = "";
			String auxiliar = "";
			while (iterator.hasNext() && !stopper) {
				auxiliar = iterator.next();
				if (spanish_french.get(auxiliar).equals(word))
					stopper = true;
			}
			translate = auxiliar;
			return translate;
		}
		if (spanish_italian.containsValue(word)) {
			Iterator<String> iterator = spanish_italian.keySet().iterator();
			boolean stopper = false;
			String translate = "";
			String auxiliar = "";
			while (iterator.hasNext() && !stopper) {
				auxiliar = iterator.next();
				if (spanish_italian.get(auxiliar).equals(word))
					stopper = true;
			}
			translate = auxiliar;
			return translate;
		}
		throw new NullWordException("La palabra: " + word + " no se encuentra en los diccionarios.");
	}

	private boolean isSpanishFrenchWord(String spanishWord) {
		return spanish_french.containsKey(spanishWord);
	}

	public void addSpanishFrenchWord(String spanishWord, String frenchTranslate) throws RepeatedWordException {
		if (isSpanishFrenchWord(spanishWord)) {
			throw new RepeatedWordException(
					"La palabra: " + spanishWord + " ya se encuentra en el diccionario español-frances.");
		}
		spanish_french.put(spanishWord, frenchTranslate);
	}

	private boolean isSpanishItalianWord(String spanishWord) {
		return spanish_italian.containsKey(spanishWord);
	}

	public void addSpanishItalianWord(String spanishWord, String italianTranslate) throws RepeatedWordException {
		if (isSpanishItalianWord(spanishWord)) {
			throw new RepeatedWordException(
					"La palabra: " + spanishWord + " ya se encuentra en el diccionario español-italiano");
		}
		spanish_italian.put(spanishWord, italianTranslate);
	}
}
