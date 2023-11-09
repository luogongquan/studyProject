package com.lgq.word;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class EchartsBase64Generator {
    public static byte[] generateEchartsBase64() {
        // Create a dataset with sample data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5, "Series 1", "Category 1");
        dataset.addValue(10, "Series 1", "Category 2");
        dataset.addValue(15, "Series 1", "Category 3");
        dataset.addValue(8, "Series 2", "Category 1");
        dataset.addValue(12, "Series 2", "Category 2");
        dataset.addValue(9, "Series 2", "Category 3");

        // Create a JFreeChart object
        JFreeChart chart = ChartFactory.createBarChart(
                "Echarts Example", // Chart title
                "Category", // X-axis label
                "Value", // Y-axis label
                dataset // Dataset
        );

        // Set chart styling if needed
        // chart.getCategoryPlot().set...

        // Generate the chart image as byte array
        byte[] imageBytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ChartUtils.writeChartAsPNG(baos, chart, 500, 300);
            imageBytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the image byte array to base64 string
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return imageBytes;
    }

    public static void main(String[] args) {
        System.out.println(generateEchartsBase64());
        System.out.println(new String(image2byte("C:\\Users\\luogongquan\\Desktop\\下载.png")));
    }

    public static byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
}