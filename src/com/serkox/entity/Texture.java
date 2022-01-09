package com.serkox.entity;

import com.serkox.main.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture {

    private BufferedImage image;

    public Texture(String img){
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream("/com/serkox/textures/" + img + ".png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getTexture() {
        return image;
    }

}
