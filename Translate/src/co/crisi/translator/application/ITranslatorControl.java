package co.crisi.translator.application;

import java.util.HashMap;

import co.crisi.translator.exceptions.NullWordException;
import co.crisi.translator.exceptions.RepeatedWordException;

public interface ITranslatorControl {
	public HashMap<String, String> getSpanish_english();

	public HashMap<String, String> getSpanish_french();

	public HashMap<String, String> getSpanish_italian();

	public void addSpanishEnglishWord(String spanishWord, String englishTranslate) throws RepeatedWordException;

	public void addSpanishFrenchWord(String spanishWord, String frenchTranslate) throws RepeatedWordException;

	public void addSpanishItalianWord(String spanishWord, String italianTranslate) throws RepeatedWordException;

	public String getTranslate(String word) throws NullWordException;
}
