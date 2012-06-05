package com.epita.mti.plic.opensource.controlibserver.jarloader;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Benoit "KIDdAe" Vasseur
 * This class is used to get only jar files when searching files
 * in a specific directory.
 */
public class JarFilter implements FilenameFilter
{

  private String extension;

  public JarFilter()
  {
    this.extension = "jar";
  }

  @Override
  public boolean accept(File dir, String filename)
  {
    if (extension != null && filename.endsWith('.' + extension))
    {
      return true;
    }
    return false;

  }
}
