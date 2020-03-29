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

    static double MaxAccell = 0.5;
    public static double AccellLimit(double originAccell) {
        // System.out.println("Catched " + originAccell);
        if (originAccell > MaxAccell) return MaxAccell;
        if (originAccell < -MaxAccell) return -MaxAccell;
        return originAccell;
    }


}