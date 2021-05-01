package com.QuasarFireOperationMS.util;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author jiz10
 * 30/4/21
 */
@Service
public class LocationCalculator {

    @Value("#{new Double('${satellites.one.x}')}")
    private Double satOneX;

    @Value("#{new Double('${satellites.one.y}')}")
    private Double satOneY;

    @Value("#{new Double('${satellites.two.x}')}")
    private Double satTwoX;

    @Value("#{new Double('${satellites.two.y}')}")
    private Double satTwoY;

    @Value("#{new Double('${satellites.three.x}')}")
    private Double satThreeX;

    @Value("#{new Double('${satellites.three.y}')}")
    private Double satThreeY;

    public double[] getLocation(double[] distances) {
        //double[][] positions2 = new double[][]{{37.418436, -121.963477}, {37.417243, -121.961889}, {37.418692, -121.960194}};
        //double[] distances2 = new double[]{0.265710701754, 0.234592423446, 0.0548954278262};

        double[][] positions = new double[][]{{satOneX, satOneY}, {satTwoX, satTwoY}, {satThreeX, satThreeY}};
        //double[][] positions = new double[][]{{37.418436, -121.963477}, {37.417243, -121.961889}, {37.418692, -121.960194}};
        //double[] distances = new double[]{0.265710701754, 0.234592423446, 0.0548954278262};

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        //the answer
        double[] centroid = optimum.getPoint().toArray();
        System.out.println(centroid[0]);
        System.out.println(centroid[1]);

        return centroid;
    }
    /*
    {
    "position": {
        "x": -58.315252587138595,
        "y": -69.55141837312165
    },
    */
}
