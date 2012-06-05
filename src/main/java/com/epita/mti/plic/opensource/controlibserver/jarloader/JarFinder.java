package com.epita.mti.plic.opensource.controlibserver.jarloader;

import java.io.File;

/**
 *
 * @author Benoit "KIDdAe" Vasseur
 * This class is used to get only jar files when searching files
 * in a specific path by using a JarFilter.
 */
public class JarFinder
{
  public JarFinder()
  {
  }

  public File[] listFiles(String url)
  {
    File dir = new File(url);
    JarFilter filter = new JarFilter();
    return dir.listFiles(filter);
  }
}
