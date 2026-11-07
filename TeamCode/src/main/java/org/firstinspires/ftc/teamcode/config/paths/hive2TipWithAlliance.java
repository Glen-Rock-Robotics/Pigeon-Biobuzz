package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
public class hive2TipWithAlliance {
    private static final PoseFactory poseFactory = PoseFactory.degrees();

    public static final Pose startPose = poseFactory.of(63, 132, 90);
    public static final Pose alignFlower = poseFactory.of(52, 120, 90);
    public static final Pose intakeFlower = poseFactory.of(48, 128, 100);
    public static final Pose alignFlower2 = poseFactory.of(17, 48, 180);
    public static final Pose intakeFlower2 = poseFactory.of(12, 48, 180);
    public static final Pose shootFlower2 = poseFactory.of(32, 126, 125);
    public static final Pose park = poseFactory.of(14, 121, 180);

    public static Path aligningFlower1() {
        return Paths.line(startPose, alignFlower).constant(alignFlower);
    }

    public static Path intakingFlower1() {
        return Paths.line(alignFlower, intakeFlower).linear(alignFlower, intakeFlower);
    }

    public static Path aligningFlower2() {
        return Paths.line(intakeFlower, alignFlower2).linear(intakeFlower, alignFlower2);
    }

    public static Path intakingFlower2() {
        return Paths.line(alignFlower2, intakeFlower2).constant(intakeFlower2);
    }

    public static Path shootingFlower2() {
        return Paths.line(intakeFlower2, shootFlower2).linear(intakeFlower2, shootFlower2);
    }

    public static Path parking() {
        return Paths.line(shootFlower2, park).linear(shootFlower2, park);
    }
}
