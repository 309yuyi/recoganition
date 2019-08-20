package com.example.recoganition;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.Vector;

import static org.opencv.core.Core.merge;
import static org.opencv.core.Core.split;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2HSV;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.equalizeHist;
import static org.opencv.imgproc.Imgproc.threshold;

public class BitmaptoColor
{
    static Point[] point;


    //bitmap转Mat
    public static Mat bitmapToMat(Bitmap bm) {
        Bitmap bmp32;
        bmp32 = bm.copy(Bitmap.Config.RGB_565, true);
        Mat imgMat = new Mat(bm.getHeight(), bm.getWidth(), CvType.CV_8UC3, new Scalar(0));
        Utils.bitmapToMat(bmp32, imgMat);
        return imgMat;
    }

    //得到直线的中心点坐标
    public static void getLine(Bitmap bitmap) {
        Mat imag;
        imag = bitmapToMat(bitmap);
        int CV_THRESH_BINARY = 0;

        //进行二值化处理，选择30，200.0为阈值
        threshold(imag, imag, 30, 200.0, CV_THRESH_BINARY);
        Mat Line = null;
        Imgproc.HoughLinesP(imag, Line, 1, Math.PI / 180, 20, 150, 10);
        //Bitmap bm = null;
        if (Line.rows() > 0 && Line.cols() > 0) {
            for (int i = 0; i < Line.cols(); i++) {
                double[] l = Line.get(0, i);

                point[i] = new Point((l[0] + l[2] / 2), (l[1] + l[3]) / 2);
                //if (l.length == 4) {
                //  if (Math.abs(l[1] - l[3]) < 2) {
                //    Point p1 = new Point(l[0], l[1]);
                //  Point p2 = new Point(l[2], l[3]);
                //Imgproc.line(imag, p1, p2, new Scalar(255, 255, 255), 2);

                // Utils.matToBitmap(imag, bm);


                // }
                //}
            }
        }
        double max = ((point[0].x - point[1].x) * (point[0].x - point[1].x) + (point[0].y - point[1].y) * (point[0].y - point[1].y));
        int maxnum = 0;
        for (int i = 0; i <= 3; i++) {
            if (max < ((point[i].x - point[i + 1].x) * (point[i].x - point[i + 1].x) + (point[i].y - point[i + 1].y) * (point[i].y - point[i + 1].y)))
            {
                max =((point[i].x - point[i + 1].x) * (point[i].x - point[i + 1].x) + (point[i].y - point[i + 1].y) * (point[i].y - point[i + 1].y));
                maxnum=i;
            }
            if(maxnum==0)
            {for(int j=0;j<=2;j++)
              {
                Point temp;
                temp=point[j];
                point[j]=point[4-j];
                point[4-j]=temp;
              }

             }
        }


    }
static public   Scalar getcolor( Mat img,Mat hsv,Point p)
{
    cvtColor(img,hsv,COLOR_BGR2HSV);
    //因为我们读取的是彩色图，直方图均衡化需要在HSV空间做

return 

}

}

