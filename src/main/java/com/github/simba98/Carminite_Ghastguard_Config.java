package com.github.simba98;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = "carminite_ghastguard_main")
public final class Carminite_Ghastguard_Config {

    @Config.RangeDouble(min = 0, max = 10000.0)
    public static double MaxtractiveAccell = 0.25;

    @Config.RangeDouble(min = 0, max = 10000.0)
    public static double MaxbrakeAccell = 0.5;

    public static boolean AllowCommand = true;

    @Mod.EventBusSubscriber(modid = "carminite_ghastguard")
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals("carminite_ghastguard")) {
                ConfigManager.sync("carminite_ghastguard", Config.Type.INSTANCE);
            }
        }
    }
}
