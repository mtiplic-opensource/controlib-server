package com.epita.mti.plic.opensource.controlibserver.plugin;

import com.epita.mti.plic.opensource.controlibserver.jarloader.JarClassLoader;
import com.epita.mti.plic.opensource.controlibutility.plugins.CLObserver;
import com.epita.mti.plic.opensource.controlibutility.plugins.CLObserverSend;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 *
 * @author benoit vasseur
 */
public class PluginVersionControl
{
    private JarClassLoader jarClassLoader;

    public PluginVersionControl() throws Exception
    {
        this.jarClassLoader = new JarClassLoader();
        jarClassLoader.initializeLoader();
    }
    
    /**
     * 
     * @param oldVersion
     * @param newVersion
     * @return true if newVersion is higher, false otherwise
     */
    private Boolean compareVersion(String[] oldVersion, String[] newVersion)
    {
       for (int i = 0; i < Math.max(oldVersion.length, newVersion.length); ++i)
       {   
        String oldString = oldVersion[i];
        String newString = newVersion[i];
        if (oldString.compareTo(newString) < 0) {
               return true;
           }
        else if (oldString.compareTo(newString) > 0) {
               return false;
           }
       }
       if (oldVersion.length > newVersion.length) {
            return false;
        }
       return true;
    }
    
    /**
     * 
     * @param pluginClass
     * @param pluginVersion
     * @return true when the version of the new plugin is higher than the old one, return false otherwise
     * @throws Exception 
     */
    public Boolean testPluginVersion(Class pluginClass, String pluginVersion) throws Exception
    {
        ArrayList<Class<?>> plugins = jarClassLoader.getPlugins();
        String version = "";
        for (Class plugin : plugins)
        {
            if (!pluginClass.equals(plugin))
            {
                continue;
            }
            Class[] interfaces = plugin.getInterfaces();
            for (Class c : interfaces)
            {
                if (c == CLObserver.class)
                {
                  Constructor<?> constructor = plugin.getConstructor();
                  CLObserver observer = (CLObserver) constructor.newInstance();
                  version = observer.getVersion();
                  break;
                }
                else if (c == CLObserverSend.class)
                {
                  Constructor<?> constructor = plugin.getConstructor();
                  CLObserverSend observer = (CLObserverSend) constructor.newInstance();
                  version = observer.getVersion();
                  break;
                }
            }
            break;
        }
        String[] oldVersionArray = version.split("\\.");
        String[] newVersionArray = pluginVersion.split("\\.");
        
        return compareVersion(oldVersionArray, newVersionArray);
    }
}
