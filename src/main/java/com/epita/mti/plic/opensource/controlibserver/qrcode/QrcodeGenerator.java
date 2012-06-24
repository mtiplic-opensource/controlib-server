package com.epita.mti.plic.opensource.controlibserver.qrcode;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class contains static methods which generate QRcode used to init
 * connection with the remote client
 *
 * @author Mickael "Mogmi" Bidon
 */
public class QrcodeGenerator
{

  public static BufferedImage generateQrcode(String ip, int output_port, int input_port, int width, int height) throws WriterException
  {
    BitMatrix mtx;
    String input = ip + "|" + String.valueOf(output_port) + "|" + String.valueOf(input_port);
    QRCodeWriter writer = new QRCodeWriter();
    mtx = writer.encode(input, BarcodeFormat.QR_CODE, width, height);
    if (mtx != null)
    {
      BufferedImage image = MatrixToImageWriter.toBufferedImage(mtx);
      return image;
    }
    return null;
  }

  public static void generateQrcodeToFile(File file, String format, String ip, int input_port, int output_port, int width, int height) throws WriterException, IOException
  {
    BitMatrix mtx;
    String input = ip + "|" + String.valueOf(output_port) + "|" + String.valueOf(input_port);
    QRCodeWriter writer = new QRCodeWriter();
    mtx = writer.encode(input, BarcodeFormat.QR_CODE, width, height);
    if (mtx != null)
    {
      BufferedImage image = MatrixToImageWriter.toBufferedImage(mtx);
      ImageIO.write(image, format, file);
    }
  }
}
