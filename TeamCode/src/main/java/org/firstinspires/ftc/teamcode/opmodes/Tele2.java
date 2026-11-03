package org.firstinspires.ftc.teamcode.opmodes;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.CommandOpMode;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

@TeleOp
public class Tele2 extends CommandOpMode {

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

        robot.drivetrain.startDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        if(gamepad1.rightBumperWasPressed()) robot.intake.flower();
        if(gamepad1.leftBumperWasPressed()) robot.intake.unflower();

        if(gamepad1.rightTriggerWasPressed()) robot.intake.start().schedule();
        if(gamepad1.rightTriggerWasReleased()) robot.intake.stop().schedule();

        if(gamepad1.leftTriggerWasPressed()) robot.intake.reverse().schedule();
        if(gamepad1.leftTriggerWasReleased()) robot.intake.stop().schedule();

        if(gamepad1.triangleWasPressed())robot.intake.block();
        if(gamepad1.crossWasPressed())robot.intake.unblock();

        if(gamepad1.squareWasPressed())robot.intake.block();
        if(gamepad1.circleWasPressed())robot.intake.unblock();

        if(gamepad1.dpadUpWasPressed()) robot.flywheel.adjustTarget(10);
        if(gamepad1.dpadDownWasPressed()) robot.flywheel.adjustTarget(-10);

        //robot.flywheel.forPose(robot.follower.pose(), robot.shootTarget);


        if(gamepad2.rightTriggerWasPressed()) robot.flywheel.activate();
        if(gamepad2.right_trigger > 0.5 && robot.flywheel.getVelocity() > robot.flywheel.getTarget() - 10){
            robot.intake.unblock();
        }

        if(gamepad2.rightTriggerWasReleased()) {
            robot.intake.block();
            robot.flywheel.deactivate();
        }



        telemetry.addData("shooter speed", robot.flywheel.getVelocity());
        telemetry.addData("shooter target", robot.flywheel.getTarget());
        telemetry.addData("kp", robot.flywheel.kP);
        telemetry.addData("ks", robot.flywheel.kS);
        telemetry.addData("kv", robot.flywheel.kV);


    }


    @Override
    public void stop() {
        robot.startPose = robot.follower.pose();
    }



}
