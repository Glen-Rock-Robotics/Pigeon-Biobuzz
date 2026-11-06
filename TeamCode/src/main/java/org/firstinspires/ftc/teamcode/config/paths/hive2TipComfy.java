package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.interpolator.Interpolator;

import org.firstinspires.ftc.teamcode.config.pedro.Constants;
import org.firstinspires.ftc.teamcode.config.robot.Robot;

public class hive2TipComfy {
    //public static final Follower follower;

    public static final PoseFactory poseFactory = PoseFactory.degrees();

    public static final Pose startPose = poseFactory.of(7.5, 15, 270);
    public static final Pose shootPreloads = poseFactory.of(18, 18, 220);

    private static final Pose alignAngleIntakeFour = poseFactory.of(11.85, 20.62, 260);

    private static final Pose angleIntakeFour = poseFactory.of(8.5, 10, 260);
    private static final Pose alignIntakeFour = poseFactory.of(8.5, 16, 270);
    private static final Pose intakeFour = poseFactory.of(8.5, 11.5, 270);
    public static final Pose shootFour = poseFactory.of(54, 120, 100);
    public static final Pose point3Control1 = poseFactory.of(33.809, 44.5813, 0);
    public static final Pose point3Control2 = poseFactory.of(10.1072, 115.5654, 0);
    private static final Pose point3Segment1Heading = poseFactory.of(48, 120, 190);
    private static final Pose point3Segment3Start = poseFactory.of(48, 120, 50.0403);
    private static final Pose point3Segment3End = poseFactory.of(48, 120, 100);
    public static final Pose intakeFlower1 = poseFactory.of(55, 128, 100);
    public static final Pose positionFlower2 = poseFactory.of(21, 48, 180);
    public static final Pose intakeFlower2 = poseFactory.of(15.5, 48, 180);
    public static final Pose shootFlower2 = poseFactory.of(46.45, 119.25, 110);
    public static final Pose shootAndPark = poseFactory.of(13, 120, 145);
    public static final Pose point7Control1 = poseFactory.of(52.8329, 87.0083, 0);

    public static Path shootingPreloads() {
        return Paths.line(startPose, shootPreloads).linear(startPose, shootPreloads);
    }
    public static Path aligningFour() {
        return Paths.line(shootPreloads, alignAngleIntakeFour).linear(shootPreloads, alignAngleIntakeFour, .2);
    }

    public static Path angleIntakingFour() {
        return Paths.line(alignAngleIntakeFour, angleIntakeFour).linear(alignAngleIntakeFour, angleIntakeFour, .2);
    }

    public static Path reverseResetFour() {
        return Paths.line(angleIntakeFour, alignIntakeFour).linear(alignIntakeFour, alignIntakeFour, .2);
    }
    public static Path intakingFour() {
        return Paths.line(alignIntakeFour, intakeFour).linear(alignIntakeFour, intakeFour, .2);
    }

    public static Path reverseIntakingFour() {
        return Paths.line(intakeFour, alignIntakeFour).linear(alignIntakeFour, intakeFour, .2);
    }

    public static Path shootingFour() {
        return Paths.curve(intakeFour, point3Control1, point3Control2, shootFour).heading(Interpolator.piecewise().until(0.1127, Interpolator.constant(point3Segment1Heading)).until(0.8653, Interpolator.tangent).until(1, Interpolator.linear(point3Segment3Start, point3Segment3End)));
    }

    public static Path intakingFlower1() {
        return Paths.line(shootFour, intakeFlower1).constant(shootFour);
    }

    public static Path aligningFlower2() {
        return Paths.line(intakeFlower1, positionFlower2).linear(intakeFlower1, positionFlower2);
    }

    public static Path intakingFlower2() {
        return Paths.line(positionFlower2, intakeFlower2).tangent();
    }

    public static Path shootingFlower2() {
        return Paths.line(intakeFlower2, shootFlower2).linear(intakeFlower2, shootFlower2);
    }

    public static Path finale() {
        return Paths.line(shootFlower2, shootAndPark).linear(shootFlower2, shootAndPark);
    }

    ///should probably switch to grabbing from flowers first then from the pile last because the pile is probably less efficient


}
