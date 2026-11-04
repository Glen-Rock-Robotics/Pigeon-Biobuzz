package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.config.pedro.Constants;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

public class hive2TipComfy {
    //public static final Follower follower;

    public static final PoseFactory poseFactory = PoseFactory.degrees();

    public static final Pose startPose = poseFactory.of(7.5, 15, 270);
    public static final Pose shootPreloads = poseFactory.of(16, 16, 220);
    public static final Pose intakeFour = poseFactory.of(10, 10, 270);
    public static final Pose shootFour = poseFactory.of(48, 120, 100);
    public static final Pose point3Control1 = poseFactory.of(33.809, 44.5813, 0);
    public static final Pose point3Control2 = poseFactory.of(10.1072, 115.5654, 0);
    public static final Pose intakeFlower1 = poseFactory.of(48, 130, 100);
    public static final Pose positionFlower2 = poseFactory.of(21, 48, 180);
    public static final Pose intakeFlower2 = poseFactory.of(11, 48, 180);
    public static final Pose shootAndPark = poseFactory.of(13, 120, 145);
    public static final Pose point7Control1 = poseFactory.of(52.8329, 87.0083, 0);

    public static Path shootingPreloads() {
        return Paths.line(startPose, shootPreloads).linear(startPose, shootPreloads);
    }

    public static Path intakingFour() {
        return Paths.line(shootPreloads, intakeFour).linear(shootPreloads, intakeFour);
    }

    public static Path shootingFour() {
        return Paths.curve(intakeFour, point3Control1, point3Control2, shootFour).linear(intakeFour, shootFour);
    }

    public static Path intakingFlower1() {
        return Paths.line(shootFour, intakeFlower1).constant(shootFour).with(Constants.foresightConfig.maxPathSpeed.at(0.5));
    }

    public static Path aligningFlower2() {
        return Paths.line(intakeFlower1, positionFlower2).linear(intakeFlower1, positionFlower2);
    }

    public static Path intakingFlower2() {
        return Paths.line(positionFlower2, intakeFlower2).tangent().with(Constants.foresightConfig.maxPathSpeed.at(0.5));
    }

    public static Path finale() {
        return Paths.curve(intakeFlower2, point7Control1, shootAndPark).linear(intakeFlower2, shootAndPark);
    }

    ///should probably switch to grabbing from flowers first then from the pile last because the pile is probably less efficient


}
