package org.firstinspires.ftc.teamcode.config.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class Intake {

    private DcMotor intake;
    private Servo flower;

    public boolean intakeOn = false;
    public boolean flowerEngaged = false;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake");
        flower = hardwareMap.get(Servo.class, "flower");
    }

    public void activateFlower(){
        flower.setPosition(.3);
        flowerEngaged = true;
    }

    public void deactivateFlower(){
        flower.setPosition(.5);
        flowerEngaged = false;
    }

    public void startIntake(){
        intake.setPower(1);
        intakeOn = true;
    }

    public void stopIntake(){
        intake.setPower(0);
        intakeOn = false;
    }

    public void reverseIntake(){
        intake.setPower(-1);
        intakeOn = true;
    }
}
