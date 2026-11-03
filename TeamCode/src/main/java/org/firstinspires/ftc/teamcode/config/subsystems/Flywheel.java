package org.firstinspires.ftc.teamcode.config.subsystems;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import smile.interpolation.BilinearInterpolation; //I CAN SMILE NOW :DDDDDDDDD
import smile.interpolation.Interpolation2D;
//https://www.aihalo.dev/interpolation

public class Flywheel {
    private DcMotorEx shooter;

    private boolean shooterOn = false;
    private double targetSpeed = 1570;

    public static double kS = 0.03, kV = 0.0004, kP = 0.005;
    //kS stands for "static." It's the greatest power you can give the flywheel motor(s) without it moving. So it should be a really small number below like 0.05 or something. You should increase it until it begins to move and then decrease it until it stops moving again.
    //kV is the next number you tune. Pick a velocity like 1500 or something and then increase kV until it gets there.
    //Tune P. Send a ball through the launcher and increase P until it doesn't overshoot past the target velocity in the recovery phase. Keep increasing though because unless you see overshoot you're fine.

    private static final double[] xs = {44, 72, 100};
    private static final double[] ys = {10, 38, 66};
    private static final double[][] velocities = {
            {1200, 1200, 1275},
            {1275, 1275, 1350},
            {1325, 1360, 1400}
    };
    public static final Interpolation2D velocityInterpolation = new BilinearInterpolation(xs, ys, velocities);
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
        double xDistance = Math.abs(target.x()-current.x());
        double yDistance = Math.abs(target.y()-current.y());

        //if (close)
            setTarget(velocityInterpolation.interpolate(xDistance, yDistance));
        //else
        //    setTarget(1550);
    }



    public void setTarget(double velocity) {
        targetSpeed = velocity;
    }
    public void adjustTarget(double boost){targetSpeed += boost;}

    public double getTarget() {
        return targetSpeed;
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
