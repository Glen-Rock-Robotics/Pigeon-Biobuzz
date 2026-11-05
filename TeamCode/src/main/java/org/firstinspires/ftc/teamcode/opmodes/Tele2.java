package org.firstinspires.ftc.teamcode.opmodes;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.hold;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.CommandOpMode;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

@TeleOp
public class Tele2 extends CommandOpMode {

    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        robot.drivetrain.follower.setPose(robot.startPose);
        //robot.drivetrain.follower.

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


        //robot.drivetrain.startDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

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

        //just shoot
        if(gamepad2.leftBumperWasPressed()) robot.flywheel.activate();

        if(gamepad2.left_bumper && robot.flywheel.getVelocity() > robot.flywheel.getTarget() - 10){
            robot.intake.unblock();
        }

        if(gamepad2.leftBumperWasReleased()) {
            robot.intake.block();
            robot.flywheel.deactivate();
        }


        //aim and shoot
        if(gamepad2.rightBumperWasPressed()){
            robot.drivetrain.follower.stop();
            hold(robot.drivetrain.follower, new Pose(robot.drivetrain.follower.pose().x(), robot.drivetrain.follower.pose().y(), robot.drivetrain.headingTargetRadians)).schedule();
            robot.flywheel.activate();

        }else if(!gamepad2.right_bumper){
            robot.drivetrain.follower.manual(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
        }

        if(gamepad2.rightBumperWasReleased()){
            robot.drivetrain.follower.stop();
            robot.intake.block();
            robot.flywheel.deactivate();
        }

        if(gamepad2.right_bumper && robot.flywheel.getVelocity() > robot.flywheel.getTarget() - 10){
            robot.intake.unblock();
        }


        //relocalize
        if(gamepad2.shareWasPressed()){
            robot.drivetrain.follower.setPose(new Pose(9, 7.5, Math.toRadians(180)));
            gamepad1.rumbleBlips(2);
            gamepad2.rumbleBlips(2);
        }

        if(gamepad2.dpadUpWasPressed()) robot.flywheel.kP += 0.0001;//robot.flywheel.adjustTarget(100);
        if(gamepad2.dpadDownWasPressed()) robot.flywheel.kP -= 0.0001;//robot.flywheel.adjustTarget(-100);

        if(gamepad2.dpadRightWasPressed()) robot.flywheel.kV += 0.0001;//robot.flywheel.adjustTarget(100);
        if(gamepad2.dpadLeftWasPressed()) robot.flywheel.kV -= 0.0001;

        telemetry.addData("shooter speed", robot.flywheel.getVelocity());
        telemetry.addData("shooter target", robot.flywheel.getTarget());
        telemetry.addData("pose", robot.drivetrain.follower.pose());
        telemetry.addData("targetpose", Math.toDegrees(robot.drivetrain.headingTargetRadians));
        telemetry.addData("kp", robot.flywheel.kP);
        telemetry.addData("ks", robot.flywheel.kS);
        telemetry.addData("kv", robot.flywheel.kV);


    }


    @Override
    public void stop() {
        robot.startPose = robot.drivetrain.follower.pose();
    }



}
