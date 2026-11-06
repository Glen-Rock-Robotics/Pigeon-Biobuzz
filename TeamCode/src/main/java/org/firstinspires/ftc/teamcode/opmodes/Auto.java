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

import static org.firstinspires.ftc.teamcode.config.paths.hive2TipComfy.*;

@Autonomous
public class Auto extends CommandOpMode {
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
                                follow(robot.drivetrain.follower, shootingPreloads()).with(
                                ),
                                instant(() -> robot.flywheel.setTarget(1590)),
                                instant(robot.flywheel::activate)
                        ),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(1000),
                        robot.unshoot(),
                        robot.intake.start(),
                        follow(robot.drivetrain.follower, intakingFour()),
                        //insert timeout and refresh path to move back and forth
                        waitMs(2000),
                        robot.intake.stop(),
                        instant(() -> robot.flywheel.setTarget(1550)),
                        follow(robot.drivetrain.follower, shootingFour()).with(
                                waitUntil(() -> robot.drivetrain.follower.completion() > .5).then(
                                        instant(robot.flywheel::activate)
                                )

                        ),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(1000),
                        robot.unshoot(),
                        instant(() -> robot.intake.flower()),
                        waitMs(1500),
                        follow(robot.drivetrain.follower, intakingFlower1()).with(
                                waitUntil(() -> robot.drivetrain.follower.completion() > .5).then(
                                        robot.intake.start()
                                )


                        ),
                        instant(robot.flywheel::activate),
                        waitMs(1000),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(1500),
                        robot.unshoot(),
                        follow(robot.drivetrain.follower, aligningFlower2()),
                        robot.intake.start(),
                        follow(robot.drivetrain.follower, intakingFlower2()),
                        waitMs(2000),
                        robot.intake.stop(),
                        follow(robot.drivetrain.follower, shootingFlower2()).with(
                                parallel(
                                        instant(() -> robot.intake.unflower()),
                                        waitUntil(() -> robot.drivetrain.follower.completion() > .5).then(
                                                parallel(
                                                        instant(robot.flywheel::activate)
                                                )
                                        )
                                )

                        ),
                        instant(robot.intake::unblock),
                        robot.intake.start(),
                        waitMs(3000),
                        follow(robot.drivetrain.follower, finale()).with(
                                robot.unshoot())


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
