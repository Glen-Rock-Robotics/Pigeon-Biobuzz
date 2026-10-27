package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.teamcode.config.CommandOpMode;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

public class Tele extends CommandOpMode {

    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        //r.f.setStartingPose(defaultPose);

        //multipleTelemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);


    }

    @Override
    public void init_loop() {
        if (gamepad1.xWasPressed()) {
        }

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        robot.periodic();

        if(gamepad1.rightBumperWasPressed()) robot.flywheel.activate();
        if(gamepad1.leftBumperWasPressed()) robot.flywheel.deactivate();

        if(gamepad1.dpadUpWasPressed()) robot.flywheel.adjustTarget(100);
        if(gamepad1.dpadDownWasPressed()) robot.flywheel.adjustTarget(-100);


        telemetry.addData("shooter speed", robot.flywheel.getVelocity());
        telemetry.addData("shooter target", robot.flywheel.getTarget());
        telemetry.addData("kp", robot.flywheel.kP);
        telemetry.addData("ks", robot.flywheel.kS);
        telemetry.addData("kv", robot.flywheel.kV);


    }


    @Override
    public void stop() {
        //robot.saveEnd();
    }



}
