package org.firstinspires.ftc.teamcode.config.robot;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.bylazar.telemetry.PanelsTelemetry;
import static com.pedropathing.ivy.commands.Commands.conditional;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.pedro.Constants;
import org.firstinspires.ftc.teamcode.config.subsystems.*;

import java.util.List;

public class Robot {
    //public final HardwareMap hardwareMap;

    //public final Telemetry telemetry;
    public static Pose startPose = new Pose(0, 0, 0);
    public static Pose shootTarget = new Pose(0,0, 0); // TODO: update with the correct target and start poses
    public static Pose shootPose = new Pose(0,0,0);

    public final Drivetrain drivetrain;
    public final Intake intake;
    public final Flywheel flywheel;
    public final Follower follower;

    private final List<LynxModule> hubs;

    public Robot(HardwareMap hardwareMap) {


//        telemetry = new MultipleTelemetry(
//                opMode.telemetry,
//                FtcDashboard.getInstance().getTelemetry(),
//                PanelsTelemetry.INSTANCE.getFtcTelemetry()
//        );

        flywheel = new Flywheel(hardwareMap);
        intake = new Intake(hardwareMap);
        follower = Constants.create(hardwareMap);

        drivetrain = new Drivetrain(hardwareMap);

        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        //periodic();
    }

    public void periodic(){

        //follower.update();
        flywheel.periodic();
        drivetrain.periodic();
    }


    ///COMMANDS START HERE
    public Command shoot(){
        //only shoots when flywheel is at the target speed
//        return parallel(
//            instant(flywheel::activate),
//                parallel(
//                        intake.unblock(),
//                        intake.start()
//                ).unless(() -> !flywheel.atTarget())
//        );

        return sequential(
                instant(flywheel::activate),
                waitMs(300), //TODO: tune waiting time
                parallel(
                        instant(() -> intake.unblock()),
                        intake.start()
                )
        );
    }

    public Command unshoot(){
        return parallel(
                instant(flywheel::deactivate),
                instant(() -> intake.block()),
                intake.stop()
        );
    }

}