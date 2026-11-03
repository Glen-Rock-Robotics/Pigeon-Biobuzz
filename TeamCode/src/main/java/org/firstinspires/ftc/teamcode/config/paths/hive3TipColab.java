package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.config.robot.Robot;

public class hive3TipColab {
    //private final Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of(63, 133, 90);
    private final Pose alignFlower1 = poseFactory.of(48, 116, 90);
    private final Pose path1Control1 = poseFactory.of(49.6824, 96.7877, 0);
    private final Pose intakeFlower1 = poseFactory.of(49, 128, 100);
    private final Pose alignFlower2 = poseFactory.of(21.0849, 48, 180);
    private final Pose point3Control1 = poseFactory.of(16.9461, 105.7633, 0);
    private final Pose grabFlower2 = poseFactory.of(14, 48, 180);
    private final Pose shootFlower2 = poseFactory.of(22, 12, 220);
    private final Pose intakeFour = poseFactory.of(7.5, 9, 270);
    private final Pose point6Control1 = poseFactory.of(14.4687, 27.3908, 0);
    private final Pose shootFour = poseFactory.of(21, 12, 220);
    private final Pose park = poseFactory.of(9.6586, 88.4432, -81.561);

    public Path aligningFlower1() {
        return Paths.curve(startPose, path1Control1, alignFlower1).linear(startPose, alignFlower1);
    }

    public Path shooting() {
        return Paths.line(alignFlower1, intakeFlower1).linear(alignFlower1, intakeFlower1);
    }

    public Path aligningFlower2() {
        return Paths.curve(intakeFlower1, point3Control1, alignFlower2).linear(intakeFlower1, alignFlower2);
    }

    public Path grabbingFlower2() {
        return Paths.line(alignFlower2, grabFlower2).tangent();
    }

    public Path shootingFlower2() {
        return Paths.line(grabFlower2, shootFlower2).linear(grabFlower2, shootFlower2);
    }

    public Path intakingFour() {
        return Paths.curve(shootFlower2, point6Control1, intakeFour).linear(shootFlower2, intakeFour);
    }

    public Path shootingFour() {
        return Paths.line(intakeFour, shootFour).linear(intakeFour, shootFour);
    }

    public Path Finale() {
        return Paths.line(shootFour, park).reverseTangent();
    }


}
