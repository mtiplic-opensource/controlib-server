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

    public PluginVersionControl() throws Exception {
        this.jarClassLoader = new JarClassLoader();
        jarClassLoader.initializeLoader();
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
            if (pluginClass != plugin)
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
        if (version.equals(""))
        {
            return false;
        }
        for (int i = 0; i < version.length(); ++i)
        {
            if (version.charAt(i) == pluginVersion.charAt(i))
            {
                continue;
            }
            else
            {
                char oldChar = version.charAt(i);
                char newChar = pluginVersion.charAt(i);
                if (newChar > oldChar)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }
    
    
}
