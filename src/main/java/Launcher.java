import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class Launcher
{
    public static void main(String[] args)
    {
        try
        {
            CodeSource cs = Launcher.class.getProtectionDomain().getCodeSource();
            File jarFile;
            jarFile = new File(cs.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            System.setProperty("jarDir",jarDir);
        }
        catch (URISyntaxException ex)
        {
            ex.printStackTrace();
        }   

        Principal.main(args);
    }   
}
