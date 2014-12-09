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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.dreamstone.util.DystopiaLogger;

public class FileSystem {

	private static final String DYSTOPIA = "Dystopia";
	private static final String OS = System.getProperty("os.name").toLowerCase();
	private static final String s = System.getProperty("file.separator");
	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice gd = ge.getDefaultScreenDevice();
	private static GraphicsConfiguration gc = gd.getDefaultConfiguration();
	
	private static File gameFolder;
	private static File resourcesFolder;
	private static File savesFolder;
	
	public static File getGameFolder() {
		return gameFolder;
	}
	
	public static File getResourcesFolder() {
		if (resourcesFolder == null) {
			URL resources = FileSystem.class.getResource(".." + s + ".." + s +".." + s + "resources");
			try {
				return new File(new URI(resources.toString()));
			} catch (URISyntaxException e) {
				DystopiaLogger.getLogger().log(Level.SEVERE, "INVALID FILE PATH");
				e.printStackTrace();
			}
		}
		return resourcesFolder;
	}
	
	public static File getSavesFolder() {
		return savesFolder;
	}
	
	private static boolean createGameDirectories()
	{
		if (OS.contains("win"))
		{
			String env = System.getenv("APPDATA");
			gameFolder = makeFolder(env, DYSTOPIA);
		}
		else if (OS.contains("mac"))
		{
			String support = System.getProperty("user.home") + s + "Library" + s + "Application Support";
			gameFolder = makeFolder(support, DYSTOPIA);
		}
		else if (OS.contains("nux"))
		{
			String config = System.getProperty("user.home");
			gameFolder = makeFolder(config, "." + DYSTOPIA);
		}
		else
		{
			String dir = System.getProperty("user.dir");
			gameFolder = makeFolder(dir, DYSTOPIA);
		}
		savesFolder = makeFolder(gameFolder.getAbsolutePath(), "saves");
		return gameFolder.exists();
	}

	public static File makeFolder(String parent, String folderName) {
		File folder = new File(parent, folderName);
		if (!folder.exists() && !folder.mkdirs()) {
			boolean dirsMade = createGameDirectories();
			if (!dirsMade) throw new RuntimeException("Game directories cannot be made: send this report to DystopiaBugs@dreamstone.com");
		}
		return folder;
	}
	
	public static File makeFile(File file) {
		if (file != null && !file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return file;
	}
	
	public static File makeFile(String folder, String fileName) {
		File file = new File(folder + s + fileName);
		return makeFile(file);
	}
	
	public static void writeTextFile(File f, StringBuilder builder) throws IOException
	{
		writeTextFile(f, builder.toString());
	}
	
	public static void writeTextFile(File f, String str) throws IOException
	{
		if (f == null || !f.exists())
		{
			throw new IOException("Writing to null file");
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		pw.write(str);
		pw.close();
	}
	
	public static String readTextFile(File f) throws IOException
	{
		if (f != null && f.exists())
		{
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
		
		if (f != null && f.exists()) {
//			URL fileUrl = f.toURI().toURL();
//			File newFile = new File(fileURL);
			try {
				im = ImageIO.read(f);
				bi = gc.createCompatibleImage(im.getWidth(null), im.getHeight(null), Transparency.TRANSLUCENT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Graphics g = bi.getGraphics();
		g.drawImage(im, 0, 0, im.getWidth(null), im.getHeight(null), null);
		g.dispose();
		return bi;
	}
}
