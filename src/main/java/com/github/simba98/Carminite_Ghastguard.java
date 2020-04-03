package com.github.simba98;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;

@Name("Carminite_Ghastguard_CoreMod")
public class Carminite_Ghastguard implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"com.github.simba98.ClassTransformer"};
    }
    @Override
    public String getModContainerClass() {
        return null;
    }
    @Override
    public String getSetupClass() {
        return null;
    }
    @Override
    public void injectData(Map<String, Object> data) {
    }
    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static double tractiveAccellLimit(double originTractiveAccell) {
        // System.out.println("Catched " + originAccell);
        if (originTractiveAccell > Carminite_Ghastguard_Config.MaxtractiveAccell)
            return Carminite_Ghastguard_Config.MaxtractiveAccell;
        if (originTractiveAccell < -Carminite_Ghastguard_Config.MaxtractiveAccell)
            return -Carminite_Ghastguard_Config.MaxtractiveAccell;
        return originTractiveAccell;
    }

    public static double brakeAccellLimit(double originBrakeAccell) {
        if (originBrakeAccell > Carminite_Ghastguard_Config.MaxbrakeAccell)
            return Carminite_Ghastguard_Config.MaxbrakeAccell;
        if (originBrakeAccell < -Carminite_Ghastguard_Config.MaxbrakeAccell)
            return -Carminite_Ghastguard_Config.MaxbrakeAccell;
        return originBrakeAccell;
    }
}