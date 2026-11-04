package org.firstinspires.ftc.teamcode.opmodes;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.pedro.PedroCommands.hold;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.CommandOpMode;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

@TeleOp
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

        robot.drivetrain.startDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

//        robot.drivetrain.arcadeDrive(
//                -gamepad1.left_stick_y,
//                gamepad1.left_stick_x,
//                gamepad1.right_stick_x,
//        )

        if(gamepad1.rightBumperWasPressed()) robot.flywheel.activate();
        if(gamepad1.leftBumperWasPressed()) robot.flywheel.deactivate();

        if(gamepad1.rightTriggerWasPressed()) robot.intake.start().schedule();
        if(gamepad1.rightTriggerWasReleased()) robot.intake.stop().schedule();

        if(gamepad1.leftTriggerWasPressed()) robot.intake.reverse().schedule();
        if(gamepad1.leftTriggerWasReleased()) robot.intake.stop().schedule();


        if(gamepad1.crossWasPressed())robot.intake.block();
        if(gamepad1.triangleWasPressed())robot.intake.unblock();

//        if(gamepad1.dpadUpWasPressed()) robot.flywheel.kP += 0.0001;//robot.flywheel.adjustTarget(100);
//        if(gamepad1.dpadDownWasPressed()) robot.flywheel.kP -= 0.0001;//robot.flywheel.adjustTarget(-100);
//
//        if(gamepad1.dpadRightWasPressed()) robot.flywheel.kV += 0.0001;//robot.flywheel.adjustTarget(100);
//        if(gamepad1.dpadLeftWasPressed()) robot.flywheel.kV -= 0.0001;

        if(gamepad1.dpadUpWasPressed()) robot.flywheel.adjustTarget(10);
        if(gamepad1.dpadDownWasPressed()) robot.flywheel.adjustTarget(-10);

        //robot.flywheel.forPose(robot.follower.pose(), robot.shootTarget);


        if(gamepad2.rightTriggerWasPressed()) schedule(
                sequential(
                        parallel(
                                instant(() -> robot.flywheel.activate()),
                                instant(() -> robot.intake.unblock())
                        ),

                        waitMs(300), //TODO: tune waiting time
                        parallel(
                                instant(robot.intake::unblock),
                                instant(robot.intake::start)
                        )
                )
        );
        if(gamepad2.rightTriggerWasReleased()) robot.unshoot().schedule();

        if(gamepad2.rightBumperWasPressed()) schedule(
            parallel(
                robot.drivetrain.faceHive(),
                robot.shoot()
            )
        );
        if(gamepad2.rightBumperWasReleased()) robot.unshoot().schedule();

        telemetry.addData("shooter speed", robot.flywheel.getVelocity());
        telemetry.addData("shooter target", robot.flywheel.getTarget());
        telemetry.addData("kp", robot.flywheel.kP);
        telemetry.addData("ks", robot.flywheel.kS);
        telemetry.addData("kv", robot.flywheel.kV);


    }


    @Override
    public void stop() {
        robot.startPose = robot.drivetrain.follower.pose();
    }



}
