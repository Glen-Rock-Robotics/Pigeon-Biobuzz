package org.firstinspires.ftc.teamcode.config.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.CommandBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class Intake {

    private DcMotor intake;
    private Servo flower;
    public Servo blocker;

    public boolean intakeOn = false;
    public boolean flowerEngaged = false;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake");
        blocker = hardwareMap.get(Servo.class, "stopperServo");
        flower = hardwareMap.get(Servo.class, "flowerServo");
    }

    public void flower(){
        flower.setPosition(0.05);
        flowerEngaged = true;
    }

    public void unflower(){
        flower.setPosition(.65);
        flowerEngaged = false;
    }

    public Command start(){
        return instant(() -> intake.setPower(1));
    }

    public Command stop(){
        return instant(() -> intake.setPower(0));
    }

    public Command reverse(){
        return instant(() -> intake.setPower(-1));
    }

    public void block(){
        blocker.setPosition(0.2);
    }

    public void unblock(){
        blocker.setPosition(0.5);
    }
}
