package com.lin.myzone.utils;

import javax.imageio.ImageIO;
import com.google.zxing.common.BitMatrix;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * QRCode生成接口
 * 图片叠加接口
 * 图文叠加接口
 * @author yl
 *
 */
public final class MatrixToImageWriter {

  private static final int BLACK = 0xFF000000;
  private static final int WHITE = 0xFFFFFFFF;

  private MatrixToImageWriter() {}
  
  public static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
      }
    }
    return image;
  }

  
  public static void writeToFile(BitMatrix matrix, String format, File file)
      throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, file)) {
      throw new IOException("Could not write an image of format " + format + " to " + file);
    }
  }

  
  public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
      throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, stream)) {
      throw new IOException("Could not write an image of format " + format);
    }
  }

  /** 
   * 添加图片水印 
   *  
   * @param targetImg 
   *            目标图片路径，如：C:\\kutuku.jpg 
   * @param waterImg 
   *            水印图片路径，如：C:\\kutuku.png 
   * @param x 
   *            水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间 
   * @param y 
   *            水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间 
   * @param alpha 
   *            透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明) 
   */  
  public final static void pressImage(String targetImg, String waterImg,  
          int x, int y, float alpha) {  
      try {  
          // 加载目标图片  
          File file = new File(targetImg);  
          Image image = ImageIO.read(file);  
          int width = image.getWidth(null);  
          int height = image.getHeight(null);  
            
          // 将目标图片加载到内存。  
          BufferedImage bufferedImage = new BufferedImage(width, height,  
                  BufferedImage.TYPE_INT_RGB);  
          Graphics2D g = bufferedImage.createGraphics();  
          g.drawImage(image, 0, 0, width, height, null);  

          // 加载水印图片。  
          Image waterImage = ImageIO.read(new File(waterImg));  
          int width_1 = waterImage.getWidth(null);  
          int height_1 = waterImage.getHeight(null);  
          // 设置水印图片的透明度。  
          g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,  
                  alpha));  

          // 设置水印图片的位置。  
          int widthDiff = width - width_1;  
          int heightDiff = height - height_1;  
          if (x < 0) {  
              x = widthDiff / 2;  
          } else if (x > widthDiff) {  
              x = widthDiff;  
          }  
          if (y < 0) {  
              y = heightDiff / 2;  
          } else if (y > heightDiff) {  
              y = heightDiff;  
          }  
            
          // 将水印图片“画”在原有的图片的制定位置。  
          g.drawImage(waterImage, x, y, width_1, height_1, null);  
          // 关闭画笔。  
          g.dispose();  
            
          // 保存目标图片。  
          ImageIO.write(bufferedImage, "jpg", file);  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
  }  

  /** 
   * 添加文字水印 
   *  
   * @param targetImg 
   *            目标图片路径，如：C:\\kutoku.jpg 
   * @param pressText 
   *            水印文字， 如：kutuku.com 
   * @param fontName 
   *            字体名称， 如：宋体 
   * @param fontStyle 
   *            字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC) 
   * @param fontSize 
   *            字体大小，单位为像素 
   * @param color 
   *            字体颜色 
   * @param x 
   *            水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间 
   * @param y 
   *            水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间 
   * @param alpha 
   *            透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明) 
   */  
  public static void pressText(String targetImg, String pressText,  
          String fontName, int fontStyle, int fontSize, Color color, int x,  
          int y, float alpha) {  
      try {  
          // 加载目标图片  
          File file = new File(targetImg);  
          Image image = ImageIO.read(file);  
          int width = image.getWidth(null);  
          int height = image.getHeight(null);  
            
          // 将目标图片加载到内存。  
          BufferedImage bufferedImage = new BufferedImage(width, height,  
                  BufferedImage.TYPE_INT_RGB);  
          Graphics2D g = bufferedImage.createGraphics();  
          g.drawImage(image, 0, 0, width, height, null);  
          g.setFont(new Font(fontName, fontStyle, fontSize));  
          g.setColor(color);  
          // 设置水印图片的透明度。  
          g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));  

          // 设置水印图片的位置。  
          //int width_1 = fontSize * getLength(pressText);  
          int width_1 = fontSize * pressText.length();
          int height_1 = fontSize;  
          int widthDiff = width - width_1;  
          int heightDiff = height - height_1;  
          if (x < 0) {  
              x = widthDiff / 2;  
          } else if (x > widthDiff) {  
              x = widthDiff;  
          }  
          if (y < 0) {  
              y = heightDiff / 2;  
          } else if (y > heightDiff) {  
              y = heightDiff;  
          }  

          // 将水印文字“写”在原有的图片的制定位置。  
          g.drawString(pressText, x, y + height_1);  
          // 关闭画笔。  
          g.dispose();  
            
          // 保存目标图片。  
          ImageIO.write(bufferedImage, "jpg", file);  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
  }  
  
}
