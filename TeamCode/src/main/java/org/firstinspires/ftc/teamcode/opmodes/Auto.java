package org.firstinspires.ftc.teamcode.opmodes;

import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.commands.Commands;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.CommandOpMode;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

import static org.firstinspires.ftc.teamcode.config.paths.hive2Tip.*;

@Autonomous
public class Auto extends CommandOpMode {
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        robot.follower.setPose(startPose);

        //multipleTelemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);
        robot.intake.block();

        schedule(
                Commands.infinite(robot::periodic),
                Commands.infinite(() -> {
                    telemetry.addData("Shooter Velocity: ", robot.flywheel.getVelocity());
                    telemetry.addData("Shooter Target", robot.flywheel.getTarget());
                    telemetry.update();
                }),
                sequential(
                        follow(robot.follower, startToScore()).with(
                                waitUntil(() -> robot.follower.completion() > .95).then(
                                        robot.shoot()
                                )
                        ),
                        waitMs(500),
                        robot.unshoot(),
                        follow(robot.follower, park())
                )
        );
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
        robot.follower.update();
        Scheduler.execute();

        telemetry.addData("X", robot.follower.pose().x());
        telemetry.addData("Y", robot.follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(robot.follower.pose().heading()));
        telemetry.addData("Follower Mode", robot.follower.mode());
        telemetry.update();
    }

    @Override
    public void stop(){
        robot.startPose = robot.follower.pose();
    }
}
