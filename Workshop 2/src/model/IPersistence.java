package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface IPersistence {
	public BufferedWriter writeToFile() throws IOException;
	public BufferedReader readFromFile() throws IOException;
}
