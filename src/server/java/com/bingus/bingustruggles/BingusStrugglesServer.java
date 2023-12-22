package com.bingus.bingustruggles;

import com.fox2code.foxloader.loader.ServerMod;
import com.fox2code.foxloader.loader.packet.ClientHello;
import com.fox2code.foxloader.network.NetworkPlayer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class BingusStrugglesServer extends BingusStruggles implements ServerMod {
    @Override
    public void onInit() {
        System.out.println("DudeSquared Acrobatics Mod server thing is initializing!");
    }


}
