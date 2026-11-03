package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.config.robot.Robot;

public class hive2TipComfy {
    //private final Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of(7.5, 15, 270);
    private final Pose shootPreloads = poseFactory.of(12, 12, 220);
    private final Pose intakeFour = poseFactory.of(7, 8, 270);
    private final Pose shootFour = poseFactory.of(48, 120, 100);
    private final Pose point3Control1 = poseFactory.of(33.809, 44.5813, 0);
    private final Pose point3Control2 = poseFactory.of(10.1072, 115.5654, 0);
    private final Pose intakeFlower1 = poseFactory.of(48, 130, 100);
    private final Pose positionFlower2 = poseFactory.of(21, 48, 180);
    private final Pose intakeFlower2 = poseFactory.of(11, 48, 180);
    private final Pose shootAndPark = poseFactory.of(13, 126, 145);
    private final Pose point7Control1 = poseFactory.of(52.8329, 87.0083, 0);

    public Path shootingPreloads() {
        return Paths.line(startPose, shootPreloads).linear(startPose, shootPreloads);
    }

    public Path intakingFour() {
        return Paths.line(shootPreloads, intakeFour).linear(shootPreloads, intakeFour);
    }

    public Path shootingFour() {
        return Paths.curve(intakeFour, point3Control1, point3Control2, shootFour).linear(intakeFour, shootFour);
    }

    public Path intakingFlower1() {
        return Paths.line(shootFour, intakeFlower1).constant(shootFour);
    }

    public Path aligningFlower1() {
        return Paths.line(intakeFlower1, positionFlower2).linear(intakeFlower1, positionFlower2);
    }

    public Path intakingFlower2() {
        return Paths.line(positionFlower2, intakeFlower2).tangent();
    }

    public Path Finale() {
        return Paths.curve(intakeFlower2, point7Control1, shootAndPark).linear(intakeFlower2, shootAndPark);
    }


}
