package org.firstinspires.ftc.teamcode.config.subsystems;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import smile.interpolation.BilinearInterpolation; //I CAN SMILE NOW :DDDDDDDDD
import smile.interpolation.Interpolation2D;
//https://www.aihalo.dev/interpolation

public class Flywheel {
    private DcMotorEx shooter;

    private boolean shooterOn = false;
    private double targetSpeed = 1570;
    private double adjustSpeed = 0;

    public static double kS = 0.03, kV = 0.0004, kP = 0.005;
    //kS stands for "static." It's the greatest power you can give the flywheel motor(s) without it moving. So it should be a really small number below like 0.05 or something. You should increase it until it begins to move and then decrease it until it stops moving again.
    //kV is the next number you tune. Pick a velocity like 1500 or something and then increase kV until it gets there.
    //Tune P. Send a ball through the launcher and increase P until it doesn't overshoot past the target velocity in the recovery phase. Keep increasing though because unless you see overshoot you're fine.


    private static final double[] xs = {0, 15, 30, 45, 60};
    private static final double[] ys = {34, 44, 54};
    private static final double[][] velocities = {
            {1510, 1550, 1590},
            {1550, 1560, 1600},
            {1590, 1610, 1620},
            {1620, 1640, 1670},
            {1720, 1740, 1760},
    };
    public static final Interpolation2D velocityInterpolation = new BilinearInterpolation(xs, ys, velocities);

    public static double getFlyWheelSpeed(double distance){
        double [][] table = {
                {0, 1200.0},
                {20, 1400.0},
                {40, 1600.0},
                {60, 1800.0},
                {100000, 1800.0}
        };
        for(int i=0; i < table.length - 1; i++){
            double x1 = table[i][0];
            double y1 = table[i][1];

            double x2 = table[i+1][0];
            double y2 = table[i+1][1];

            if(distance >= x1 && distance <= x2){
                return y1 + (distance -x1) * (y2 - y1)/(x2-x1);
            }
        }
        return -1;


    }



    public double xDistance = 0;
    public double yDistance = 0;
    public Flywheel(HardwareMap hardwareMap) {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotor.Direction.REVERSE);
    }

    public void deactivate() {
        shooterOn = false;
        shooter.setPower(0);
    }

    public void activate() {
        shooterOn = true;
    }


    public void forPose(Pose current, Pose target/*, boolean close*/) {
        xDistance = Math.abs(target.x()-current.x());
        yDistance = Math.abs(target.y()-current.y());

        //if (close)
            setTarget(velocityInterpolation.interpolate(xDistance, yDistance));
        //else
        //    setTarget(1550);
    }



    public void setTarget(double velocity) {
        targetSpeed = velocity;
    }
    public void adjustTarget(double boost){adjustSpeed += boost;}

    public double getTarget() {
        return targetSpeed + adjustSpeed;
    }

    public boolean atTarget() {
        return getVelocity() > getTarget() - 20;
    }

    public double getVelocity() {
        return shooter.getVelocity();
    }

    public void periodic(){
        if (shooterOn){
            shooter.setPower((kV * getTarget()) + (kP * (getTarget() - getVelocity())) + kS);
        }
    }


}
