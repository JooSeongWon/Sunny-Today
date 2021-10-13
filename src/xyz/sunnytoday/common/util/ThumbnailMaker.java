package xyz.sunnytoday.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ThumbnailMaker {
    /**
     * 원본이미지의 썸네일 이미지를 만듭니다.
     * @param originImgSaveName 원본이미지 저장이름
     * @param uploadPath 업로드 폴더 경로
     * @param width 썸네일 넓이
     * @param height 썸네일 높이 
     * @return 저장된 썸네일 파일 이름
     */
    public static String makeThumbnail(String originImgSaveName, String uploadPath, int width, int height) {
        try {

            int dotIndex = originImgSaveName.lastIndexOf(".");
            String thumbnailPath = originImgSaveName.substring(0, dotIndex) + "_thumb" + originImgSaveName.substring(dotIndex);  // 썸네일 파일명
            String imgFormat = originImgSaveName.substring(dotIndex + 1); // 썸네일 포맷

            // 원본 이미지 가져오기
            Image image = ImageIO.read(new File(uploadPath + "/" + originImgSaveName));

            // 이미지 리사이즈
            Image resizeImage = image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);

            // 새 이미지 저장하기
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, imgFormat, new File(uploadPath + "/" + thumbnailPath));

            return thumbnailPath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
