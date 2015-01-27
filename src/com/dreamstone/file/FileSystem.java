package com.dreamstone.file;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class FileSystem {

	private static final String DYSTOPIA = "Dystopia";
	private static final String OS = System.getProperty("os.name").toLowerCase();
	private static final String s = System.getProperty("file.separator");
	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice gd = ge.getDefaultScreenDevice();
	private static GraphicsConfiguration gc = gd.getDefaultConfiguration();
	private static ClassLoader classLoader = FileSystem.class.getClassLoader();
	public static final String LINE_BREAK = System.getProperty("line.separator");
	public static final String TAB = "\t";
	private static File gameFolder;

	public static File getGameFolder() {
		return gameFolder;
	}

	public static boolean createGameDirectory() {
		if (OS.contains("win")) {
			String env = System.getenv("APPDATA");
			gameFolder = getFolder(env, DYSTOPIA);
		}
		else if (OS.contains("mac")) {
			String support = System.getProperty("user.home") + s + "Library" + s + "Application Support";
			gameFolder = getFolder(support, DYSTOPIA);
		}
		else if (OS.contains("nux")) {
			String config = System.getProperty("user.home");
			gameFolder = getFolder(config, "." + DYSTOPIA);
		}
		else {
			String dir = System.getProperty("user.dir");
			gameFolder = getFolder(dir, DYSTOPIA);
		}
		return gameFolder.exists();
	}

	public static File getFolder(File parent, String folderName) {
		File folder = new File(parent.getAbsolutePath(), folderName);
		if (!folder.exists() && !folder.mkdirs()) {
			boolean dirsMade = createGameDirectory();
			if (!dirsMade) throw new RuntimeException("Game directories cannot be made: send this report to DystopiaBugs@dreamstone.com");
		}
		return folder;
	}

	public static File getFolder(String parent, String folderName) {
		return getFolder(new File(parent), folderName);
	}

	public static File getFile(File file) {
		if (!fileExists(file)) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return file;
	}
	
	public static File getFile(File folder, String fileName) {
		File file = new File(folder.getAbsolutePath() + s + fileName);
		return getFile(file);
	}

	public static File getFile(String folder, String fileName) {
		return getFile(new File(folder), fileName);
	}
	
	public static boolean fileExists(File parent, String fileName) {
		File file = new File(parent.getAbsolutePath() + s + fileName);
		return fileExists(file);
	}
	
	public static boolean fileExists(File file) {
		return file != null && file.exists();
	}

	public static String getClassLoaderResourceDirectory(String parent, String fileName) {
		if (fileName != null && !fileName.isEmpty()) {
			return parent + "/" + fileName;
		}
		else {
			return parent;
		}
	}

	public static File getClassLoaderResourceFile(String directory, String fileName) {
		URL url = null;
		File f = null;
		url = classLoader.getResource(directory + "/" + fileName);
		try {
			f = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static void writeTextFile(File f, StringBuilder builder) throws IOException {
		writeTextFile(f, builder.toString());
	}

	public static void writeTextFile(File f, String str) throws IOException	{
		if (f == null || !f.exists()) {
			throw new IOException("Writing to null file");
		}

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		pw.write(str);
		pw.close();
	}

	public static String readTextFile(File f) throws IOException
	{
		if (f != null && f.exists()) {
			Scanner reader = new Scanner(f);
			reader.useDelimiter("\\Z");
			String str = reader.next();
			reader.close();
			return str;
		}
		return null;
	}

	public static BufferedImage readImageFile(File f) throws IOException {
		Image im = null;
		BufferedImage bi = null;

		try {
			im = ImageIO.read(f);
			bi = gc.createCompatibleImage(im.getWidth(null), im.getHeight(null), Transparency.TRANSLUCENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Graphics g = bi.createGraphics();
		g.drawImage(im, 0, 0, im.getWidth(null), im.getHeight(null), null);
		g.dispose();
		return bi;
	}

	public static BufferedImage loadImageFromJar(String directory, String imageNameDotExtension) {
		BufferedImage bi = null;

		try {
			bi = FileSystem.readImageFile(FileSystem.getClassLoaderResourceFile(directory, imageNameDotExtension));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
}
