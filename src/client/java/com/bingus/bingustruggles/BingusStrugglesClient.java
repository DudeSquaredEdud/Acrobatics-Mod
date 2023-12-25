package com.bingus.bingustruggles;

import com.fox2code.foxloader.client.KeyBindingAPI;
import com.fox2code.foxloader.loader.ClientMod;
import com.fox2code.foxloader.loader.packet.ClientHello;
import com.fox2code.foxloader.network.NetworkPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.src.client.KeyBinding;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.game.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class BingusStrugglesClient extends BingusStruggles implements ClientMod {

    @Override //Init Sequence
    public void onInit() {
        System.out.println("Acrobatics Mod initializing!");
        //This puts the keybinding in the in-game menu
        KeyBindingAPI.registerKeyBinding(dash);
        KeyBindingAPI.registerKeyBinding(prone);
        //This code makes a new file to save settings in
        try {
            FileReader savedSettings = new FileReader("mods/AcrobaticsModSettings.txt");
        }
        catch(FileNotFoundException e){
            //This complicated try catch just makes a file; the try/catch in this catch is more of a formality
            try {
                Files.createFile(Paths.get("mods/AcrobaticsModSettings.txt"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public boolean onNetworkPlayerDisconnected(NetworkPlayer networkPlayer, String kickMessage, boolean cancelled) {
        //This only serves to make sure the player doesn't get teleported down
            // two blocks when they leave a world while crouched
        if(networkPlayer.getPlayerName().equals(OurPlayer.getPlayerName()) && isProne){
            isProne = false;
            OurPlayer.moveEntity(0,1.8,0);
        }
        return super.onNetworkPlayerDisconnected(networkPlayer, kickMessage, cancelled);
    }

    //Public Variables
public KeyBinding dash = new KeyBinding("Dash", 15); //15 is the keycode for tab; this is what I find easy.
    public KeyBinding prone = new KeyBinding("Prone", 29); //15 is the keycode for lcontrol
    boolean dashed = false; // used to tell if the player has dashed recently
    long dashtimer = System.currentTimeMillis(); // This is used with dashtimecooldown to determine the cooldown
    int dashtimeCooldown = 2000; // The dash cooldown, in milliseconds
    float groundDashCoefficient = 1; // the distance you dash forward when dashing on the ground
    float airDashCoefficient = 0.33333f; // the distance you dash forward when dashing in the air
    float dashCoefficient; // convience variable to choose between ground and air dash coefficients

    int wallJumpTimer; // The timer for when you can walljump
    boolean spaceWasHeldLastTick = false; // what it says on the tin ; true if space was held last tick
    public static boolean isProne = false;

    EntityPlayerSP OurPlayer; // simplification variable
    boolean reasonableDashingTime; // if it's a reasonable time to dash
    boolean reasonableUnProneingTime; // if it's a reasonable time to get up
    long hundredMillicecondTimer = System.currentTimeMillis();
    
    public static float height = 0.37F;
    boolean ForceProne = false;


    public void onTick() {

        OurPlayer = Minecraft.getInstance().thePlayer; //it is almost silly how much time this saves
        try{
            //WALL JUMPING
            //If the player is against a wall and not on the ground and the timer is within reason
            if (OurPlayer.isCollidedHorizontally && !OurPlayer.onGround && wallJumpTimer < 10){
                //If the player presses space again
                if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !spaceWasHeldLastTick){
                    OurPlayer.motionY = .5; //they jump
                    wallJumpTimer = 10;     //the timer maxes out (you cant double wall jump
                    OurPlayer.fallDistance = 0; //The fall distance is set to zero (because I'm nice :] )
                }
                wallJumpTimer += 1; // increment timer
            }
            //Reset the timer when the player reaches the ground
            if (OurPlayer.onGround){
                wallJumpTimer = 0;
            }
            //Check to see if it's a reasonable time to dash
            reasonableDashingTime =
                            Minecraft.getInstance().inGameHasFocus
                            && !OurPlayer.isPlayerSleeping()
                            && !OurPlayer.isDead;


            //DASHING
            //Check for reasonability, as well as dashed status and if the button is pressed
            if(reasonableDashingTime && !dashed && Keyboard.isKeyDown(dash.keyCode)){
                OurPlayer.motionY = .4; // small jump
                double yaw = OurPlayer.rotationYaw/57.29578; // get the direction the player is facing in radians
                //check if we should use the ground dash coefficient ...
                if(OurPlayer.onGround){
                    dashCoefficient = groundDashCoefficient;
                }
                //... or the air dash coefficient
                else{
                    dashCoefficient = airDashCoefficient;
                }
                //add motion accordingly
                OurPlayer.motionX = -Math.sin(yaw)* dashCoefficient;
                OurPlayer.motionZ = Math.cos(yaw)* dashCoefficient;
                //The player has dashed, begin the cooldown
                dashed = true;
                dashtimer = System.currentTimeMillis() + dashtimeCooldown;
            }
            //resetting the dash timer
            if(dashed && dashtimer < System.currentTimeMillis()){
                dashed = false;
            }

            reasonableUnProneingTime = Minecraft.getInstance().theWorld.isBlockNormalCube((int)OurPlayer.posX, (int) OurPlayer.posY+1, (int)OurPlayer.posZ);

            //CRAWLING
            //turns out a reasonable dashing time is a reasonable prone time
            //this makes the player prone
            if(((reasonableDashingTime && Keyboard.isKeyDown(prone.keyCode) && hundredMillicecondTimer < System.currentTimeMillis()) && OurPlayer.onGround) || ForceProne) {
                isProne = !isProne; //flip flop
                if(isProne) { //Changes your hitbox and teleports you down
                    OurPlayer.ySize +=2 ;
                    OurPlayer.boundingBox.setBounds(0+OurPlayer.posX,0+OurPlayer.posY,0+OurPlayer.posZ,.7+OurPlayer.posX,.7+OurPlayer.posY,0.7+OurPlayer.posZ);
                    OurPlayer.moveEntity(0, 0-1.5, 0);
                }
                else {//reverses the above
                    OurPlayer.ySize -=2 ;
                    OurPlayer.boundingBox.setBounds(0 + OurPlayer.posX, 0 + OurPlayer.posY, 0 + OurPlayer.posZ, .7 + OurPlayer.posX, 1.8 + OurPlayer.posY, 0.7 + OurPlayer.posZ);
                }
                //this is a timer for how often you can change position.
                hundredMillicecondTimer = System.currentTimeMillis() + 600;
            }

            if(isProne){
                OurPlayer.yOffset = height;  //This causes issues when the game is closed >:(
                OurPlayer.landMovementFactor = 0.04F;
                OurPlayer.stepHeight = 0;
            }
            else{
                OurPlayer.yOffset = 1.62F;
                OurPlayer.stepHeight = .5f;
            }

            //This sets prone to false if you try to sneak, jump, sleep, etc
            if(OurPlayer.isSneaking() || OurPlayer.isJumping || OurPlayer.isPlayerSleeping()){
                isProne = false;
            }

        } catch (Exception e){} // No u
    }


}

