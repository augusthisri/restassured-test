package com.test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.test.base.BaseConfig;

public class TestUtils extends BaseConfig {

	public static StringBuffer getPayload(String filePath) {

		StringBuffer str = new StringBuffer();
		File file = new File(filePath);

		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = in.readLine()) != null) {
				str.append(line);
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return str;
	}
}
