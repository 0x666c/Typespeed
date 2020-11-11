package io.x666c.typespeed;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class WordScraper {

	private static final String key = "jecgaa";
	private static Stack<String> wordStack = new Stack<>();

	private static final String ERROR_WORD = "<ERROR>";

	public static void fillStack(int items) {
		String json = randomWords(items);
		String[] words = null;
		if (json.equals(ERROR_WORD)) {
			// Use offline word list
			words = (String[]) new BufferedReader(
					new InputStreamReader(WordScraper.class.getResourceAsStream("/english-nouns.txt"))).lines()
							.toArray(String[]::new);
			Collections.shuffle(Arrays.asList(words));
			System.out.println("Using offline word list");
		} else {
			System.out.println("Formatting");
			json = json.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");

			words = json.split(",");
			words = Arrays.stream(words).filter(w -> w.length() <= 16).map(w -> w.trim()).toArray(String[]::new);
		}
		wordStack.addAll(Arrays.asList(words));
	}

	public static String getWord() {
		if (!wordStack.isEmpty()) {
			return wordStack.pop();
		} else {
			fillStack(200);
			return getWord(); // Error?
		}
	}

	private static String randomWords(int amt) {
		try {
			System.out.println("Opened connection");
			HttpURLConnection httpClient = (HttpURLConnection) new URL(
					"https://random-word-api.herokuapp.com/word?key=" + key + "&number=" + amt)
							.openConnection(Proxy.NO_PROXY);
			httpClient.setRequestMethod("GET");
			httpClient.setRequestProperty("Cache-Control", "max-age=0");
			httpClient.setRequestProperty("Connection", "close");
			System.out.println("Reading");
			String get = new BufferedReader(new InputStreamReader(httpClient.getInputStream())).lines().map(w -> {
				System.out.println(w);
				return w;
			}).reduce("", String::concat);

			return get;
		} catch (Exception e) {
			return ERROR_WORD;
		}

	}

	public static void rickRoll(String[] lyrics) {
		wordStack.clear();

		String[] words = lyrics;
		Collections.reverse(Arrays.asList(words));
		words = Arrays.stream(words).filter(w -> w.length() <= 16).map(w -> w.trim().toLowerCase())
				.toArray(String[]::new);

		wordStack.addAll(Arrays.asList(words));
	}

}