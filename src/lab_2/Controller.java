package lab_2;

import lab_1.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import javafx.geometry.Point2D;
import java.io.*;


public class Controller implements Initializable{
    @FXML
    private FileChooser fileChooser;
    @FXML
    private Spinner spinner;
    public LineChart chart;
    public NumberAxis xAxis, yAxis;
    public Button switcher_btn;
    private int next;
    private boolean mode;

    private int approx_degree;
    private int n;
    private double[] X, Y;
    private double dragX, dragY;
    private Polynom pol1, pol2;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT","*.txt");
        fileChooser.getExtensionFilters().add(extFilter);


        try{
            File file = new File("C:\\Users\\Виктория\\IdeaProjects\\WSkills\\src\\lab_2\\input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            n = Integer.parseInt(br.readLine());

            String[] str = new String[n + 1];
            X = new double[n + 1];
            str = br.readLine().split(" ");
            for (int i = 0; i <= n; i++)
                X[i] = Double.parseDouble(str[i]);

            str = br.readLine().split(" ");
            Y = new double[n + 1];
            for (int i = 0; i <= n; i++)
                Y[i] = Double.parseDouble(str[i]);
        } catch(IOException e)
        {
            System.out.println("ERROR");
        }

        approx_degree = n;
        pol1 = new Approx(X.clone(), Y.clone(), approx_degree);
        pol2 = new Interp(X.clone(), Y.clone());

        SpinnerValueFactory<Integer> temp = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, n, n);
        spinner.setValueFactory(temp);

        getField();
    }

    private void getField(){
        double max_X = X[0];
        double min_X = X[0];
        double max_Y = Y[0];
        double min_Y = Y[0];
        for (int i = 1; i < X.length; i++)
        {
            if (X[i] < min_X)
                min_X = X[i];
            if (X[i] > max_X)
                max_X = X[i];
            if (Y[i] < min_Y)
                min_Y = Y[i];
            if (Y[i] > max_Y)
                max_Y = Y[i];
        }

        getChart(3*min_X/2 - max_X/2, 3*max_X/2 -min_X/2,3*min_Y/2 - max_Y/2, 3*max_Y/2 - min_Y/2);
    }

    private void getChart(double leftBound, double rightBound, double lowerBound, double upperBound) {
        xAxis.setLowerBound(leftBound);
        xAxis.setUpperBound(rightBound);
        yAxis.setLowerBound(lowerBound);
        yAxis.setUpperBound(upperBound);

        chart.getData().removeAll(chart.getData());

        XYChart.Series points = new XYChart.Series();
        XYChart.Series points1 = new XYChart.Series();
        XYChart.Series points2 = new XYChart.Series();

        for (int i = 0; i < X.length; i++)
            points.getData().add(new XYChart.Data(X[i], Y[i]));


        for (double i = leftBound; i < rightBound; i += Math.abs(leftBound-rightBound)/100) {
            points1.getData().add(new XYChart.Data<>(i, pol1.getValue(i)));
            points2.getData().add(new XYChart.Data<>(i, pol2.getValue(i)));
        }

        chart.getData().add(points);
        chart.getData().add(points1);
        chart.getData().add(points2);
    }

    @FXML
    private void input(ActionEvent event) throws Exception{
        File file = this.fileChooser.showOpenDialog(null);
        if (file != null){
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                n = Integer.parseInt(br.readLine());

                String[] str = new String[n + 1];
                X = new double[n + 1];
                str = br.readLine().split(" ");
                for (int i = 0; i <= n; i++)
                    X[i] = Double.parseDouble(str[i]);

                str = br.readLine().split(" ");
                Y = new double[n + 1];
                for (int i = 0; i <= n; i++)
                    Y[i] = Double.parseDouble(str[i]);
            } catch(IOException e)
            {
                System.out.println("ERROR");
            }

            approx_degree = n;
            pol1 = new Approx(X.clone(), Y.clone(), approx_degree);
            pol2 = new Interp(X.clone(), Y.clone());

            SpinnerValueFactory<Integer> temp = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, n, n);
            spinner.setValueFactory(temp);

            getField();
        }
    }

    @FXML
    private void Approximate(ActionEvent event) throws Exception{
        approx_degree = (int)spinner.getValue();
        pol1 = new Approx(X.clone(), Y.clone(), approx_degree);
        getField();
    }

    @FXML
    private void switcher(ActionEvent event) {
        if (mode) {
            mode = false;
            switcher_btn.setText("Edit");
        }
        else {
            mode = true;
            switcher_btn.setText("No edit");
        }
    }

    @FXML
    private void Press(MouseEvent event){
        Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());
        double xAxisLoc = xAxis.sceneToLocal(pointInScene).getX();
        double yAxisLoc = yAxis.sceneToLocal(pointInScene).getY();
        dragX = xAxis.getValueForDisplay(xAxisLoc).doubleValue();
        dragY = yAxis.getValueForDisplay(yAxisLoc).doubleValue();

        System.out.println(dragX);
        System.out.println(dragY);

        if(mode)
        {
            next = 0;
            double len = Double.MAX_VALUE;
            for (int i = 0; i < X.length; i++) {
                double cur = Math.abs(dragX - X[i]);
                if (cur < len) {
                    len = cur;
                    next = i;
                }
            }
        }
    }

    @FXML
    private void Dragg(MouseEvent event) {
        if (mode) {
            Y[next] = yAxis.getValueForDisplay(event.getY()).doubleValue();
            pol1 = new Approx(X.clone(), Y.clone(), approx_degree);
            pol2 = new Interp(X.clone(), Y.clone());

            getChart(xAxis.getLowerBound(), xAxis.getUpperBound(), yAxis.getLowerBound(), yAxis.getUpperBound());
        }
        else {
            Point2D pointInScene = new Point2D(event.getSceneX(), event.getSceneY());
            double xAxisLoc = xAxis.sceneToLocal(pointInScene).getX();
            double yAxisLoc = yAxis.sceneToLocal(pointInScene).getY();
            double x = xAxis.getValueForDisplay(xAxisLoc).doubleValue();
            double y = yAxis.getValueForDisplay(yAxisLoc).doubleValue();
            double tempX = dragX - x;
            double tempY = dragY - y;

            getChart(xAxis.getLowerBound() + tempX, xAxis.getUpperBound() + tempX,yAxis.getLowerBound() + tempY, yAxis.getUpperBound() + tempY);
        }
    }

    @FXML
    private void Zoom(ScrollEvent event){
        double leftBound = xAxis.getLowerBound();
        double rightBound = xAxis.getUpperBound();
        double lowerBound = yAxis.getLowerBound();
        double upperBound = yAxis.getUpperBound();
        double length = (upperBound - lowerBound) / 4;
        double width = (rightBound - leftBound) / 4;

        if (event.getDeltaY() > 0) {
            if (width <= 1)
                return;
            getChart(leftBound + width/2, rightBound - width/2,lowerBound + length/2, upperBound - length/2);
        } else {
            if (length >= 100)
                return;
            getChart(leftBound - width, rightBound + width,lowerBound - length, upperBound + length);
        }
    }
}