package org.firstinspires.ftc.teamcode.config.robot;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.pedro.Constants;
import org.firstinspires.ftc.teamcode.config.subsystems.*;

import java.util.List;

public class Robot {
    //public final HardwareMap hardwareMap;

    //public final Telemetry telemetry;
//    public final Drivetrain drivetrain;
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

        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        periodic();
    }

    public void periodic(){

        //follower.update();
        flywheel.periodic();
        //drivetrain.periodic();
    }
}