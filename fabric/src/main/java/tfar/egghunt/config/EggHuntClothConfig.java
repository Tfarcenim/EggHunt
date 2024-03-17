package tfar.egghunt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import tfar.egghunt.EggHunt;

@Config(name = EggHunt.MOD_ID)
public class EggHuntClothConfig implements ConfigData {

    //client

    //server
    public int start_x = 0;
    public int start_y = 0;
    public int start_z = 0;

}
