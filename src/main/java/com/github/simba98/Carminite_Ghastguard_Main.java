package com.github.simba98;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Carminite_Ghastguard_Main.MODID, name = Carminite_Ghastguard_Main.NAME,
        version = Carminite_Ghastguard_Main.VERSION, useMetadata = true)
public class Carminite_Ghastguard_Main
{
    public static final String MODID = "carminite_ghastguard_main";
    public static final String NAME = "Carminite Ghastguard Main";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new Carminite_Ghastguard_Command());
    }
}
