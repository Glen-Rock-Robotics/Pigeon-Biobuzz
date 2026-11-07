package org.firstinspires.ftc.teamcode.opmodes;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.commands.Commands;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.CommandOpMode;
import org.firstinspires.ftc.teamcode.config.pedro.Constants;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

import static org.firstinspires.ftc.teamcode.config.paths.hive2TipWithAlliance.*;

@Autonomous
public class withAlliance extends CommandOpMode {
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        robot.drivetrain.follower.setPose(startPose);
        robot.drivetrain.follower.update();

        //multipleTelemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);
        robot.intake.block();

        schedule(
//                Commands.infinite(robot::periodic),
//                Commands.infinite(() -> {

//                    telemetry.addData("Shooter Velocity: ", robot.flywheel.getVelocity());
//                    telemetry.addData("Shooter Target", robot.flywheel.getTarget());
//                    telemetry.update();
//                }),
                sequential(
                        parallel(
                                instant(() -> robot.flywheel.setTarget(1590)),
                                instant(robot.flywheel::activate)
                        ),
                        waitMs(7000),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(1000),
                        robot.unshoot(),
                        robot.intake.start(),
                        follow(robot.drivetrain.follower, aligningFlower1()),
                        instant(() -> robot.intake.flower()),
                        follow(robot.drivetrain.follower, intakingFlower1()).until(() -> robot.drivetrain.follower.completion() > 0.95),
                        parallel(
                                instant(() -> robot.flywheel.setTarget(1590)),
                                instant(robot.flywheel::activate)
                        ),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(1500),
                        robot.unshoot(),
                        robot.intake.start(),
                        follow(robot.drivetrain.follower, aligningFlower2()),
                        follow(robot.drivetrain.follower, intakingFlower2()).until(() -> robot.drivetrain.follower.completion() > 0.95),
                        waitMs(1500),
                        follow(robot.drivetrain.follower, shootingFlower2()),
                        parallel(
                                instant(() -> robot.flywheel.setTarget(1590)),
                                instant(robot.flywheel::activate),
                                instant(() -> robot.intake.unflower())
                        ),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(2000),
                        robot.unshoot(),
                        follow(robot.drivetrain.follower, parking())
                ));
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
    public void loop(){
        robot.drivetrain.follower.update();
        robot.periodic();
        Scheduler.execute();

        telemetry.addData("Shooter Velocity: ", robot.flywheel.getVelocity());
        telemetry.addData("Shooter Target", robot.flywheel.getTarget());
        telemetry.addData("Completeion Mode", robot.drivetrain.follower.completion());
        telemetry.addData("servo", robot.intake.blocker.getPosition());
        telemetry.addData("X", robot.drivetrain.follower.pose().x());
        telemetry.addData("Y", robot.drivetrain.follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(robot.drivetrain.follower.pose().heading()));
        telemetry.addData("Follower Mode", robot.drivetrain.follower.mode());
        telemetry.update();
    }

    @Override
    public void stop(){
        robot.startPose = robot.drivetrain.follower.pose();
    }
}
