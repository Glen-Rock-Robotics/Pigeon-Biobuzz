package org.firstinspires.ftc.teamcode.config.subsystems;


import com.pedropathing.controllers.Controller;
import com.pedropathing.controllers.PIDController;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.pedro.Constants;

public class Drivetrain {
    private DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private Follower follower;
    private boolean hold = false, field = true;
    private boolean lockHeading = false;
    private double headingTargetRadians = 0; //whatever the target heading is

    Controller headingController = Controller.pid(1.75, 0, 0.09); //TODO: tune PID values

    public Drivetrain(HardwareMap hardwareMap, Pose start){
        follower = Constants.create(hardwareMap);
        follower.setPose(start);

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backRight.setDirection(DcMotor.Direction.REVERSE);
        //frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
    }

    private static double signedSquare(double raw) {
        return Math.signum(raw) * Math.pow(raw, 2);
    }



    public void startDrive(){follower.manual();}

    public void arcadeDrive(double forward, double strafe, double turn/*, Alliance alliance*/) {
        double headingRadians = follower.pose().heading();

        forward = signedSquare(forward);
        strafe = signedSquare(strafe);

        if (lockHeading) {
            turn = -headingController.calculate((AngleUnit.normalizeRadians(headingTargetRadians)) /*target*/, (AngleUnit.normalizeRadians(headingTargetRadians - headingRadians)) /*error*/);
            //turn = -headingController.run();
        } else {
            turn = signedSquare(turn);
        }


        /*if (alliance == Alliance.BLUE) headingRadians += Math.PI;*/

        double x = strafe * Math.cos(headingRadians) + forward * Math.sin(headingRadians);
        double y = strafe * -Math.sin(headingRadians) + forward * Math.cos(headingRadians);
//        double x = strafe;
//        double y = forward;
        y *= 1.1;

        double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(turn), 1);

        frontLeft.setPower((y + x + turn) / denominator);
        frontRight.setPower((y - x - turn) / denominator);
        backLeft.setPower((y - x + turn) / denominator);
        backRight.setPower((y + x - turn) / denominator);
    }

}
