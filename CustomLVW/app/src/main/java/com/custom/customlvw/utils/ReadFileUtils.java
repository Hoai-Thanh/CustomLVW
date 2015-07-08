/**
 * 
 */
package com.custom.customlvw.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class ReadFileUtils {

	public String readFile(Context ctx, int resourceID) {
		StringBuilder contents = new StringBuilder();
		String sep = System.getProperty("line.separator");

		try {
			InputStream is = ctx.getResources().openRawResource(resourceID);

			BufferedReader input = new BufferedReader(
					new InputStreamReader(is), 1024 * 8);
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(sep);
				}
			} finally {
				input.close();
			}
		} catch (FileNotFoundException ex) {
			return null;
		} catch (IOException ex) {
			return null;
		}

		return contents.toString();
	}

}
