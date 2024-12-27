import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import acmx.export.java.io.FileReader;


/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the
	 * requested file does not exist or if an error occurs as the file is being
	 * read.
	 */
	private HashMap<String, NameSurferEntry> allInfo;

	// = new HashMap <String, NameSurferEntry>();
	public NameSurferDataBase(String filename) {
		allInfo = new HashMap<String, NameSurferEntry>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				NameSurferEntry entry = new NameSurferEntry(line);
				allInfo.put(entry.getName(), entry);
			}
			
			br.close();

		} catch (IOException e) {
			System.out.println("No File found");
		}

	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	
	//Fixes the name, but this was not required.
	private void nameFixer(String name) {
		char name1 = name.charAt(0);
		String name2 = name.substring(1, name.length());
		if (Character.isLowerCase(name1)) {
			name1 = Character.toUpperCase(name1);
		}
		for (int i = 0; i < name2.length(); i++) {

			if (Character.isUpperCase(name2.charAt(i))) {
				name2 = name2.substring(1, i) + Character.toLowerCase(name2.charAt(i))
						+ name2.substring(i, name2.length());
			}

		}
		name = name1 + name2;
		
		

	}

	public NameSurferEntry findEntry(String name) {
		nameFixer(name);
		return allInfo.get(name);
	}
}
