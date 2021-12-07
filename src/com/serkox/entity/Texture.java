package com.serkox.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture {

    private BufferedImage image;

    public Texture(String img){
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream("../textures/" + img + ".png"));
            System.out.println("ok");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getTexture() {
        return image;
    }

}
