package com.github.simba98;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;

@Name("Carminite_Ghastguard")
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

    static double MaxtractiveAccell = 0.1;
    public static double tractiveAccellLimit(double originTractiveAccell) {
        // System.out.println("Catched " + originAccell);
        if (originTractiveAccell > MaxtractiveAccell) return MaxtractiveAccell;
        if (originTractiveAccell < -MaxtractiveAccell) return -MaxtractiveAccell;
        return originTractiveAccell;
    }

    static double MaxbrakeAccell = 0.25;
    public static double brakeAccellLimit(double originBrakeAccell) {
        if (originBrakeAccell > MaxbrakeAccell) return MaxbrakeAccell;
        if (originBrakeAccell < -MaxbrakeAccell) return -MaxbrakeAccell;
        return originBrakeAccell;
    }
}