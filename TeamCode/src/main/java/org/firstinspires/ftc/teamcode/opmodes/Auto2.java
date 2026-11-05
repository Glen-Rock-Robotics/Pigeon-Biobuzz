package org.firstinspires.ftc.teamcode.opmodes;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static org.firstinspires.ftc.teamcode.config.paths.biggestTreason.*;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.CommandOpMode;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

@Autonomous
public class Auto2 extends CommandOpMode {
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap);
        robot.drivetrain.follower.setPose(start);
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
                        instant(robot.flywheel::activate).with(
                                waitUntil(() -> robot.flywheel.atTarget()).then(
                                        parallel(
                                                instant(robot.intake::unblock),
                                                robot.intake.start()
                                        )
                                )
                        ),
                        waitMs(3000),
                        robot.unshoot(),
                        follow(robot.drivetrain.follower, alignToFour()),
                        waitMs(1000),
                        robot.intake.start(),
                        follow(robot.drivetrain.follower, intakeFour()),
                        robot.intake.stop(),
                        follow(robot.drivetrain.follower, alignToShoot()),
                        follow(robot.drivetrain.follower, shootFour()).with(
                                waitUntil(() -> robot.drivetrain.follower.completion() > .5).then(
                                        instant(robot.flywheel::activate)
                                )),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(3000),
                        robot.unshoot(),
                        instant(() -> robot.intake.flower()),
                        follow(robot.drivetrain.follower, intakeBackFlower1()).with(
                                waitUntil(() -> robot.drivetrain.follower.completion() > .5).then(
                                        robot.intake.start()
                                )


                        ),
                        waitMs(1000),
                        instant(robot.flywheel::activate),
                        waitMs(1000),
                        parallel(
                                instant(robot.intake::unblock),
                                robot.intake.start()
                        ),
                        waitMs(3000),
                        robot.unshoot(),
                        follow(robot.drivetrain.follower, moveBackFlower1()),
                        follow(robot.drivetrain.follower, alignFlower2()),
                        robot.intake.start(),
                        follow(robot.drivetrain.follower, intakeFlower2()),
                        waitMs(2000),
                        robot.intake.stop(),
                        follow(robot.drivetrain.follower, moveBackFlower2()),
                        follow(robot.drivetrain.follower, shootFlower2()).with(
                                parallel(
                                        instant(() -> robot.intake.unflower()),
                                        waitUntil(() -> robot.drivetrain.follower.completion() > .5).then(
                                                instant(robot.flywheel::activate)
                                        )
                                )
                        ),
                        instant(robot.intake::unblock),
                        robot.intake.start(),
                        waitMs(3000),
                        robot.unshoot(),
                        follow(robot.drivetrain.follower, park())

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
        robot.drivetrain.follower.update();
        robot.periodic();
        Scheduler.execute();

        telemetry.addData("Shooter Velocity: ", robot.flywheel.getVelocity());
        telemetry.addData("Shooter Target", robot.flywheel.getTarget());

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
