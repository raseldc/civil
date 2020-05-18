/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controllers;

import com.civil.model.Settings;
import com.civil.service.MixService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rasel
 */
@Component
public class CommonFunction {

    @Autowired
    MixService mixService;

    public CommonFunction() {
    }

    public String getBase64String(String fileLocation) {

        try {
            File serverFile = new File(fileLocation);
            BufferedImage bImage = ImageIO.read(serverFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] data = bos.toByteArray();
            String base64String = Base64.encodeBase64String(data);

            base64String = "data:image/png;base64," + base64String;

            return base64String;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String getPhotoByEmail(String email) throws IOException {

        if (email != null) {
            Settings s = mixService.getSettingsByKey("userProfilePicLocation");

            String realPath = "";
            if (s != null) {
                realPath = s.getValue();//ApplicationSettings.userProfilePicLocation;
            } else {
                return null;
            }
            File serverFile = new File(realPath + email + ".jpg");
            BufferedImage bImage = ImageIO.read(serverFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] data = bos.toByteArray();
            String base64String = Base64.encodeBase64String(data);
            base64String = "data:image/png;base64," + base64String;
            return base64String;
        } else {
            return null;
        }

    }

}
